package com.doruk.infrastructure.apiclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record GoogleTokenInfoResponse(
        String iss,
        String sub,
        String aud,
        long exp,
        long iat,
        String email,
        @JsonProperty("email_verified") Boolean emailVerified,
        String name,
        String picture
) {}
