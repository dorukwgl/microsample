package com.doruk.application.users.service;

import com.doruk.application.users.dto.*;
import com.doruk.application.auth.dto.UploadedFileResult;
import com.doruk.application.dto.EmailOtpDto;
import com.doruk.application.dto.SmsOtpDto;
import com.doruk.application.enums.TemplateType;
import com.doruk.application.events.ProfileImageUpload;
import com.doruk.application.exception.ConflictingArgumentException;
import com.doruk.application.exception.IncompleteStateException;
import com.doruk.application.exception.InvalidCredentialException;
import com.doruk.application.exception.TooManyAttemptsException;
import com.doruk.application.interfaces.EventPublisher;
import com.doruk.application.interfaces.MemoryStorage;
import com.doruk.infrastructure.config.AppConfig;
import com.doruk.infrastructure.persistence.users.repository.UserRepository;
import com.doruk.infrastructure.util.Constants;
import com.doruk.infrastructure.util.GenerateRandom;
import com.doruk.infrastructure.util.KeyNamespace;
import com.doruk.infrastructure.util.StringUtil;
import io.micronaut.serde.jackson.SerdeJacksonConfiguration;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.util.Map;

@Singleton
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final EventPublisher event;
    private final MemoryStorage storage;
    private final AppConfig config;
    private final SerdeJacksonConfiguration serdeJacksonConfiguration;

    private void checkConflict(UserUniqueFields src, CreateUserCmd req) {
        if (req.email().equals(src.email()))
            throw new ConflictingArgumentException("Email address already taken");
        // its username, as only to samples are there
        throw new ConflictingArgumentException("Username already taken");
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

    public UserResponseDto registerUser(CreateUserCmd userDto) {
        var existing = userRepo.findByUsernameOrEmail(userDto.username(), userDto.email());
        existing.ifPresent(src -> this.checkConflict(src, userDto));

        // create the user
        var usr = userRepo.createUser(userDto);

        // send email verification otp
        this.createAndPublishEmailVerificationTransaction(usr.id().toString());
        return usr;
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
        userRepo.verifyUserEmail(userId);
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
        userRepo.verifyUserEmail(transaction.id());
        this.removeVerificationTransaction(transactionId);
        storage.delete(pointer);
    }

    public void initPhoneVerification(String userId) {
        var phone = userRepo.getPhoneNumber(userId);
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
        userRepo.verifyUserPhone(transaction.id());
        this.removeVerificationTransaction(transactionId);
    }

    public ProfileDto updateProfile(String userId, ProfileDto cmd) {
        return userRepo.updateProfile(userId, cmd);
    }

    public void updatePhoneNumber(String userId, String phone) {
        userRepo.updatePhoneNumber(userId, phone);
        initPhoneVerification(userId);
    }

    public void updateEmail(String userId, String email) {
        userRepo.updateEmailAddress(userId, email);
        initEmailVerification(userId);
    }

    public Map<String, String> updateProfileIcon(String userId, UploadedFileResult icon) {
        var previous = userRepo.updateProfileIconReturningOld(userId, icon.storedName());

        event.publish(new ProfileImageUpload(icon, previous));

        return Map.of("newProfilePicture", icon.storedName());
    }

    public CurrentUserDto getCurrentUser(String userId) {
        return userRepo.getCurrentUser(userId);
    }
}
