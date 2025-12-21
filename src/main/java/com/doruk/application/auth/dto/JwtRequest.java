package com.doruk.application.auth.dto;

import com.doruk.domain.shared.enums.Permissions;
import io.micronaut.core.annotation.Introspected;

import java.util.List;

@Introspected
public record JwtRequest(String id, String audience, List<Permissions> permissions) {
}
