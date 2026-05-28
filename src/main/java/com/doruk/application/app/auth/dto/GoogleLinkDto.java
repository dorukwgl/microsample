package com.doruk.application.app.auth.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record GoogleLinkDto(
        UUID userId,
        String googleSub,
        String email
) {
}
