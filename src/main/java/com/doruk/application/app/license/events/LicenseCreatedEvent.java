package com.doruk.application.app.license.events;

import com.doruk.application.interfaces.EventDto;
import lombok.Builder;

import java.util.Optional;

@Builder
public record LicenseCreatedEvent(
        String callId,
        String licenseKey,
        String orgId,
        String skuId,
        String tierId,
        String status,
        String validUntil,
        String entitlements,
        int seats,
        Optional<String> parentSkuId,
        boolean isAddon
) implements EventDto {
    @Override
    public String eventSubject() {
        return "license.created";
    }
}
