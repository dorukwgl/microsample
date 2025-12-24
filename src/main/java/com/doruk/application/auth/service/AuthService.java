package com.doruk.application.auth.service;

import com.doruk.application.auth.dto.AuthDto;
import com.doruk.application.auth.dto.JwtRequest;
import com.doruk.application.auth.dto.JwtResponse;
import com.doruk.application.auth.dto.LoginResponse;
import com.doruk.application.exception.InvalidCredentialException;
import com.doruk.application.interfaces.MemoryStorage;
import com.doruk.application.security.PasswordEncoder;
import com.doruk.domain.shared.enums.MultiAuthType;
import com.doruk.domain.shared.enums.Permissions;
import com.doruk.infrastructure.config.AppConfig;
import com.doruk.infrastructure.config.AppExecutors;
import com.doruk.infrastructure.persistence.auth.AuthRepository;
import com.doruk.infrastructure.security.JwtIssuer;
import com.doruk.infrastructure.util.Constants;
import com.doruk.infrastructure.util.GenerateRandom;
import io.micronaut.context.annotation.Context;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.function.Function;

@Context
@AllArgsConstructor
public class AuthService {
    private final AppExecutors executor;
    private final JwtIssuer issuer;
    private final AppConfig appConfig;
    private final AuthRepository authRepository;
    private final PasswordEncoder hasher;
    private final String mfaAttempt = ":attempt";
    private final MemoryStorage memoryStorage;

    private final Map<MultiAuthType, Function<AuthDto, LoginResponse>> authInitializers = Map.of(
            MultiAuthType.PHONE, this::initPhoneFactorAuth,
            MultiAuthType.EMAIL, this::initEmailFactorAuth
    );

    private LoginResponse createMfaResponse(AuthDto user) {
        String mfaToken = GenerateRandom.generateMfaToken();

        return LoginResponse.builder()
                .isEmailVerified(user.emailVerified())
                .isPhoneVerified(user.phoneVerified())
                .mfaRequired(true)
                .mfaType(user.multiFactorAuth())
                .mfaToken(mfaToken)
                .mfaExpiresIn(Constants.MFA_VALIDITY_SECONDS)
                .build();
    }

    private LoginResponse createLoginResponse(Optional<String> deviceId, Optional<String> deviceInfo, AuthDto user) {
        // sign jwt, and also create refresh token
        var tokens = this.createSessionTokens(user.id(), user.permissions(), deviceId, deviceInfo);
        return LoginResponse.builder()
                .accessToken(tokens.getValue().accessToken())
                .accessTokenType(tokens.getValue().tokenType())
                .accessTokenExpiresIn(tokens.getValue().expiresIn())
                .refreshToken(tokens.getKey())
                .isEmailVerified(user.emailVerified())
                .isPhoneVerified(user.phoneVerified())
                .mfaRequired(false)
                .build();
    }

    private Pair<String, JwtResponse> createSessionTokens(String userId, Set<Permissions> permissions, Optional<String> deviceId, Optional<String> deviceInfo) {
        var sessionId = GenerateRandom.generateSessionId();
        var sessionExpiration = LocalDateTime.now().plusDays(appConfig.sessionExpiration());

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var accessTokenFuture = CompletableFuture.supplyAsync(() -> issuer.issueToken(
                    new JwtRequest(userId, appConfig.appId(), permissions)), executor);

            var refreshTokenFuture = CompletableFuture.runAsync(() ->
                    authRepository.createSession(userId, sessionId, permissions, sessionExpiration,
                            deviceId, deviceInfo), executor);
            CompletableFuture.allOf(accessTokenFuture, refreshTokenFuture).join();

            return new Pair<>(sessionId, accessTokenFuture.resultNow());
        }
    }

    private LoginResponse createMfaTransaction(AuthDto user) {
        var response = createMfaResponse(user);
        var mfaToken = response.mfaToken();
        var otp = GenerateRandom.generateOtp();
        var duration = Duration.ofSeconds(Constants.MFA_VALIDITY_SECONDS);
        memoryStorage.saveEx(mfaToken, otp, duration);
        memoryStorage.saveEx(mfaToken + mfaAttempt, 0, duration);
        return response;
    }

    private LoginResponse initPhoneFactorAuth(AuthDto user) {
        // generate a random otp
        // create redis entry for the otp, mfa token, expires in, attempt count
        // send it to the user
        // return the response
        var response = createMfaTransaction(user);
        // extract method, createMfaTransaction
        return response;
    }

    private LoginResponse initEmailFactorAuth(AuthDto user) {
        // generate a random otp
        // create redis entry for the otp, mfa token, expires in, attempt count
        // send it to the user
        // return the response
        var response = createMfaTransaction(user);

        return response;
    }

    public LoginResponse performLogin(String identifier, String password, Optional<String> deviceId, Optional<String> deviceInfo) {
        var invalidCredentials = new InvalidCredentialException("Incorrect username/email or password.");

        var user = authRepository.findByUsernameOrEmail(identifier)
                .orElseThrow(() -> invalidCredentials);

        if (!hasher.matches(password, user.password()))
            throw invalidCredentials;

        // check for multi factor auth
        if (user.multiFactorAuth() != MultiAuthType.NONE)
            return this.authInitializers.get(user.multiFactorAuth()).apply(user);

        return createLoginResponse(deviceId, deviceInfo, user);
    }

}
