package com.doruk.application.app.organization.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record OrganizationUpdateDto(String name) {}
