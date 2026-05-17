package com.doruk.application.app.users.dto;

import com.doruk.domain.shared.enums.OrganizationType;
import io.micronaut.core.annotation.Introspected;

import java.util.Locale;

@Introspected
public record CreateUserCmd(
        String username,
        String password,
        String email,
        String phone,
        String orgCode,
        String orgName,
        OrganizationType type
) {
    public CreateUserCmd {
        username = username.toLowerCase(Locale.ROOT);
        email = email.toLowerCase(Locale.ROOT);
    }
}
