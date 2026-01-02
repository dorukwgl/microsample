package com.doruk.presentation.auth.controller;

import com.doruk.application.auth.dto.JwtResponse;
import com.doruk.application.auth.dto.LoginResponse;
import com.doruk.application.auth.dto.SessionDto;
import com.doruk.application.auth.service.AuthService;
import com.doruk.infrastructure.config.AppConfig;
import com.doruk.infrastructure.util.Constants;
import com.doruk.infrastructure.util.StringUtil;
import com.doruk.presentation.auth.dto.DeviceInfoRequest;
import com.doruk.presentation.auth.dto.LoginRequest;
import com.doruk.presentation.auth.mappers.DeviceInfoMapper;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.cookie.Cookie;
import io.micronaut.http.cookie.SameSite;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Tag(name = "Authentications")
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;
    private final DeviceInfoMapper infoMapper;
    private final AppConfig appConfig;

    @ApiResponse(responseCode = "201", description = "Login successful")
    @ApiResponse(responseCode = "202", description = "Multi factor authentication required")
    @Secured(SecurityRule.IS_ANONYMOUS)
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

        // now set cookies to the response
        return HttpResponse.created(response)
                .cookie(Cookie.of(Constants.SESSION_COOKIE_HEADER, response.refreshToken())
                        .httpOnly()
                        .secure(appConfig.cookieSecure())
                        .maxAge(Duration.ofDays(appConfig.sessionExpiration()))
                        .path("/app/auth/session")
                        .sameSite(SameSite.Lax));
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
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
    @Get("/session/refresh")
    HttpResponse<JwtResponse> refreshAccessToken(@CookieValue(Constants.SESSION_COOKIE_HEADER) String info) {
        return HttpResponse.ok().body(service.refreshAccessToken(info));
    }
// #####################################################################################

    @Operation(description = "View the list of logged in devices")
    @Get("/session/list")
    HttpResponse<List<SessionDto>> listSessions(Authentication auth) {
        return HttpResponse.ok()
        .body(service.listSessions(auth.getName()));
    }

    @Operation(description = "Sign Out from current device")
    @Delete("/session/logout")
    HttpResponse<?> logout(@CookieValue(Constants.SESSION_COOKIE_HEADER) String sessionId) {
        service.logoutCurrent(sessionId);
        return HttpResponse.noContent();
    }

    @Operation(description = "Sign Out from all devices")
    @Delete("/session/logout-all{?biometric}")
    HttpResponse<?> logoutAll(Authentication auth,
            @CookieValue(Constants.SESSION_COOKIE_HEADER)
            String sessionId,
            @Parameter(description = "Also removes the biometrics data, if set to true")
            @QueryValue(defaultValue = "false")
            boolean biometric) {
        service.logoutAll(auth.getName(), biometric);
        return HttpResponse.noContent();
    }

    @Operation(description = "Sign Out from other logged in devices, except the current one.")
    @Delete("/session/logout-others{?biometric}")
    HttpResponse<?> logoutOthers(Authentication auth,
            @CookieValue(Constants.SESSION_COOKIE_HEADER)
            String sessionId,
            @Parameter(description = "Also removes the biometrics data, if set to true")
            @QueryValue(defaultValue = "false")
            boolean biometric) {
        service.logoutOthers(auth.getName(), sessionId, biometric);
        return HttpResponse.noContent();
    }
}
