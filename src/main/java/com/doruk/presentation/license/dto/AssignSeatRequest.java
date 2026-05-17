package com.doruk.presentation.license.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Introspected
@Serdeable
public record AssignSeatRequest(
    @NotBlank UUID userId
) {
}
