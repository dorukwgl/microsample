package com.doruk.application.auth.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record MfaTransaction(int otp, String username) {
}
