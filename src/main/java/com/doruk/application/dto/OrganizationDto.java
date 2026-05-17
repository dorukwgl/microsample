package com.doruk.application.dto;

import com.doruk.domain.shared.enums.OrganizationType;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

import java.util.UUID;

@Serdeable
@Builder
public record OrganizationDto(
        UUID id,
        String name,
        String orgCode,
        OrganizationType type
) {
}
