package com.doruk.application.app.admin.service;

import com.doruk.application.app.admin.repo.AdminServiceRepo;
import com.doruk.application.app.admin.dto.*;
import com.doruk.application.app.license.LicenseService;
import com.doruk.application.app.license.dto.LicenseCmd;
import com.doruk.application.app.license.dto.PaymentCompletedEvent;
import com.doruk.application.app.license.events.LicenseRevokedEvent;
import com.doruk.application.app.license.events.LicenseUpdatedEvent;
import com.doruk.application.app.license.repo.LicenseServiceRepository;
import com.doruk.application.dto.PageQuery;
import com.doruk.application.dto.PageResponse;
import com.doruk.application.exception.NotFoundException;
import com.doruk.application.interfaces.EventPublisher;
import com.doruk.domain.shared.enums.LicenseStatus;
import com.doruk.infrastructure.dto.InfoResponse;
import io.micronaut.json.JsonMapper;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class AdminService {
    private final AdminServiceRepo repo;
    private final LicenseServiceRepository licenseRepo;
    private final LicenseService licenseService;
    private final EventPublisher natsEvent;
    private final JsonMapper jsonMapper;

    public ProductServerResponse create(CreateProductServerCmd cmd) {
        return repo.create(cmd);
    }

    public PageResponse<ProductServerResponse> list(PageQuery query, String nameSearch) {
        return repo.list(query, nameSearch);
    }

    public void deleteProductServer(String skuId) {
        var existing = repo.findBySkuId(skuId);
        if (existing.isEmpty())
            throw new NotFoundException("Product server not found for SKU: " + skuId);
        repo.deleteBySkuId(skuId);
    }

    public PageResponse<LicenseResponse> getLicensesByOrgId(UUID orgId, PageQuery query) {
        return repo.getLicensesByOrgId(orgId, query);
    }

    public KeyPairDto generateEd25519KeyPair() throws RuntimeException {
        KeyPairGenerator generator = null;
        try {
            generator = KeyPairGenerator.getInstance("Ed25519");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        java.security.KeyPair keyPair = generator.generateKeyPair();

        String privateKey = Base64.getEncoder()
                .encodeToString(keyPair.getPrivate().getEncoded()); // PKCS#8 format

        String publicKey = Base64.getEncoder()
                .encodeToString(keyPair.getPublic().getEncoded());  // X.509/SPKI format

        return new KeyPairDto(privateKey, publicKey);
    }

    public InfoResponse grantLicense(GrantLicenseCmd cmd) {
        var event = new PaymentCompletedEvent(
                cmd.orgId(),
                cmd.skuId(),
                cmd.tierId(),
                cmd.validUntil(),
                cmd.entitlements(),
                cmd.productName(),
                cmd.tierName(),
                cmd.seats(),
                Optional.ofNullable(cmd.parentSkuId()),
                cmd.isAddon()
        );
        licenseService.onPayment(event);
        return new InfoResponse("License granted");
    }

    public InfoResponse revokeLicense(String licenseId) {
        var existing = repo.findLicenseById(licenseId);
        if (existing.isEmpty())
            throw new NotFoundException("License not found: " + licenseId);

        var cmd = licenseRepo.updateLicenseStatus(licenseId, LicenseStatus.REVOKED);
        var builder = LicenseRevokedEvent.builder()
                .orgId(cmd.orgId())
                .licenseKey(cmd.licenseKey())
                .skuId(cmd.skuId())
                .parentSkuId(cmd.parentSkuId())
                .status(cmd.status().name())
                .isAddon(cmd.isAddon());
        try {
            builder.callId(jsonMapper.writeValueAsString(builder.build()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        natsEvent.publish(builder.build());
        return new InfoResponse("License revoked");
    }

    public InfoResponse suspendLicense(String licenseId) {
        var existing = repo.findLicenseById(licenseId);
        if (existing.isEmpty())
            throw new NotFoundException("License not found: " + licenseId);

        var cmd = licenseRepo.updateLicenseStatus(licenseId, LicenseStatus.SUSPENDED);
        publishUpdatedEvent(cmd);
        return new InfoResponse("License suspended");
    }

    public InfoResponse reactivateLicense(String licenseId) {
        var existing = repo.findLicenseById(licenseId);
        if (existing.isEmpty())
            throw new NotFoundException("License not found: " + licenseId);

        var cmd = licenseRepo.updateLicenseStatus(licenseId, LicenseStatus.ACTIVE);
        publishUpdatedEvent(cmd);
        return new InfoResponse("License reactivated");
    }

    private void publishUpdatedEvent(LicenseCmd cmd) {
        var builder = LicenseUpdatedEvent.builder()
                .orgId(cmd.orgId())
                .licenseKey(cmd.licenseKey())
                .skuId(cmd.skuId())
                .tierId(cmd.tierId())
                .status(cmd.status().name())
                .validUntil(cmd.validUntil() != null ? cmd.validUntil().toString() : null)
                .entitlements(cmd.entitlements())
                .seats(cmd.seats())
                .parentSkuId(cmd.parentSkuId())
                .isAddon(cmd.isAddon());
        try {
            builder.callId(jsonMapper.writeValueAsString(builder.build()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        natsEvent.publish(builder.build());
    }
}
