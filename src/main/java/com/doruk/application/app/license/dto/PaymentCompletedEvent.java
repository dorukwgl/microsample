package com.doruk.application.app.license.dto;

import io.micronaut.serde.annotation.Serdeable;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Optional;

@Serdeable
public record PaymentCompletedEvent(
        String externalOrgId,
        String skuId,
        Long tierId,
        OffsetDateTime validUntil,
        String entitlements,
        String productName,
        String tierName,
        int seats,
        Optional<String> parentSkuId,
        boolean isAddon
) {
}
