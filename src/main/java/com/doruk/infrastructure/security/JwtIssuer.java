package com.doruk.infrastructure.security;

import com.doruk.application.app.auth.dto.JwtResponse;
import com.doruk.application.exception.ApplicationException;
import com.doruk.infrastructure.apiclient.JwksClient;
import com.doruk.infrastructure.security.mappers.JwksRequestMapper;
import com.doruk.infrastructure.security.mappers.JwksTokenMapper;
import com.doruk.application.app.auth.dto.JwtRequest;
import io.micronaut.http.client.exceptions.HttpClientException;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class JwtIssuer {
    private final JwksClient jwksClient;
    private final JwksTokenMapper jwksTokenMapper;
    private final JwksRequestMapper jwksRequestMapper;

    public JwtResponse issueToken(JwtRequest request) {
        try {
            return jwksTokenMapper.toTokenResponse(
                    jwksClient.issueToken(jwksRequestMapper.toJwksRequest(request))
            );
        } catch (HttpClientException e) {
            throw new RuntimeException("Authentication service is currently unavailable. Please try again later.", e);
        }
    }
}
