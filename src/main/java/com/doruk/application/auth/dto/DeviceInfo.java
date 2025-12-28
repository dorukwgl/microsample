package com.doruk.application.auth.dto;

import io.micronaut.core.annotation.Introspected;

import java.util.Optional;

@Introspected
public record DeviceInfo(
        Optional<String> deviceId,
        Optional<String> platform,
        Optional<String> brand1,
        Optional<String> brand2
) {
    public Optional<String> deviceInfo() {
        if (platform.isEmpty() && brand1.isEmpty() && brand2.isEmpty())
            return Optional.empty();

        return Optional.of(String.format("%s (%s) on %s",
                brand1.orElse(""),
                brand2.orElse(""),
                platform.orElse(""))
                .strip()
        );
    }
}
