package com.doruk.application.app.users.dto;

import io.micronaut.core.annotation.Introspected;

import java.util.Locale;

@Introspected
public record CreateUserCmd(
        String username,
        String password,
        String email,
        String phone
) {
    public CreateUserCmd {
        username = username != null ? username.toLowerCase(Locale.ROOT) : null;
        email = email.toLowerCase(Locale.ROOT);
    }
}
