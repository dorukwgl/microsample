package com.doruk.presentation.auth.dto;

import com.doruk.infrastructure.annotataions.ValidUserAgent;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.Header;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Serdeable
public record DeviceInfoRequest(
        @Nullable
        @Header("X-Device-Id")
        @Size(max = 200, min = 32)
        @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
        String deviceId,

        @NotBlank
        @Header("User-Agent")
        @ValidUserAgent
        String userAgent
) {
}
