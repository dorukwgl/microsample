package com.doruk.application.auth.dto;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record MfaTransaction(int otp, String username) {
}
