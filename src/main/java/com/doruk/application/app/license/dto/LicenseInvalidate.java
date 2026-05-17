package com.doruk.application.app.license.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

import java.util.Optional;

@Serdeable
@Builder
public record LicenseInvalidate(
        String event,
        String licenseKey,
        String orgId,
        String skuId,
        String status,
        int seats,
        Optional<String> parentSkuId,
        boolean isAddon
) {}
