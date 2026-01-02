package com.doruk.application.auth.dto;

import com.doruk.domain.shared.enums.Permissions;
import io.micronaut.core.annotation.Introspected;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Introspected
@Builder
public record SessionDto(String id, String userId, String sessionId, String deviceInfo, String deviceId, LocalDateTime expiresAt,
                         Set<Permissions> permissions) {
}
