package com.doruk.application.app.interop.dto;

import com.doruk.domain.shared.enums.LicenseStatus;
import com.doruk.domain.shared.enums.LicenseType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;

@Serdeable
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record LicenseInfoResponse(
    String id,
    String licenseKey,
    String organizationId,
    String skuId,
    String tierId,
    LicenseType type,
    LicenseStatus status,
    String name,
    String tierName,
    String entitlements,
    Integer totalSeats,
    Integer assignedSeats,
    OffsetDateTime validFrom,
    OffsetDateTime validUntil,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt,
    List<LicenseInfoResponse> addons // without: key, seats, org
) {
}
