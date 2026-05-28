package com.doruk.presentation.auth.dto;

import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Serdeable
public record GoogleLoginRequest(
        @NotBlank
        @Schema(description = "Google ID token obtained from Google Sign-In client-side SDK")
        String token
) {}
