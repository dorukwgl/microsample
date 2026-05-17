package com.doruk.presentation.admin.dto;

import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

@Serdeable
public record GrantLicenseRequest(
        @NotBlank @Schema(description = "Organization UUID to grant the license to") String orgId,
        @NotBlank @Schema(description = "SKU identifier of the product") String skuId,
        @NotNull @Schema(description = "Tier identifier") Long tierId,
        @NotNull @Schema(description = "License validity end date (ISO-8601)") OffsetDateTime validUntil,
        @Nullable @Schema(description = "Entitlements as JSON string") String entitlements,
        @NotBlank @Schema(description = "Product display name") String productName,
        @NotBlank @Schema(description = "Tier display name") String tierName,
        @Schema(description = "Number of seats") int seats,
        @Nullable @Schema(description = "Parent SKU ID for addon licenses") String parentSkuId,
        @Schema(description = "Whether this is an addon license") boolean isAddon
) {}
