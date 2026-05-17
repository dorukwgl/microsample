package com.doruk.application.app.license.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

import java.time.OffsetDateTime;

@Serdeable
@Builder
public record SeatDto(
    String userId,
    String username,
    String email,
    OffsetDateTime assignedAt,
    String assignedBy
) {
}
