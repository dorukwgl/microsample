package com.doruk.application.app.organization.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

@Serdeable
@Builder
public record OrganizationInviteResponseDto(
        boolean success,
        String message,
        String inviteLink
) {}
