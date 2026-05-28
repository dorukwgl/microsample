package com.doruk.infrastructure.security;

import com.doruk.application.app.auth.dto.GoogleClaims;
import com.doruk.application.exception.InvalidCredentialException;
import com.doruk.infrastructure.apiclient.GoogleApiClient;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;

@Singleton
public class GoogleTokenVerifier {

    private static final String EXPECTED_ISSUER = "https://accounts.google.com";

    private final GoogleApiClient googleApi;
    private final String expectedAudience;

    public GoogleTokenVerifier(GoogleApiClient googleApi,
                               @Value("${micronaut.security.oauth2.clients.google.client-id}") String expectedAudience) {
        this.googleApi = googleApi;
        this.expectedAudience = expectedAudience;
    }

    public GoogleClaims verify(String idToken) {
        var info = googleApi.verifyToken(idToken);

        if (!EXPECTED_ISSUER.equals(info.iss()))
            throw new InvalidCredentialException("Invalid token issuer");

        if (!expectedAudience.equals(info.aud()))
            throw new InvalidCredentialException("Invalid token audience");

        if (info.sub() == null || info.sub().isBlank())
            throw new InvalidCredentialException("Missing subject in Google token");

        if (info.email() == null || info.email().isBlank())
            throw new InvalidCredentialException("Missing email in Google token");

        return new GoogleClaims(
                info.sub(),
                info.email(),
                info.name(),
                Boolean.TRUE.equals(info.emailVerified())
        );
    }
}
