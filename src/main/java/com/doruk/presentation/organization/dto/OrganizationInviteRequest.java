package com.doruk.presentation.organization.dto;

import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Serdeable
@Schema(description = "Organization invitation request")
public record OrganizationInviteRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Schema(description = "Email address to send invitation to", example = "user@example.com")
        String email
) {
}