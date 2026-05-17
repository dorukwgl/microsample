package com.doruk.application.app.admin.dto;

import io.micronaut.core.annotation.Introspected;
import jakarta.annotation.Nullable;

import java.time.OffsetDateTime;

@Introspected
public record GrantLicenseCmd(
        String orgId,
        String skuId,
        Long tierId,
        OffsetDateTime validUntil,
        @Nullable String entitlements,
        String productName,
        String tierName,
        int seats,
        @Nullable String parentSkuId,
        boolean isAddon
) {}
