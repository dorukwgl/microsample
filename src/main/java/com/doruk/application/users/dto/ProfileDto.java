package com.doruk.application.users.dto;

import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;

@Serdeable
public record ProfileDto(
        String userId,
        String fullName,
        String address,
        String city,
        String state,
        String country,
        String postalCode,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
