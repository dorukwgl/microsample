package com.doruk.presentation.auth.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Introspected
public record MfaRequest(
        @Nullable
        @Size(max = 200, min = 32)
        @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
        String deviceId,

        @Nullable
        @Size(min = 3, max = 200)
        String deviceInfo,

        @Max(value = 999999, message = "OTP must be 6 digits")
        @Min(value = 100000, message = "OTP must be 6 digits")
        int otp
) {
}
