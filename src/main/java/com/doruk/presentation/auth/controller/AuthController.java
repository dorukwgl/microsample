package com.doruk.presentation.auth.controller;

import com.doruk.application.auth.dto.AuthUpdateResponse;
import com.doruk.application.auth.dto.JwtResponse;
import com.doruk.application.auth.dto.LoginResponse;
import com.doruk.application.auth.dto.SessionDto;
import com.doruk.application.auth.service.AuthService;
import com.doruk.infrastructure.config.AppConfig;
import com.doruk.infrastructure.dto.InfoResponse;
import com.doruk.infrastructure.util.Constants;
import com.doruk.presentation.auth.dto.DeviceInfoRequest;
import com.doruk.presentation.auth.dto.LoginRequest;
import com.doruk.presentation.auth.mappers.DeviceInfoMapper;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
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
import jakarta.validation.constraints.*;
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

    @Operation(description = "Change Password")
    @Put("/password")
    HttpResponse<?> changePassword(Authentication auth,
                                   @Body
                                   @Size(min = 8, max = 50)
                                   String newPassword,
                                   @Body
                                   @Size(min = 8, max = 50)
                                   String password) {
        service.updatePassword(auth.getName(), password, newPassword);
        return HttpResponse.noContent();
    }

    @Status(HttpStatus.ACCEPTED)
    @Post("/email/init-verification")
    public Map<String, String> initEmailVerification(Authentication auth) {
        return service.initEmailVerification(auth.getName());
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
    public Map<String, String> initPhoneVerification(Authentication auth) {
        return service.initPhoneVerification(auth.getName());
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

    @Operation(description = "Update the existing email address")
    @ApiResponse(
            description = "When existing email address is not verified.",
            responseCode = "200"
    )
    @ApiResponse(
            description = "When existing email address is verified.",
            responseCode = "202"
    )
    @Put("/update/email")
    public HttpResponse<AuthUpdateResponse> updateEmail(Authentication auth,
                                                        @Email
                                                        @NotBlank
                                                        String email) {
        var res = service.updateEmail(auth.getName(), email);
        if (res.otpRequired())
            return HttpResponse.accepted().body(res);
        return HttpResponse.ok().body(res);
    }

    @Operation(description = "Update existing or add new phone number")
    @ApiResponse(
            description = "When existing phone number is not verified",
            responseCode = "200"
    )
    @ApiResponse(
            description = "When existing phone number is verified",
            responseCode = "202"
    )
    @Put("/update/phone")
    public HttpResponse<AuthUpdateResponse> updatePhone(Authentication auth,
                                                        @NotBlank
                                                        @Pattern(regexp = "^\\+?\\d{6,14}$\n")
                                                        String phone) {
        var res = service.updatePhone(auth.getName(), phone);
        if (res.otpRequired())
            return HttpResponse.accepted().body(res);
        return HttpResponse.ok().body(res);
    }

    @Operation(description = "Verify the update phone transaction. Second step of the route /update/phone")
    @Put("/update/phone/verify")
    public InfoResponse verifyUpdatePhone(Authentication auth,
                                          @Size(max = 80)
                                          @Body
                                          String tid,
                                          @Body
                                          @Max(999999)
                                          @Min(1)
                                          int otp
    ) {
        service.verifyUpdatePhoneTransaction(auth.getName(), tid, otp);
        return new InfoResponse("Phone number updated successfully");
    }

    @Operation(description = "Verify the update email transaction. Second step of the route /update/email")
    @Put("/update/email/verify")
    public InfoResponse verifyUpdateEmail(Authentication auth,
                                          @Size(max = 80)
                                          @Body
                                          String tid,
                                          @Body
                                          @Max(999999)
                                          @Min(1)
                                          int otp) {
        service.verifyUpdateEmailTransaction(auth.getName(), tid, otp);
        return new InfoResponse("Email address updated successfully");
    }
}
