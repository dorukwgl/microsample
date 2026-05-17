package com.doruk.application.app.license.dto;

import com.doruk.domain.shared.enums.LicenseStatus;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.Optional;

@Serdeable
@Builder
public record LicenseCmd(
        String id,
//        String event,
        String licenseKey,
        String orgId,
        String skuId,
        Long tierId,
        LicenseStatus status,
        String productName,
        String tierName,
        OffsetDateTime validUntil,
        String entitlements,
        int seats,
        boolean isAddon,
        Optional<String> parentSkuId
) {}
