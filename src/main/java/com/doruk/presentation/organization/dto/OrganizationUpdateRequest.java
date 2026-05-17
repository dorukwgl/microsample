package com.doruk.presentation.organization.dto;

import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Serdeable
@Schema(description = "Organization update request")
public record OrganizationUpdateRequest(
        @NotBlank(message = "Organization name is required")
        @Size(min = 3, max = 100, message = "Organization name must be between 3 and 100 characters")
        @Schema(description = "New organization name", example = "Acme Corporation")
        String name
) {
}