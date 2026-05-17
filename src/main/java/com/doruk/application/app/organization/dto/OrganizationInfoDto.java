package com.doruk.application.app.organization.dto;

import com.doruk.domain.shared.enums.OrganizationType;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Serdeable
@Builder
public record OrganizationInfoDto(
        UUID id,
        String name,
        String orgCode,
        OrganizationType type,
        OffsetDateTime createdAt,
        int memberCount
) {}
