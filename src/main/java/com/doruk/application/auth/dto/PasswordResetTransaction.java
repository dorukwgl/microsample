package com.doruk.application.auth.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record PasswordResetTransaction(
        String userId,
        int otp
) {
}
