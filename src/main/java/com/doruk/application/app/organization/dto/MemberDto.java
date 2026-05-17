package com.doruk.application.app.organization.dto;

import com.doruk.domain.shared.enums.UserAccountStatus;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Serdeable
@Builder
public record MemberDto(
        UUID userId,
        String username,
        String email,
        boolean isOrgAdmin,
        OffsetDateTime joinedAt,
        UserAccountStatus status
) {}
