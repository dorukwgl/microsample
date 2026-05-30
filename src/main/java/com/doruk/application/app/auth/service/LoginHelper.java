package com.doruk.application.app.auth.service;

import com.doruk.application.app.auth.dto.AuthDto;
import com.doruk.application.app.auth.dto.DeviceInfoObject;
import com.doruk.application.app.auth.dto.JwtRequest;
import com.doruk.application.app.auth.dto.JwtResponse;
import com.doruk.application.app.auth.dto.LoginResponse;
import com.doruk.domain.shared.enums.Permissions;
import com.doruk.infrastructure.config.AppConfig;
import com.doruk.infrastructure.persistence.auth.AuthRepository;
import com.doruk.infrastructure.security.JwtIssuer;
import com.doruk.infrastructure.util.GenerateRandom;
import com.doruk.infrastructure.util.Pair;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import nl.basjes.parse.useragent.UserAgentAnalyzer;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Singleton
@RequiredArgsConstructor
public class LoginHelper {
    private final JwtIssuer issuer;
    private final AppConfig appConfig;
    private final AuthRepository authRepo;
    private final UserAgentAnalyzer uaa;

    private Pair<String, JwtResponse> createSessionTokens(String userId, Set<Permissions> permissions, Optional<String> deviceId, Optional<String> deviceInfo) {
        var sessionId = GenerateRandom.generateSessionId();
        var sessionExpiration = OffsetDateTime.now().plusDays(appConfig.sessionExpiration());

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var accessTokenFuture = CompletableFuture.supplyAsync(() -> issuer.issueToken(
                    new JwtRequest(userId, appConfig.appId(), permissions)), executor);

            var refreshTokenFuture = CompletableFuture.runAsync(() ->
                    authRepo.createSession(userId, sessionId, permissions, sessionExpiration,
                            deviceId, deviceInfo), executor);
            CompletableFuture.allOf(accessTokenFuture, refreshTokenFuture).join();

            return new Pair<>(sessionId, accessTokenFuture.resultNow());
        }
    }

    public LoginResponse createLoginResponse(DeviceInfoObject deviceInfoObject, AuthDto user) {
        var deviceId = deviceInfoObject.deviceId();
        var deviceInfo = deviceInfoObject.deviceInfo(uaa);
        var fingerprintId = deviceInfoObject.fingerprintDeviceId();

        // sign jwt, and also create refresh token
        var tokens = this.createSessionTokens(user.id(), user.permissions(), deviceId, deviceInfo);

        // register device for future notification / fingerprint tracking
        deviceId.ifPresent(notifId ->
                authRepo.upsertUserDevice(user.id(), notifId,
                        fingerprintId.orElse(null),
                        deviceInfo.orElse(null)));

        return LoginResponse.builder()
                .accessToken(tokens.value().accessToken())
                .accessTokenType(tokens.value().tokenType())
                .accessTokenExpiresIn(tokens.value().expiresIn())
                .refreshToken(tokens.key())
                .isEmailVerified(user.emailVerified())
                .isPhoneVerified(user.phoneVerified())
                .mfaRequired(false)
                .build();
    }
}
