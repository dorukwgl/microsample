package com.doruk.presentation.auth.controller;

import com.doruk.application.auth.dto.LoginResponse;
import com.doruk.application.auth.service.AuthService;
import com.doruk.presentation.auth.dto.LoginRequest;
import com.doruk.presentation.auth.dto.MfaRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation
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
            return HttpResponse.status(202, "MFA Required").body(response);
        return HttpResponse.created(response);
    }

    @Post("/mfa/verify-mfa/{mfaToken}/{otp}")
    LoginResponse verifyMfa(@Valid @RequestBean MfaRequest dto) {
        return service.performMfa(dto.mfaToken(), dto.otp(),
                Optional.ofNullable(dto.deviceId()), Optional.ofNullable(dto.deviceInfo()));
    }
}
