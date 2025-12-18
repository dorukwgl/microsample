package com.doruk.application.auth.service;

import com.doruk.application.auth.dto.JwtResponse;
import com.doruk.domain.shared.enums.Permissions;
import com.doruk.infrastructure.config.AppExecutors;
import com.doruk.infrastructure.security.JwtIssuer;
import com.doruk.application.auth.dto.JwtRequest;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@Singleton
@AllArgsConstructor
public class AuthService {
    private final AppExecutors executor;
    private final JwtIssuer issuer;

    public Mono<String> performLogin() {
        return issuer.issueToken(new JwtRequest("doruk", "name", List.of(Permissions.UPDATE_OWN_PROFILE)))
                .map(JwtResponse::accessToken);
    }
}
