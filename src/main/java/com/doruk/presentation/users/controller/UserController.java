package com.doruk.presentation.users.controller;

import com.doruk.application.app.users.dto.CurrentUserDto;
import com.doruk.application.app.users.dto.ProfileDto;
import com.doruk.application.app.users.dto.UserResponseDto;
import com.doruk.application.app.users.service.UserService;
import com.doruk.application.files.FileService;
import com.doruk.infrastructure.annotataions.Routes;
import io.micronaut.http.annotation.Controller;
import com.doruk.presentation.users.dto.ProfileUpdateRequest;
import com.doruk.presentation.users.dto.RegistrationRequest;
import com.doruk.presentation.users.mapper.ProfileMapper;
import com.doruk.presentation.users.mapper.RegistrationMapper;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.http.multipart.StreamingFileUpload;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Tag(name = "User Management", description = "User registration and profile management")
@Secured(SecurityRule.IS_AUTHENTICATED)
@RequiredArgsConstructor
@Controller(Routes.APP + "/users")
public class UserController {
    private final UserService service;
    private final RegistrationMapper registrationMapper;
    private final ProfileMapper profileMapper;
    private final FileService fileService;

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Operation(
            summary = "Register new user account",
            description = """
                    Creates a new user account with organization support. Two registration types supported:
                    
                    **Enterprise Admin Registration:**
                    - type: ENTERPRISE
                    - orgName: Required (organization name)
                    - orgCode: Required (unique organization code)
                    - Result: Creates user with isOrgAdmin=true and a full organization
                    
                    **Personal User Registration:**
                    - type: PERSONAL
                    - orgName: Not applicable (must be null)
                    - orgCode: Not applicable (must be null)
                    - Result: Creates user with isOrgAdmin=false and a placeholder organization
                    
                    **Note:** Users can join existing organizations via the dashboard after registration.
                    """
    )
    @Status(HttpStatus.CREATED)
    @Post("/register")
    public UserResponseDto registerUser(
            @Valid @Body @RequestBody(description = "User registration details") RegistrationRequest req
    ) {
        return service.registerUser(registrationMapper.toUserCmdDto(req));
    }

    @Operation(summary = "Get information about current user")
    @CustomHttpMethod(method = "info", uri = "/me")
    public CurrentUserDto currentUser(Authentication auth) {
        return service.getCurrentUser(auth.getName());
    }

    @Put("/profile")
    public ProfileDto updateProfile(Authentication auth, @Valid @Body ProfileUpdateRequest req) {
        return service.updateProfile(auth.getName(), profileMapper.toProfileDto(req));
    }

    @Put("/profile/icon")
    public Map<String, String> updateProfileIcon(Authentication auth,
                                                 @Part("profile-icon")
                                                 StreamingFileUpload file) {
        var uploaded = fileService.imageUploadPublic(file);
        return service.updateProfileIcon(auth.getName(), uploaded);
    }
}
