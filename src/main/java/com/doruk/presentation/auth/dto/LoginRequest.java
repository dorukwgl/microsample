package com.doruk.presentation.auth.dto;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Serdeable
public record LoginRequest(
        @NotBlank
        @Size(max = 150, min = 1)
        String identifier,

        @NotBlank
        @Size(max = 150, min = 1)
        String password,

        @Nullable
        @Size(max = 200, min = 32)
        @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
        String deviceId,

        @Nullable
        @Size(min = 3, max = 200)
        String deviceInfo
) {
}
