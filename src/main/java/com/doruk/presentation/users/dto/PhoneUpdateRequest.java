package com.doruk.presentation.users.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Serdeable
public record PhoneUpdateRequest(
        @NotBlank
        @Pattern(
                regexp = "^\\+?\\d{6,14}$",
                message = "Invalid phone number"
        )
        String phone
) {
}
