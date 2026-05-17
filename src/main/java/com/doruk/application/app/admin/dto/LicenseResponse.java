package com.doruk.application.app.admin.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Serdeable
@Builder
public record LicenseResponse(
        String id,
        String productName,
        String tierName,
        String licenseKey,
        String skuId,
        String tierId,
        String status,
        String entitlements,
        int seats,
        int totalSeats,
        int assignedSeats,
        UUID organizationId,
        OffsetDateTime validFrom,
        OffsetDateTime validUntil,
        Addon[] addons
) {
}
