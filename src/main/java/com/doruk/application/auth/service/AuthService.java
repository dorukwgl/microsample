package com.doruk.application.auth.service;

import com.doruk.application.auth.dto.LoginResponse;
import com.doruk.application.exception.InvalidCredentialException;
import com.doruk.application.security.PasswordEncoder;
import com.doruk.infrastructure.config.AppConfig;
import com.doruk.infrastructure.config.AppExecutors;
import com.doruk.infrastructure.persistence.auth.AuthRepository;
import com.doruk.infrastructure.security.JwtIssuer;
import io.micronaut.context.annotation.Context;
import lombok.AllArgsConstructor;

import java.util.concurrent.CompletableFuture;

@Context
@AllArgsConstructor
public class AuthService {
    private final AppExecutors executor;
    private final JwtIssuer issuer;
    private final AppConfig appConfig;
    private final AuthRepository authRepository;
    private final PasswordEncoder hasher;

    public LoginResponse performLogin() {
        var invalidCredentials = new InvalidCredentialException("Incorrect username/email or password.");

        var user = authRepository.findByUsernameOrEmail("doruk")
                .orElseThrow(() -> invalidCredentials);

        if (!hasher.matches("", user.password()))
            throw invalidCredentials;

//        issuer.issueToken(new JwtRequest("doruk", appConfig.appId(), List.of(Permissions.UPDATE_OWN_PROFILE)))
//                .map(JwtResponse::accessToken);

        return null;
    }

    public String test() {
        var t = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "hello nice world";
        });
        t.join();

        System.out.println(Thread.currentThread().getName());
        return t.resultNow();
    }
}
