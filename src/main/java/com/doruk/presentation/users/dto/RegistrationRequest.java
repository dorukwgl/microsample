package com.doruk.presentation.users.dto;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.*;

@Serdeable
public record RegistrationRequest(
        @NotBlank
        String username,

        @NotBlank
        @Size(min = 8, max = 50, message = "Password must be 8 to 50 characters.")
        String password,

        @NotBlank
        @Email(message = "Invalid email format")
        String email,

        @Nullable
        @Pattern(
                regexp = "^\\+?[0-9]{1,4}[\\s-]?(\\([0-9]+\\)|[0-9])[0-9\\s-]*$",
                message = "Invalid phone number"
        )
        String phone
) {
}
