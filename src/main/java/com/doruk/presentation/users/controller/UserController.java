package com.doruk.presentation.users.controller;

import com.doruk.application.security.UserScope;
import com.doruk.application.users.dto.UserResponseDto;
import com.doruk.application.users.service.UserService;
import com.doruk.infrastructure.dto.InfoResponse;
import com.doruk.presentation.users.dto.RegistrationRequest;
import com.doruk.presentation.users.mapper.RegistrationMapper;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Tag(name = "User Management, Registrations & Profiles")
@RequiredArgsConstructor
@Controller("users")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class UserController {
    private final UserService service;
    private final RegistrationMapper registrationMapper;

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Status(HttpStatus.CREATED)
    @Post("/register")
    public UserResponseDto registerUser(@Valid @Body RegistrationRequest req) {
        return service.registerUser(registrationMapper.toUserCmdDto(req));
    }


    @Get("/test")
    public Authentication test(Authentication auth) {
        IO.println(auth.getName());
        IO.println(auth.getAttributes());
        IO.println(auth.getAttributes().get(UserScope.KEY));
        IO.println(auth.getRoles());
        return auth;
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
}
