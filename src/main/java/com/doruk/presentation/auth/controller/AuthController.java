package com.doruk.presentation.auth.controller;

import com.doruk.application.auth.dto.JwtResponse;
import com.doruk.application.auth.dto.LoginResponse;
import com.doruk.application.auth.service.AuthService;
import com.doruk.infrastructure.util.Constants;
import com.doruk.presentation.auth.dto.DeviceInfoRequest;
import com.doruk.presentation.auth.dto.LoginRequest;
import com.doruk.presentation.auth.mappers.DeviceInfoMapper;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Tag(name = "Authentications")
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;
    private final DeviceInfoMapper infoMapper;


    @Get("/hello")
    HttpResponse<Map<String, String>> hello(@Valid @RequestBean DeviceInfoRequest info) {
        IO.println(info.userAgent());
        return HttpResponse.status(666, "hello world").body(Map.of("message", "hello world"));
    }

    @Operation
    @ApiResponse(responseCode = "201", description = "Login successful")
    @ApiResponse(responseCode = "202", description = "Multi factor authentication required")
    @Post("/login")
    HttpResponse<LoginResponse> login(
            @Valid @Body LoginRequest request,
            @Valid @RequestBean DeviceInfoRequest info) {
        var response = service.performLogin(request.identifier(),
                request.password(),
                infoMapper.toDeviceInfo(info)
        );

        if (response.mfaRequired())
            return HttpResponse.status(202, "MFA Required").body(response);
        return HttpResponse.created(response);
    }

    @Post("/mfa/verify/{mfaToken}")
    LoginResponse verifyMfa(
            @Valid @RequestBean DeviceInfoRequest info,
            String mfaToken,
            @Max(value = 999999, message = "OTP must be 6 digits")
            @Min(value = 100000, message = "OTP must be 6 digits")
            int otp
    ) {
        return service.performMfa(mfaToken, otp, infoMapper.toDeviceInfo(info));
    }

    @ApiResponse(responseCode = "200", description = "Access token refreshed successfully")
    @ApiResponse(responseCode = "401", description = "Invalid or expired session. Please login again.")
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/refresh/token")
    HttpResponse<JwtResponse> refreshAccessToken(@CookieValue(Constants.SESSION_COOKIE_HEADER) String info) {
        return HttpResponse.ok().body(service.refreshAccessToken(info));
    }
}
