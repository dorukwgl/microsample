package com.doruk.application.app.auth.dto;

public record GoogleClaims(
        String sub,
        String email,
        String name,
        boolean emailVerified
) {}
