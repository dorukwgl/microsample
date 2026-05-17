package com.doruk.application.app.users.service;

import com.doruk.application.app.users.dto.*;
import com.doruk.application.dto.UploadedFile;
import com.doruk.application.events.ProfileImageUploadEvent;
import com.doruk.application.exception.ConflictingArgumentException;
import com.doruk.application.exception.InvalidInputException;
import com.doruk.application.interfaces.EventPublisher;
import com.doruk.application.interfaces.ObjectStorage;
import com.doruk.application.security.PasswordEncoder;
import com.doruk.domain.shared.enums.OrganizationType;
import com.doruk.infrastructure.persistence.users.UserRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Singleton
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final EventPublisher event;
    private final PasswordEncoder passwordEncoder;
    private final ObjectStorage storage;

    private void checkConflict(UserUniqueFields src, CreateUserCmd req) {
        if (req.email().equals(src.email()))
            throw new ConflictingArgumentException("Email address already taken");
        // its username, as only to samples are there
        throw new ConflictingArgumentException("Username already taken");
    }

    private void verifyOrgDetails(OrganizationType type, String orgCode, String orgName) {
        // Personal users - no org details needed, will use placeholder
        if (type == OrganizationType.PERSONAL && (orgName != null || orgCode != null))
            throw new InvalidInputException("orgName and orgCode should be null for PERSONAL accounts");

        // Enterprise users - require both orgName and orgCode
        if (type == OrganizationType.ENTERPRISE) {
            if (orgName == null || orgName.isBlank() || orgCode == null || orgCode.isBlank())
                throw new InvalidInputException("orgName and orgCode are required for ENTERPRISE accounts");

            if (userRepo.orgCodeExists(orgCode))
                throw new ConflictingArgumentException("Organization code already exists");
        }
    }

    public UserResponseDto registerUser(CreateUserCmd userDto) {
        var existing = userRepo.findByUsernameOrEmail(userDto.username(), userDto.email());
        existing.ifPresent(src -> this.checkConflict(src, userDto));

        this.verifyOrgDetails(userDto.type(), userDto.orgCode(), userDto.orgName());

        // create the user
        return userRepo.createUser(userDto, passwordEncoder.encode(userDto.password()));
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

    public CurrentUserDto getCurrentUser(String userId) {
        return userRepo.getCurrentUser(userId);
    }
}
