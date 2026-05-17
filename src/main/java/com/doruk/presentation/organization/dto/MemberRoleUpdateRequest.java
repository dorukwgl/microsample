package com.doruk.presentation.organization.dto;

import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;

@Serdeable
@Schema(description = "Member role update request")
public record MemberRoleUpdateRequest(
        @Schema(description = "New admin status", example = "true")
        boolean isAdmin
) {
}