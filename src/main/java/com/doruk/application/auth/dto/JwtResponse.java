package com.doruk.application.auth.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record JwtResponse(String accessToken, String tokenType, int expiresIn) {
}
