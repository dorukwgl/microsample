package com.doruk.application.users.service;

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
import com.doruk.application.security.PasswordEncoder;
import com.doruk.application.users.dto.*;
import com.doruk.infrastructure.config.AppConfig;
import com.doruk.infrastructure.persistence.users.repository.UserRepository;
import com.doruk.infrastructure.util.Constants;
import com.doruk.infrastructure.util.GenerateRandom;
import com.doruk.infrastructure.util.KeyNamespace;
import com.doruk.infrastructure.util.StringUtil;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.util.Map;

@Singleton
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final EventPublisher event;
    private final PasswordEncoder passwordEncoder;

    private void checkConflict(UserUniqueFields src, CreateUserCmd req) {
        if (req.email().equals(src.email()))
            throw new ConflictingArgumentException("Email address already taken");
        // its username, as only to samples are there
        throw new ConflictingArgumentException("Username already taken");
    }

    public UserResponseDto registerUser(CreateUserCmd userDto) {
        var existing = userRepo.findByUsernameOrEmail(userDto.username(), userDto.email());
        existing.ifPresent(src -> this.checkConflict(src, userDto));

        // create the user
        return userRepo.createUser(userDto, passwordEncoder.encode(userDto.password()));
    }

    public ProfileDto updateProfile(String userId, ProfileDto cmd) {
        return userRepo.updateProfile(userId, cmd);
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
