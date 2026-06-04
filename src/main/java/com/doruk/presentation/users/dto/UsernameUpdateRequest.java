package com.doruk.presentation.users.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Serdeable
public record UsernameUpdateRequest(
        @NotBlank
        @Size(min = 3, max = 30, message = "Username must be between 3 to 30 letters")
        String username
) {
}
