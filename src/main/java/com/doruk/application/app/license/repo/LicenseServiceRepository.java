package com.doruk.application.app.license.repo;


import com.doruk.application.app.license.dto.*;
import com.doruk.application.dto.PageQuery;
import com.doruk.application.dto.PageResponse;
import com.doruk.domain.license.entity.License;
import com.doruk.domain.shared.enums.LicenseStatus;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public interface LicenseServiceRepository {
    boolean orgExists(String orgId);

    Optional<LicenseCmd> findLicense(String orgId, String skuId);

    Optional<LicenseCmd> findAddon(String parentLicenseId, String skuId);

    LicenseCmd upsertProductLicense(String licenseKey, PaymentCompletedEvent dto);

    LicenseCmd upsertAddonLicense(String licenseId, PaymentCompletedEvent dto);

    LicenseCmd upsertProductLicenseRenew(String licenseId, PaymentRenewedEvent dto, LicenseStatus status);

    LicenseCmd upsertAddonLicenseRenew(String parentLicenseId, PaymentRenewedEvent dto, LicenseStatus status);

    LicenseCmd updateLicenseStatus(String licenseId, LicenseStatus status);

    LicenseCmd updateAddonLicenseStatus(String addonId, LicenseStatus status);

    PageResponse<LicenseResponse> getLicenses(String userId, PageQuery query);

    Optional<LicenseResponse> findLicenseByKey(String licenseKey);

    PageResponse<SeatDto> getAllocatedSeats(String licenseId, PageQuery query);

    boolean userExistsOnOrg(UUID orgId, UUID userId);

    boolean isSeatAssigned(UUID licenseId, UUID userId);

    /**
     * Atomically lock the license row, apply a domain function, and save it — all within one transaction.
     * The pessimistic lock (FOR UPDATE) is held until the save completes, then released.
     *
     * @param licenseId the license to lock and modify
     * @param domainFn   receives the locked License for mutation
     * @return the modified License
     * @throws com.doruk.domain.exception.DomainException propagated from domainFn,
     *                                                   triggers transaction rollback
     * @throws java.util.NoSuchElementException           if the license key is not found
     */
    void assignSeatTransactional(UUID adminId, UUID licenseId, UUID userId,  Consumer<License> domainFn);

    /**
     *
     * @param licenseId
     * @param userId
     * @param domainFn
     * @return
     */
    void releaseSeatTransactional(UUID licenseId, UUID userId,  Consumer<License> domainFn);
}
