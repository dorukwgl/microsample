package com.doruk.presentation.auth.dto;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Serdeable
public record DeviceInfoRequest(
        @Nullable
        @Size(max = 200, min = 32)
        @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
        String deviceId,

        @Nullable
        @Size(min = 3, max = 50)
        String platform,

        @Nullable
        @Size(min = 3, max = 50)
        String brand1,

        @Nullable
        @Size(min = 3, max = 50)
        String brand2
) {
}
