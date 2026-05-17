package com.doruk.application.app.organization.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record OrganizationInviteDto(String email, String inviterName) {}
