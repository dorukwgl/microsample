package com.doruk.application.app.license.dto;

import io.micronaut.serde.annotation.Serdeable;

import java.util.Optional;

@Serdeable
public record PaymentExpiredEvent(
        String externalOrgId,
        String skuId,
        Optional<String> parentSkuId,
        boolean isAddon
) {
}
