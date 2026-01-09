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
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nl.basjes.parse.useragent.UserAgentAnalyzer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.function.Function;

@Context
@RequiredArgsConstructor
public class AuthService {
    @Builder
    private record TransactionRecord(
            String transactionId,
            String tid,
            String magicLink,
            int otp
    ) {
    }

    ;

    private final AppExecutors executor;
    private final JwtIssuer issuer;
    private final AppConfig appConfig;
    private final AuthRepository authRepo;
    private final PasswordEncoder hasher;
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

    private LoginResponse createMfaResponse(AuthDto user, String mfaToken) {
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

    private TransactionRecord createOtpExTransaction(String prefix, Duration duration, boolean createMagic) {
        // should return transactionId, tid separately (tid = internal id, sent to user, transactionId = full redis id)
        var cooldownDuration = Duration.ofSeconds(Constants.RESEND_OTP_COOLDOWN_SECONDS);
        var otp = GenerateRandom.generateOtp();
        var tid = GenerateRandom.generateTransactionId();

        var transactionId = KeyNamespace.getNamespacedId(prefix, tid);

        // create attempts, cooldown
        storage.saveEx(KeyNamespace.cooldownPrefix(prefix, tid), Boolean.TRUE, cooldownDuration);
        storage.saveEx(KeyNamespace.attemptPrefix(prefix, tid), 0, duration);

        var transactionRecord = TransactionRecord.builder()
                .transactionId(transactionId)
                .tid(tid)
                .otp(otp);

        if (!createMagic)
            return transactionRecord.build();

        var magicSuffix = GenerateRandom.generateSessionId();
        var magicLink = StringUtil.generateUrl(config, magicSuffix);

        // save magic link
        storage.saveEx(KeyNamespace.magicLinkPrefix(prefix, magicSuffix), transactionId, duration);

        return transactionRecord
                .magicLink(magicLink)
                .build();
    }

    private void deleteOtpExTransaction(String prefix, String tid) {
        storage.delete(KeyNamespace.getNamespacedId(prefix, tid)); // main transaction object
        storage.delete(KeyNamespace.cooldownPrefix(prefix, tid));
        storage.delete(KeyNamespace.attemptPrefix(prefix, tid));
    }

    private void deleteOtpExTransactionFromMagic(String prefix, String magicSuffix, String transactionId) {
        storage.delete(KeyNamespace.getNamespacedId(prefix, magicSuffix)); // magic link
        this.deleteOtpExTransaction(prefix, KeyNamespace.extractTid(prefix, transactionId)); // main transaction object
    }

    private void publishSmsOtpEvent(String userId, String phone, int otp, TemplateType template) {
        eventPublisher.publish(new SmsOtpDto(
                userId,
                phone,
                otp,
                template
        ));
    }

    private void publishEmailOtpEvent(String userId, String magicLink, int otp, TemplateType template) {
        eventPublisher.publish(new EmailOtpDto(
                userId,
                magicLink,
                otp,
                template
        ));
    }

    private Pair<Integer, LoginResponse> createMfaTransaction(AuthDto user) {
        var duration = Duration.ofSeconds(Constants.MFA_VALIDITY_SECONDS);
        var record = this.createOtpExTransaction(KeyNamespace.mfaTransactionPrefix(), duration, false);

        var response = createMfaResponse(user, record.tid());

        storage.saveEx(record.transactionId(), new MfaTransaction(record.otp(), user.username()), duration);
        return new Pair<>(record.otp(), response);
    }

    private LoginResponse initPhoneFactorAuth(AuthDto user) {
        var response = createMfaTransaction(user);
        this.publishSmsOtpEvent(user.id(), user.phone(), response.getKey(), TemplateType.MFA);
        return response.getValue();
    }

    private LoginResponse initEmailFactorAuth(AuthDto user) {
        var response = createMfaTransaction(user);
        this.publishEmailOtpEvent(user.id(), null, response.getKey(), TemplateType.MFA);
        return response.getValue();
    }

    private String createAndPublishEmailVerificationTransaction(String userId) {
        var duration = Duration.ofSeconds(Constants.MAGIC_LINK_VALIDITY_SECONDS);
        var record = this.createOtpExTransaction(
                KeyNamespace.verificationTransaction(), duration, true);

        // create otp transaction
        var transaction = new EmailOtpDto(
                userId,
                null,
                record.otp(),
                null);

        storage.saveEx(record.transactionId(), transaction, duration);

        this.publishEmailOtpEvent(userId, record.magicLink(), record.otp(), TemplateType.EMAIL_VERIFICATION);

        return record.tid();
    }

    private String createAndPublishPhoneVerificationTransaction(String userId, String phone) {
        var duration = Duration.ofSeconds(Constants.OTP_VALIDITY_SECONDS);
        var record = this.createOtpExTransaction(KeyNamespace.verificationTransaction(), duration, false);

        var transaction = new SmsOtpDto(
                userId,
                null,
                0,
                null);

        storage.saveEx(record.transactionId(), transaction, duration);

        this.publishSmsOtpEvent(userId, phone, record.otp, TemplateType.PHONE_VERIFICATION);

        return record.tid();
    }

    private String createAndPublishAuthUpdateTransaction(String userId, String payload, AuthUpdateTransaction.Type type) {
        var duration = Duration.ofSeconds(Constants.AUTH_UPDATE_VALIDITY_SECONDS);
        var record = this.createOtpExTransaction(KeyNamespace.updateAuthTransaction(), duration, false);

        var txn = new AuthUpdateTransaction(
                record.tid(),
                userId,
                record.otp(),
                payload,
                type
        );

        storage.saveEx(record.transactionId(), txn, duration);
        event.publish(txn);

        return record.tid();
    }

    private void verifyAuthUpdateTransaction(String userId, String tid, int otp, AuthUpdateTransaction.Type type) {
        var invalidCredentials = new InvalidCredentialException("Expired or Invalid otp session");
        var prefix = KeyNamespace.updateAuthTransaction();
        var txn = storage.get(KeyNamespace.getNamespacedId(prefix, tid), AuthUpdateTransaction.class)
                .orElseThrow(() -> invalidCredentials);
        if (!(
                txn.type() == type &&
                        txn.userId().equalsIgnoreCase(userId)
        ))
            throw invalidCredentials;

        // check for attempts
        var attempts = storage.increment(KeyNamespace.attemptPrefix(prefix, tid));
        if (attempts >= Constants.AUTH_UPDATE_ATTEMPT_LIMIT) {
            this.deleteOtpExTransaction(prefix, tid);
            throw new TooManyAttemptsException("Too Many Attempts");
        }

        if (txn.otp() != otp)
            throw invalidCredentials;

        switch (type) {
            case EMAIL -> authRepo.updateEmail(txn.userId(), txn.payload(), true);
            case PHONE -> authRepo.updatePhone(txn.userId(), txn.payload(), true);
        }
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
        String prefix = KeyNamespace.mfaTransactionPrefix();
        var mfaTransaction = storage.get(KeyNamespace.getNamespacedId(prefix, mfaToken), MfaTransaction.class)
                .orElseThrow(() -> new InvalidCredentialException("Invalid or expired MFA session."));

        // increment the attempt
        var attempt = storage.increment(KeyNamespace.attemptPrefix(prefix, mfaToken));
        if (attempt > Constants.MFA_ATTEMPT_LIMIT) {
            this.deleteOtpExTransaction(prefix, mfaToken);
            throw new TooManyAttemptsException("Too many attempts");
        }

        if (mfaTransaction.otp() != otp)
            throw new InvalidCredentialException("Invalid otp code");

        // create session
        var response = this.createLoginResponse(deviceInfoObject.deviceId(), deviceInfoObject.deviceInfo(uaa),
                authRepo.findByUsernameOrEmail(mfaTransaction.username()).orElseThrow());

        // remove the mfa transaction
        this.deleteOtpExTransaction(prefix, mfaToken);
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

    public Map<String, String> initEmailVerification(String userId) {
        var tid = this.createAndPublishEmailVerificationTransaction(userId);
        return Map.of("tid", tid, "message", "OTP is sent to your email address");
    }

    // from otp, and from magic link
    public void verifyEmail(String userId, String tid, int otp) {
        var expiredException = new InvalidCredentialException("Invalid or expired verification session.");
        var prefix = KeyNamespace.verificationTransaction();

        var transaction = storage.get(KeyNamespace.getNamespacedId(prefix, tid),
                EmailOtpDto.class).orElseThrow(() -> expiredException);

        // check if the user id matches
        if (!transaction.id().equals(userId))
            throw expiredException;

        // finally proceed with otp matching and attempt counts.
        var attempt = storage.increment(KeyNamespace.attemptPrefix(prefix, tid));
        if (attempt >= Constants.MFA_ATTEMPT_LIMIT) {
            this.deleteOtpExTransaction(prefix, tid);
            throw new TooManyAttemptsException("Too many attempts");
        }
        // check if the otp matches
        if (transaction.otp() != otp)
            throw expiredException;

        // finally validate the user
        authRepo.verifyUserEmail(userId);
        this.deleteOtpExTransaction(prefix, tid);
    }

    public void verifyEmail(String magicLinkPointer) {
        var expiredException = new InvalidCredentialException("Invalid or expired verification session.");
        var prefix = KeyNamespace.verificationTransaction();
        var pointer = KeyNamespace.magicLinkPrefix(prefix, magicLinkPointer);
        // fetch the transaction id
        var transactionId = storage.get(pointer, String.class)
                .orElseThrow(() -> expiredException);

        // check if the transaction exists, in case of otp attempt limit
        var transaction = storage.get(transactionId,
                EmailOtpDto.class).orElseThrow(() -> {
            // delete the pointer
            storage.delete(pointer);
            return expiredException;
        });

        // finally validate the user
        authRepo.verifyUserEmail(transaction.id());
        this.deleteOtpExTransactionFromMagic(prefix, magicLinkPointer, transactionId);
    }

    public Map<String, String> initPhoneVerification(String userId) {
        var phonePair = authRepo.getUserPhone(userId);
        var phone = phonePair.getKey();

        if (phone == null || phone.isBlank())
            throw new IncompleteStateException("Phone number not provided, please update your profile.");
        var tid = this.createAndPublishPhoneVerificationTransaction(userId, phone);
        return Map.of("tid", tid, "message", "OTP is sent to your phone number");
    }

    // from otp only
    public void verifyPhone(String tid, int otp) {
        var expiredException = new InvalidCredentialException("Invalid or expired otp session");
        var prefix = KeyNamespace.verificationTransaction();
        var transaction = storage.get(KeyNamespace.getNamespacedId(prefix, tid),
                SmsOtpDto.class).orElseThrow(() -> expiredException);

        // check for attempts
        var attempt = storage.increment(KeyNamespace.attemptPrefix(prefix, tid));
        if (attempt >= Constants.MFA_ATTEMPT_LIMIT) {
            this.deleteOtpExTransaction(prefix, tid);
            throw new TooManyAttemptsException("Too many attempts");
        }

        // check otp
        if (transaction.otp() != otp)
            throw expiredException;

        // finally validate the user
        authRepo.verifyUserPhone(transaction.id());
        this.deleteOtpExTransaction(prefix, tid);
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

    public void verifyUpdatePhoneTransaction(String userId, String tid, int otp) {
        verifyAuthUpdateTransaction(userId, tid, otp, AuthUpdateTransaction.Type.PHONE);
    }

    public void verifyUpdateEmailTransaction(String userId, String tid, int otp) {
        verifyAuthUpdateTransaction(userId, tid, otp, AuthUpdateTransaction.Type.EMAIL);
    }

    private String createAndPublishPasswordResetTransaction(String userId, String phone, boolean isPhone) {
        var duration = Duration.ofSeconds(Constants.PW_RESET_VALIDITY_SECONDS);
        var prefix = KeyNamespace.resetPasswordTransaction();

        var record = this.createOtpExTransaction(prefix, duration, !isPhone);
        var txn = new PasswordResetTransaction(userId, record.otp());

        // store in redis
        storage.saveEx(record.transactionId(), txn, duration);

        if (isPhone)
            this.publishSmsOtpEvent(userId, phone, record.otp(), TemplateType.PASSWORD_RESET);
        else
            this.publishEmailOtpEvent(userId, record.magicLink(), record.otp(), TemplateType.PASSWORD_RESET);

        return record.tid();
    }

    public Map<String, String> initPasswordReset(String identifier, boolean usePhone) {
        Map<String, String> successMsg = new HashMap<>();
        successMsg.put("message", "OTP sent to your " + (usePhone ? "phone number" : "email address"));

        var userOpt = authRepo.findByUsernameOrEmail(identifier);
        if (userOpt.isEmpty())
            return successMsg;
        var user = userOpt.get();

        if (usePhone && user.phone() == null)
            return successMsg;

        var tid = this.createAndPublishPasswordResetTransaction(user.id(), user.phone(), usePhone);

        successMsg.put("tid", tid);
        return successMsg;
    }

    public void verifyAndResetPasswordOtp(String tid, int otp, String password) {
        var invalidException = new InvalidCredentialException("Invalid or Expired otp session");
        var prefix = KeyNamespace.resetPasswordTransaction();

        var txn = storage.get(KeyNamespace.getNamespacedId(prefix, tid), PasswordResetTransaction.class)
                .orElseThrow(() -> invalidException);

        var attempts = storage.increment(KeyNamespace.attemptPrefix(prefix, tid));
        if (attempts >= Constants.PW_UPDATE_OTP_ATTEMPT_LIMIT) {
            this.deleteOtpExTransaction(prefix, tid);
            throw new TooManyAttemptsException("Too many attempts");
        }

        if (txn.otp() != otp)
            throw invalidException;

        // update the password
        authRepo.updatePassword(txn.userId(), passwordEncoder.encode(password));

        // delete txn
        this.deleteOtpExTransaction(prefix, tid);
    }

    public void verifyAndResetPasswordMagic(String magicLink, String password) {
        var invalidException = new InvalidCredentialException("Invalid or Expired otp session");
        var  prefix = KeyNamespace.resetPasswordTransaction();

        var reference = storage.get(KeyNamespace.magicLinkPrefix(prefix, magicLink), String.class)
                .orElseThrow(() -> invalidException);

        var txn = storage.get(reference, PasswordResetTransaction.class)
                .orElseThrow(() -> {
                    storage.delete(reference);
                    return invalidException;
                });

        // if txn exists, update the password now
        authRepo.updatePassword(txn.userId(), passwordEncoder.encode(password));

        this.deleteOtpExTransactionFromMagic(prefix, magicLink, reference);
    }
}