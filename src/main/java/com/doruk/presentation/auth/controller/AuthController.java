package com.doruk.presentation.auth.controller;

import com.doruk.application.auth.dto.LoginResponse;
import com.doruk.application.auth.service.AuthService;
import com.doruk.presentation.auth.dto.LoginRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @Get("/hello")
    Mono<String> hello() {
        return Mono.fromCallable(() -> {
            IO.println(Thread.currentThread().getName());
            return "world...";
        });
    }

    @ApiResponse(responseCode = "201", description = "Login successful")
    @ApiResponse(responseCode = "202", description = "Multi factor authentication required")
    @Post("/login")
    HttpResponse<LoginResponse> login(@Valid @Body LoginRequest request) {
        var response = service.performLogin(request.identifier(),
                request.password(),
                Optional.ofNullable(request.deviceId()),
                Optional.ofNullable(request.deviceInfo())
        );

        if (response.mfaRequired())
            return HttpResponse.accepted().body(response);
        return HttpResponse.created(response);
    }

    @Post("/mfa/verify-mfa/{mfaToken}/{otp}")
    LoginResponse verifyMfa(
            String mfaToken,
            @Min(value = 100000, message = "OTP must be 6 digits")
            @Max(value = 999999, message = "OTP must be 6 digits")
            int otp) {
        IO.println("hello: " + otp);
        return null;
    }
}
