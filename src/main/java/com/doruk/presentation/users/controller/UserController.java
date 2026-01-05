package com.doruk.presentation.users.controller;

import com.doruk.application.users.dto.CurrentUserDto;
import com.doruk.application.users.dto.ProfileDto;
import com.doruk.application.enums.FileType;
import com.doruk.application.interfaces.FileUploadHandler;
import com.doruk.application.security.UserScope;
import com.doruk.application.users.dto.UserResponseDto;
import com.doruk.application.users.service.UserService;
import com.doruk.infrastructure.config.AppConfig;
import com.doruk.infrastructure.dto.InfoResponse;
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
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Tag(name = "User Management, Registrations & Profiles")
@RequiredArgsConstructor
@Controller("users")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class UserController {
    private final UserService service;
    private final RegistrationMapper registrationMapper;
    private final ProfileMapper profileMapper;
    private final FileUploadHandler fileUploadHandler;
    private final AppConfig appConfig;

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Status(HttpStatus.CREATED)
    @Post("/register")
    public UserResponseDto registerUser(@Valid @Body RegistrationRequest req) {
        return service.registerUser(registrationMapper.toUserCmdDto(req));
    }

    @Operation(summary = "Get information about current user")
    @CustomHttpMethod(method = "info")
    public CurrentUserDto test(Authentication auth) {
        return service.getCurrentUser(auth.getName());
    }

    @Status(HttpStatus.ACCEPTED)
    @Post("/email/init-verification")
    public InfoResponse initEmailVerification(Authentication auth) {
        service.initEmailVerification(auth.getName());
        return new InfoResponse("OTP is sent to your email address");
    }

    @Post("/email/verify-otp/{transactionId}")
    public InfoResponse verifyEmailOtp(Authentication auth,
                                       String transactionId,
                                       @Body
                                       @NotBlank
                                       @Min(value = 100000)
                                       @Max(value = 999999)
                                       int otp) {

        service.verifyEmail(auth.getName(), transactionId, otp);
        return new InfoResponse("Email verified successfully");
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/email/verify-magic/{magicLink}")
    public InfoResponse verifyEmailViaMagicLink(String magicLink) {
        service.verifyEmail(magicLink);
        return new InfoResponse("Email verified successfully");
    }

    @Status(HttpStatus.ACCEPTED)
    @Post("/phone/init-verification")
    public InfoResponse initPhoneVerification(Authentication auth) {
        service.initPhoneVerification(auth.getName());
        return new InfoResponse("OTP sent to your phone number");
    }

    @Post("/phone/verify-otp/{transactionId}")
    public InfoResponse verifyPhoneNumber(
            String transactionId,
            @Body
            @NotBlank
            @Min(value = 100000)
            @Max(value = 999999)
            int otp) {
        service.verifyPhone(transactionId, otp);
        return new InfoResponse("Phone number verified successfully");
    }

    @Put("/profile")
    public ProfileDto updateProfile(Authentication auth, @Valid @Body ProfileUpdateRequest req) {
        return service.updateProfile(auth.getName(), profileMapper.toProfileDto(req));
    }

    @Put("/profile/phone")
    public InfoResponse updatePhoneNumber(Authentication auth,
                                          @Body
                                          @NotBlank
                                          @Pattern(regexp = "^\\+?[1-9][0-9]{7,14}$")
                                          String phone) {
        service.updatePhoneNumber(auth.getName(), phone);
        return new InfoResponse("Phone number updated successfully");
    }

    @Put("/profile/email")
    public InfoResponse updateEmailAddress(Authentication auth,
                                           @Body
                                           @NotBlank
                                           @Email
                                           String email) {
        service.updateEmail(auth.getName(), email);
        return new InfoResponse("Email address updated successfully");
    }

    @Put("/profile/icon")
    public Map<String, String> updateProfileIcon(Authentication auth,
                                                 @Part("profile-icon")
                                                 StreamingFileUpload file) {
        var uploaded = fileUploadHandler.uploadSingle(file, FileType.IMAGE, appConfig.profileIconMaxSize());

        return service.updateProfileIcon(auth.getName(), uploaded);
    }
}
