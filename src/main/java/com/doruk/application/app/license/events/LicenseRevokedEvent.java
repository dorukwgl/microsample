package com.doruk.application.app.license.events;

import com.doruk.application.interfaces.EventDto;
import lombok.Builder;

import java.util.Optional;

@Builder
public record LicenseRevokedEvent(
        String callId,
        String licenseKey,
        String orgId,
        String skuId,
        String status,
        Optional<String> parentSkuId,
        boolean isAddon
) implements EventDto {
    @Override
    public String eventSubject() {
        return "license.revoked";
    }
}
