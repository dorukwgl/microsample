package com.doruk.application.app.users.service;

import com.doruk.application.app.auth.dto.OtpTransaction;
import com.doruk.application.app.users.dto.*;
import com.doruk.application.dto.UploadedFile;
import com.doruk.application.enums.OtpChannel;
import com.doruk.application.enums.TemplateType;
import com.doruk.application.events.OtpDeliveryEvent;
import com.doruk.application.events.ProfileImageUploadEvent;
import com.doruk.application.exception.ConflictingArgumentException;
import com.doruk.application.interfaces.EventPublisher;
import com.doruk.application.interfaces.MemoryStorage;
import com.doruk.application.interfaces.ObjectStorage;
import com.doruk.application.security.PasswordEncoder;
import com.doruk.infrastructure.config.AppConfig;
import com.doruk.infrastructure.persistence.users.UserRepository;
import com.doruk.infrastructure.util.Constants;
import com.doruk.infrastructure.util.GenerateRandom;
import com.doruk.infrastructure.util.KeyNamespace;
import com.doruk.infrastructure.util.StringUtil;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.util.Locale;
import java.util.Map;

@Singleton
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final EventPublisher event;
    private final PasswordEncoder passwordEncoder;
    private final ObjectStorage storage;
    private final MemoryStorage memoryStorage;
    private final AppConfig appConfig;

    private void checkConflict(UserUniqueFields src, CreateUserCmd req) {
        if (req.email().equals(src.email()))
            throw new ConflictingArgumentException("Email address already taken");
        throw new ConflictingArgumentException("Username already taken");
    }

    private void sendVerificationEmail(String userId, String email) {
        var duration = Duration.ofSeconds(Constants.MAGIC_LINK_VALIDITY_SECONDS);
        var prefix = KeyNamespace.verificationTransaction();
        var otp = GenerateRandom.generateOtp();
        var tid = GenerateRandom.generateTransactionId();
        var transactionId = KeyNamespace.getNamespacedId(prefix, tid);

        var txn = OtpTransaction.builder()
                .userId(userId)
                .target(email)
                .channel(OtpChannel.EMAIL)
                .otp(otp)
                .build();

        memoryStorage.saveEx(transactionId, txn, duration);
        memoryStorage.saveEx(KeyNamespace.cooldownPrefix(prefix, tid), Boolean.TRUE,
                Duration.ofSeconds(Constants.RESEND_OTP_COOLDOWN_SECONDS));
        memoryStorage.saveEx(KeyNamespace.attemptPrefix(prefix, tid), 0, duration);

        var magicSuffix = GenerateRandom.generateSessionId();
        var magicLink = StringUtil.generateUrl(appConfig, magicSuffix);
        memoryStorage.saveEx(KeyNamespace.magicLinkPrefix(prefix, magicSuffix), transactionId, duration);

        event.publish(OtpDeliveryEvent.builder()
                .to(email)
                .channel(OtpChannel.EMAIL)
                .otp(otp)
                .magicLink(magicLink)
                .contentTemplate(TemplateType.EMAIL_VERIFICATION)
                .build());
    }

    public UserResponseDto registerUser(CreateUserCmd userDto) {
        var existing = userRepo.findByUsernameOrEmail(userDto.username(), userDto.email());
        existing.ifPresent(src -> this.checkConflict(src, userDto));

        var user = userRepo.createUser(userDto, passwordEncoder.encode(userDto.password()));

        sendVerificationEmail(user.id().toString(), user.email());

        return user;
    }

    public ProfileDto updateProfile(String userId, ProfileDto cmd) {
        return userRepo.updateProfile(userId, cmd);
    }

    public Map<String, String> updateProfileIcon(String userId, UploadedFile icon) {
        var previous = userRepo.updateProfileIconReturningOld(userId, icon);
        var stored = icon.storedObject();

        event.publish(new ProfileImageUploadEvent(
                stored.objectKey(),
                stored.mimeType(),
                previous
        ));

        return Map.of("newProfilePicture", storage.resolveUrl(stored));
    }

    public UserResponseDto updateUsername(String userId, String newUsername) {
        var normalized = newUsername.toLowerCase(Locale.ROOT);
        if (userRepo.existsByUsername(normalized, userId))
            throw new ConflictingArgumentException("Username already taken");

        return userRepo.updateUsername(userId, normalized);
    }

    public UserResponseDto updatePhone(String userId, String newPhone) {
        return userRepo.updatePhone(userId, newPhone);
    }

    public CurrentUserDto getCurrentUser(String userId) {
        return userRepo.getCurrentUser(userId);
    }
}
