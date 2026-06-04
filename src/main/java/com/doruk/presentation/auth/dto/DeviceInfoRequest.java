package com.doruk.presentation.auth.dto;

import com.doruk.infrastructure.annotations.ValidUserAgent;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.Header;
import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Serdeable
public record DeviceInfoRequest(
        @Parameter(description = "Firebase notification device id (FCM token) or random UUID for web browsers. " +
                "Used for push notifications. Can differ between sessions on the same physical device.")
        @Parameter(description = "Not to be confused with X-Fingerprint-Id (biometric hardware device ID)")
        @Nullable
        @Header("X-Device-Id")
        @Size(max = 255, min = 32) @Pattern(regexp = "^[a-zA-Z0-9:_-]{32,255}$")
        String deviceId,

        @Parameter(description = "Biometric/fingerprint hardware device identifier. " +
                "Persistent across sessions on the same physical device. " +
                "Same value used during biometric enrollment.")
        @Nullable
        @Header("X-Fingerprint-Id")
        @Size(min = 32, max = 255)
        String fingerprintDeviceId,

        @Parameter(required = false)
        @NotBlank
        @Header("User-Agent")
        @ValidUserAgent
        String userAgent
) {
}
