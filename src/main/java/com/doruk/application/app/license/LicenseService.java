package com.doruk.application.app.license;

import com.doruk.application.app.license.dto.*;
import com.doruk.application.app.license.events.LicenseExpiredEvent;
import com.doruk.application.app.license.events.LicenseRevokedEvent;
import com.doruk.application.app.license.events.LicenseUpdatedEvent;
import com.doruk.application.app.license.repo.LicenseServiceRepository;
import com.doruk.application.dto.PageQuery;
import com.doruk.application.dto.PageResponse;
import com.doruk.application.exception.*;
import com.doruk.application.interfaces.EventPublisher;
import com.doruk.application.policies.LicensePolicy;
import com.doruk.domain.license.entity.License;
import com.doruk.domain.shared.enums.LicenseStatus;
import com.doruk.infrastructure.logging.LoggingService;
import com.doruk.infrastructure.persistence.users.UserRepository;
import com.doruk.infrastructure.util.LicenseKeysUtils;
import io.micronaut.json.JsonMapper;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class LicenseService {
    private final EventPublisher natsEvent;
    private final LicenseServiceRepository repo;
    private final UserRepository userRepo;
    private final JsonMapper jsonMapper;
    private final JSqlClient sqlClient;

    /**
     *
     * org id and sku_id to be sent for getting license information (first time)
     * license key to be sent for license confirmation
     * <p>
     */

    private LicenseStatus transferLicenseState(LicenseStatus licenseStatus) {
        return licenseStatus == LicenseStatus.ACTIVE || licenseStatus == LicenseStatus.EXPIRED ?
                LicenseStatus.ACTIVE : licenseStatus;
    }

    private LicenseCmd processAddonLicense(PaymentCompletedEvent dto) {
        // upsert the addon: (nats may call multiple times, user may upgrade tier etc)
        var existingParent = repo.findLicense(dto.externalOrgId(), dto.parentSkuId().orElseThrow())
                .orElseThrow(() -> new IncompleteStateException("OnPayment: Parent not found: Addons must have parent product active."));

        return repo.upsertAddonLicense(existingParent.id(), dto);
    }

    private LicenseCmd processProductLicense(PaymentCompletedEvent dto) {
        // upsert the license: (nats may call multiple times, user may upgrade tier etc)
        var existing = repo.findLicense(dto.externalOrgId(), dto.skuId());

        var key = existing.map(LicenseCmd::licenseKey)
                .orElse(LicenseKeysUtils.createLicenseKey());

        return repo.upsertProductLicense(key, dto);
    }

    private LicenseCmd processAddonLicenseRenew(PaymentRenewedEvent dto) {
        var existingParent = repo.findLicense(dto.externalOrgId(), dto.parentSkuId().orElseThrow())
                .orElseThrow(() -> new IncompleteStateException("OnPaymentRenew: Parent not found: Addons must have parent product active."));

        var addonLicense = repo.findAddon(existingParent.id(), dto.skuId())
                .orElseThrow(() -> new IncompleteStateException("OnPaymentRenew: Addon not found:"));

        return repo.upsertAddonLicenseRenew(existingParent.id(), dto,
                transferLicenseState(addonLicense.status()));
    }

    private LicenseCmd processProductLicenseRenew(PaymentRenewedEvent dto) {
        var existingProduct = repo.findLicense(dto.externalOrgId(), dto.skuId())
                .orElseThrow(() -> new IncompleteStateException("OnPaymentRenew: License not found:"));

        return repo.upsertProductLicenseRenew(existingProduct.id(), dto,
                transferLicenseState(existingProduct.status()));
    }

    private LicenseCmd processAddonLicenseInvalidate(String orgId, String skuId, Optional<String> parentSkuId, LicenseStatus status) {
        var existingParent = repo.findLicense(orgId, parentSkuId.orElseThrow())
                .orElseThrow(() -> new IncompleteStateException("OnPaymentInvalidate: Parent not found: Addons must have parent product active."));

        var addonLicense = repo.findAddon(existingParent.id(), skuId)
                .orElseThrow(() -> new IncompleteStateException("OnPaymentInvalidate: Addon not found:"));

        return repo.updateAddonLicenseStatus(addonLicense.id(), status);
    }

    private LicenseCmd processProductLicenseInvalidate(String orgId, String skuId, LicenseStatus status) {
        var existingProduct = repo.findLicense(orgId, skuId)
                .orElseThrow(() -> new IncompleteStateException("OnPaymentInvalidate: License not found:"));

        return repo.updateLicenseStatus(existingProduct.id(), status);
    }

    private LicenseResponse verifyOrgActionsReturningLicense(String userId, String licenseKey, UUID targetUserId) {
        var adminUser = userRepo.getCurrentUser(userId);
        var license = repo.findLicenseByKey(licenseKey)
                .orElseThrow(() -> new NotFoundException("License not found"));

        if (repo.userExistsOnOrg(license.organizationId(), targetUserId))
            throw new NotFoundException("User not found in your organization");

        if (!LicensePolicy.canManageLicense(adminUser, license))
            throw new ForbiddenException("Only org admin can assign seats");

        return license;
    }

    public void onPayment(PaymentCompletedEvent dto) {
        try {
            // check if org exists, log error if not
            if (!repo.orgExists(dto.externalOrgId()))
                throw new InvalidInputException("OnPayment: Failed to create license. ORG not found: " + dto.externalOrgId());

            if (dto.isAddon() && dto.parentSkuId().isEmpty())
                throw new InvalidInputException("OnPayment: Failed to create license: addon should have parent sku id");

            var license = dto.isAddon() ?
                    processAddonLicense(dto) :
                    processProductLicense(dto);

            var event = LicenseUpdatedEvent.builder()
                    .orgId(license.orgId())
                    .licenseKey(license.licenseKey())
                    .entitlements(license.entitlements())
                    .isAddon(license.isAddon())
                    .validUntil(license.validUntil().toString())
                    .parentSkuId(license.parentSkuId())
                    .status(license.status().name())
                    .tierId(license.tierId())
                    .seats(license.seats());

            // create callId (unique for each event)
            event.callId(jsonMapper.writeValueAsString(event.build()));

            // fire the webhook
            natsEvent.publish(event.build());
        } catch (InvalidInputException | IncompleteStateException e) {
            LoggingService.logError(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void onPaymentRenew(PaymentRenewedEvent dto) {
        try {
            if (dto.isAddon() && dto.parentSkuId().isEmpty())
                throw new InvalidInputException("OnPayment: Failed to create license: addon should have parent sku id");

            var license = dto.isAddon() ?
                    processAddonLicenseRenew(dto) :
                    processProductLicenseRenew(dto);

            var event = LicenseUpdatedEvent.builder()
                    .orgId(license.orgId())
                    .licenseKey(license.licenseKey())
                    .entitlements(license.entitlements())
                    .isAddon(license.isAddon())
                    .validUntil(license.validUntil().toString())
                    .parentSkuId(license.parentSkuId())
                    .status(license.status().name())
                    .tierId(license.tierId());

            // create callId, unique for each event
            event.callId(jsonMapper.writeValueAsString(event.build()));

            // fire the webhook
            natsEvent.publish(event.build());
        } catch (InvalidInputException | IncompleteStateException e) {
            LoggingService.logError(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void onPaymentRevoked(PaymentRevokedEvent dto) {
        try {
            if (dto.isAddon() && dto.parentSkuId().isEmpty())
                throw new InvalidInputException("OnPaymentRevoked: Failed to revoke license: addon should have parent sku id");

            var license = dto.isAddon() ?
                    processAddonLicenseInvalidate(dto.externalOrgId(), dto.skuId(), dto.parentSkuId(), LicenseStatus.REVOKED) :
                    processProductLicenseInvalidate(dto.externalOrgId(), dto.skuId(), LicenseStatus.REVOKED);

            var event = LicenseRevokedEvent.builder()
                    .orgId(license.orgId())
                    .licenseKey(license.licenseKey())
                    .isAddon(license.isAddon())
                    .skuId(license.skuId())
                    .parentSkuId(license.parentSkuId())
                    .status(license.status().name());

            // create callId, unique for each event
            event.callId(jsonMapper.writeValueAsString(event.build()));

            // fire the webhook
            natsEvent.publish(event.build());
        } catch (InvalidInputException | IncompleteStateException e) {
            LoggingService.logError(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void onPaymentExpired(PaymentExpiredEvent dto) {
        try {
            if (dto.isAddon() && dto.parentSkuId().isEmpty())
                throw new InvalidInputException("OnPaymentExpired: Failed to expire license: addon should have parent sku id");

            var license = dto.isAddon() ?
                    processAddonLicenseInvalidate(dto.externalOrgId(), dto.skuId(), dto.parentSkuId(), LicenseStatus.EXPIRED) :
                    processProductLicenseInvalidate(dto.externalOrgId(), dto.skuId(), LicenseStatus.EXPIRED);

            var event = LicenseExpiredEvent.builder()
                    .orgId(license.orgId())
                    .licenseKey(license.licenseKey())
                    .isAddon(license.isAddon())
                    .skuId(license.skuId())
                    .parentSkuId(license.parentSkuId())
                    .status(license.status().name());

            // create callId, unique for each event
            event.callId(jsonMapper.writeValueAsString(event.build()));

            // fire the webhook
            natsEvent.publish(event.build());
        } catch (InvalidInputException | IncompleteStateException e) {
            LoggingService.logError(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public PageResponse<LicenseResponse> getLicenses(String userId, PageQuery query) {
        return repo.getLicenses(userId, query);
    }

    public PageResponse<SeatDto> listSeats(String userId, String licenseKey, PageQuery query) {
        var currentUser = userRepo.getCurrentUser(userId);
        var license = repo.findLicenseByKey(licenseKey)
                .orElseThrow(() -> new NotFoundException("License not found"));

        if (!LicensePolicy.canManageLicense(currentUser, license))
            throw new ForbiddenException("You don't have permission to view this license");

        return repo.getAllocatedSeats(license.id(), query);
    }

    // assign seat to a user from same org, is not already assigned
    public void assignSeat(String userId, String licenseKey, UUID targetUserId) {
        var license = verifyOrgActionsReturningLicense(userId, licenseKey, targetUserId);

        // check if user is already assigned a seat
        if (repo.isSeatAssigned(UUID.fromString(license.id()), targetUserId))
            throw new ConflictingArgumentException("User already has a seat assigned");

        // use sql transaction with lock by key
        repo.assignSeatTransactional(
                UUID.fromString(userId), // assigned by
                UUID.fromString(license.id()),
                targetUserId,  // assigned to
                License::allocateSeat // domain logic enforcement
        );
    }

    public void revokeSeat(String userId, String licenseKey, UUID targetUserId) {
        var license = verifyOrgActionsReturningLicense(userId, licenseKey, targetUserId);
        repo.releaseSeatTransactional(
                UUID.fromString(license.id()),
                targetUserId,
                License::releaseSeat
        );
    }
}
