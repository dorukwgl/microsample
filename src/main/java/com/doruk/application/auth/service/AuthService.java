package com.doruk.application.auth.service;

import com.doruk.application.auth.dto.*;
import com.doruk.application.dto.EmailOtpDto;
import com.doruk.application.dto.SmsOtpDto;
import com.doruk.application.enums.TemplateType;
import com.doruk.application.exception.IncompleteStateException;
import com.doruk.application.exception.InvalidCredentialException;
import com.doruk.application.exception.TooManyAttemptsException;
import com.doruk.application.interfaces.EventPublisher;
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
import com.doruk.infrastructure.util.KeyNamespace;
import com.doruk.infrastructure.util.StringUtil;
import io.micronaut.context.annotation.Context;
import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
import nl.basjes.parse.useragent.UserAgentAnalyzer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.function.Function;

@Context
@RequiredArgsConstructor
public class AuthService {
    private final AppExecutors executor;
    private final JwtIssuer issuer;
    private final AppConfig appConfig;
    private final AuthRepository authRepo;
    private final PasswordEncoder hasher;
    private final MemoryStorage memoryStorage;
    private final EventPublisher eventPublisher;
    private final UserAgentAnalyzer uaa;
    private final PasswordEncoder passwordEncoder;
    private final MemoryStorage storage;
    private final AppConfig config;
    private final EventPublisher event;

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
                    authRepo.createSession(userId, sessionId, permissions, sessionExpiration,
                            deviceId, deviceInfo), executor);
            CompletableFuture.allOf(accessTokenFuture, refreshTokenFuture).join();

            return new Pair<>(sessionId, accessTokenFuture.resultNow());
        }
    }

    private Pair<Integer, LoginResponse> createMfaTransaction(AuthDto user) {
        var response = createMfaResponse(user);
        var mfaToken = response.mfaToken();
        var otp = GenerateRandom.generateOtp();
        var duration = Duration.ofSeconds(Constants.MFA_VALIDITY_SECONDS);
        memoryStorage.saveEx(KeyNamespace.mfaTransactionId(mfaToken), new MfaTransaction(otp, user.username()), duration);
        memoryStorage.saveEx(KeyNamespace.mfaOtpAttempt(mfaToken), 0, duration);
        return new Pair<>(otp, response);
    }

    private void removeMfaTransaction(String mfaToken) {
        memoryStorage.delete(KeyNamespace.mfaTransactionId(mfaToken));
        memoryStorage.delete(KeyNamespace.mfaOtpAttempt(mfaToken));
    }

    private LoginResponse initPhoneFactorAuth(AuthDto user) {
        var response = createMfaTransaction(user);

        eventPublisher.publish(new SmsOtpDto(
                user.id(),
                user.phone(),
                response.getKey(),
                TemplateType.MFA
        ));
        return response.getValue();
    }

    private LoginResponse initEmailFactorAuth(AuthDto user) {
        var response = createMfaTransaction(user);

        eventPublisher.publish(new EmailOtpDto(
                user.id(),
                null,
                response.getKey(),
                TemplateType.MFA
        ));
        return response.getValue();
    }

    private void createAndPublishEmailVerificationTransaction(String userId) {
        var otp = GenerateRandom.generateOtp();
        var tmpUrl = GenerateRandom.generateSessionId();
        var transactionId = GenerateRandom.generateTransactionId();
        var duration = Duration.ofSeconds(Constants.MAGIC_LINK_VALIDITY_SECONDS);

        var magicLink = StringUtil.generateUrl(config, tmpUrl);
        // create otp transaction
        var transaction = new EmailOtpDto(
                userId,
                magicLink,
                otp,
                TemplateType.EMAIL_VERIFICATION);

        var tid = KeyNamespace.verificationTransactionId(transactionId);
        storage.saveEx(tid, transaction, duration);
        storage.saveEx(KeyNamespace.verificationOtpAttempt(transactionId), 0, duration);

        // create magic link transaction
        storage.saveEx(KeyNamespace.verificationMagicId(tmpUrl), tid, duration);

        event.publish(transaction);
    }

    private void createAndPublishPhoneVerificationTransaction(String userId, String phone) {
        var otp = GenerateRandom.generateOtp();
        var transactionId = GenerateRandom.generateTransactionId();
        var duration = Duration.ofSeconds(Constants.OTP_VALIDITY_SECONDS);

        var transaction = new SmsOtpDto(
                userId,
                phone,
                otp,
                TemplateType.PHONE_VERIFICATION);

        var tid = KeyNamespace.verificationTransactionId(transactionId);
        storage.saveEx(tid, transaction, duration);
        storage.saveEx(KeyNamespace.verificationOtpAttempt(transactionId), 0, duration);

        event.publish(transaction);
    }

    private void removeVerificationTransaction(String transactionId) {
        storage.delete(KeyNamespace.verificationTransactionId(transactionId));
        storage.delete(KeyNamespace.verificationOtpAttempt(transactionId));
    }

    private String createAndPublishAuthUpdateTransaction(String userId, String payload, AuthUpdateTransaction.Type type) {
        var otp = GenerateRandom.generateOtp();
        var transactionId = GenerateRandom.generateTransactionId();
        var duration = Duration.ofSeconds(Constants.AUTH_UPDATE_VALIDITY_SECONDS);

        var txn = new AuthUpdateTransaction(
                transactionId,
                userId,
                otp,
                payload,
                type
        );

        storage.saveEx(KeyNamespace.updateAuthTransactionId(transactionId), txn, duration);
        storage.saveEx(KeyNamespace.updateAuthOtpAttempt(transactionId), 0, duration);

        event.publish(txn);

        return transactionId;
    }

    public LoginResponse performLogin(String identifier, String password, DeviceInfoObject deviceInfoObject) {
        var invalidCredentials = new InvalidCredentialException("Incorrect username/email or password.");

        var user = authRepo.findByUsernameOrEmail(identifier)
                .orElseThrow(() -> invalidCredentials);

        if (!hasher.matches(password, user.password()))
            throw invalidCredentials;

        // check for multi factor auth
        if (user.multiFactorAuth() != MultiAuthType.NONE)
            return this.authInitializers.get(user.multiFactorAuth()).apply(user);

        return createLoginResponse(deviceInfoObject.deviceId(), deviceInfoObject.deviceInfo(uaa), user);
    }

    public LoginResponse performMfa(String mfaToken, int otp, DeviceInfoObject deviceInfoObject) {
        var mfaTransaction = memoryStorage.get(mfaToken, MfaTransaction.class)
                .orElseThrow(() -> new InvalidCredentialException("Invalid or expired MFA session."));

        // increment the attempt
        var attempt = memoryStorage.increment(KeyNamespace.mfaOtpAttempt(mfaToken));
        if (attempt > Constants.MFA_ATTEMPT_LIMIT) {
            this.removeMfaTransaction(mfaToken);
            throw new TooManyAttemptsException("Too many attempts");
        }

        if (mfaTransaction.otp() != otp)
            throw new InvalidCredentialException("Invalid otp code");

        // create session
        var response = this.createLoginResponse(deviceInfoObject.deviceId(), deviceInfoObject.deviceInfo(uaa),
                authRepo.findByUsernameOrEmail(mfaTransaction.username()).orElseThrow());

        // remove the mfa transaction
        this.removeMfaTransaction(mfaToken);
        return response;
    }

    public JwtResponse refreshAccessToken(String sessionId) {
        var invalidException = new InvalidCredentialException("Invalid or expired session. Please login again.");
        var session = authRepo.getSession(sessionId)
                .orElseThrow(() -> invalidException);

        if (session.expiresAt().isBefore(LocalDateTime.now()))
            throw invalidException;

        return issuer.issueToken(new JwtRequest(
                session.userId(),
                appConfig.appId(),
                session.permissions()));
    }

    public List<SessionDto> listSessions(String userId) {
        return authRepo.getActiveDevices(userId);
    }

    public void logoutCurrent(String sessionId) {
        authRepo.deleteSession(sessionId);
    }

    public void logoutAll(String userId, boolean deleteBiometrics) {
        authRepo.deleteAllSessions(userId, deleteBiometrics);
    }

    public void logoutOthers(String userId, String sessionId, boolean deleteBiometrics) {
        authRepo.deleteOtherSessions(userId, sessionId, deleteBiometrics);
    }

    public void updatePassword(String userId, String password, String newPassword) {
        var currentPassword = authRepo.getUserPassword(userId);
        if (!passwordEncoder.matches(password, currentPassword))
            throw new InvalidCredentialException("Incorrect current password");

        authRepo.updatePassword(userId, passwordEncoder.encode(newPassword));
    }

    public void initEmailVerification(String userId) {
        this.createAndPublishEmailVerificationTransaction(userId);
    }

    // from otp, and from magic link
    public void verifyEmail(String userId, String transactionId, int otp) {
        var expiredException = new InvalidCredentialException("Invalid or expired verification session.");

        var transaction = storage.get(KeyNamespace.verificationTransactionId(transactionId),
                EmailOtpDto.class).orElseThrow(() -> expiredException);

        // check if the user id matches
        if (!transaction.id().equals(userId))
            throw expiredException;

        // finally proceed with otp matching and attempt counts.
        var attempt = storage.increment(KeyNamespace.verificationOtpAttempt(transactionId));
        if (attempt >= Constants.MFA_ATTEMPT_LIMIT) {
            this.removeVerificationTransaction(transactionId);
            throw new TooManyAttemptsException("Too many attempts");
        }

        // check if the otp matches
        if (transaction.otp() != otp)
            throw expiredException;

        // finally validate the user
        authRepo.verifyUserEmail(userId);
        this.removeVerificationTransaction(transactionId);
    }

    public void verifyEmail(String magicLinkPointer) {
        var expiredException = new InvalidCredentialException("Invalid or expired verification session.");
        var pointer = KeyNamespace.verificationMagicId(magicLinkPointer);
        // fetch the transaction id
        var transactionId = storage.get(pointer, String.class)
                .orElseThrow(() -> expiredException);

        // check if the transaction exists, in case of otp attempt limit
        var transaction = storage.get(transactionId,
                EmailOtpDto.class).orElseThrow(() -> expiredException);

        // finally validate the user
        authRepo.verifyUserEmail(transaction.id());
        this.removeVerificationTransaction(transactionId);
        storage.delete(pointer);
    }

    public void initPhoneVerification(String userId) {
        var phonePair = authRepo.getUserPhone(userId);
        var phone = phonePair.getKey();

        if (phone == null || phone.isBlank())
            throw new IncompleteStateException("Phone number not provided, please update your profile.");
        this.createAndPublishPhoneVerificationTransaction(userId, phone);
    }

    // from otp only
    public void verifyPhone(String transactionId, int otp) {
        var expiredException = new InvalidCredentialException("Invalid or expired otp session");
        var transaction = storage.get(transactionId,
                SmsOtpDto.class).orElseThrow(() -> expiredException);

        // check for attempts
        var attempt = storage.increment(KeyNamespace.verificationOtpAttempt(transactionId));
        if (attempt >= Constants.MFA_ATTEMPT_LIMIT) {
            this.removeVerificationTransaction(transactionId);
            throw new TooManyAttemptsException("Too many attempts");
        }

        // check otp
        if (transaction.otp() != otp)
            throw expiredException;

        // finally validate the user
        authRepo.verifyUserPhone(transaction.id());
        this.removeVerificationTransaction(transactionId);
    }

    // ~~~~~~~~~Update Email / Phone~~~~~~~~~
    public AuthUpdateResponse updateEmail(String userId, String email) {
        var current = authRepo.getUserEmail(userId);
        if (!current.getValue()) {
            authRepo.updateEmail(userId, email, true);
            return new AuthUpdateResponse(null, false,
                    "Email address updated, please proceed to verify it.");
        }

        var tid = createAndPublishAuthUpdateTransaction(userId, email, AuthUpdateTransaction.Type.EMAIL);

        return new AuthUpdateResponse(
                tid,
                true,
                "OTP is sent to your new email address, please enter the OTP to complete the process."
        );
    }

    public AuthUpdateResponse updatePhone(String userId, String phone) {
        var current = authRepo.getUserPhone(userId);
        if (!current.getValue()) {
            authRepo.updateEmail(userId, phone, true);
            return new AuthUpdateResponse(null, false,
                    "Phone Number updated, please proceed to verify it.");
        }

        var tid = createAndPublishAuthUpdateTransaction(userId, phone, AuthUpdateTransaction.Type.PHONE);

        return new AuthUpdateResponse(tid, true,
                "OTP is sent to your new phone number, please enter the OTP to complete the process.");
    }

    private void verifyAuthUpdateTransaction(String userId, String tid, int otp, AuthUpdateTransaction.Type type) {
        var invalidCredentials = new InvalidCredentialException("Expired or Invalid otp session");
        var txn = storage.get(KeyNamespace.updateAuthTransactionId(tid), AuthUpdateTransaction.class)
                .orElseThrow(() -> invalidCredentials);
        if (!(
                txn.type() == type &&
                        txn.userId().equalsIgnoreCase(userId)
        ))
            throw invalidCredentials;

        // check for attempts
        var attempts = storage.increment(KeyNamespace.updateAuthOtpAttempt(tid));
        if (attempts >= Constants.AUTH_UPDATE_ATTEMPT_LIMIT) {
            storage.delete(KeyNamespace.updateAuthTransactionId(tid));
            storage.delete(KeyNamespace.updateAuthOtpAttempt(tid));
            throw new TooManyAttemptsException("Too Many Attempts");
        }

        if (txn.otp() != otp)
            throw invalidCredentials;

        switch (type) {
            case EMAIL -> authRepo.updateEmail(txn.userId(), txn.payload(), true);
            case PHONE -> authRepo.updatePhone(txn.userId(), txn.payload(), true);
        }
    }

    public void verifyUpdatePhoneTransaction(String userId, String tid, int otp) {
        verifyAuthUpdateTransaction(userId, tid, otp, AuthUpdateTransaction.Type.PHONE);
    }

    public void verifyUpdateEmailTransaction(String userId, String tid, int otp) {
        verifyAuthUpdateTransaction(userId, tid, otp, AuthUpdateTransaction.Type.EMAIL);
    }
}