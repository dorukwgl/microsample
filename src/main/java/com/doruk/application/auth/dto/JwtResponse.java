package com.doruk.application.auth.dto;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record JwtResponse(String accessToken, String tokenType, int expiresIn) {
}
