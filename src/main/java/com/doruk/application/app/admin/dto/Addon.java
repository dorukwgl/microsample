package com.doruk.application.app.admin.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

import java.time.OffsetDateTime;

@Serdeable
@Builder
public record Addon(
        String productName,
        String tierName,
        String skuId,
        String tierId,
        String status,
        String entitlements,
        OffsetDateTime validUntil
) {
}
