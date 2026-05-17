package com.doruk.presentation.license;

import com.doruk.application.app.license.dto.LicenseResponse;
import com.doruk.application.app.license.LicenseService;
import com.doruk.application.app.license.dto.SeatDto;
import com.doruk.application.dto.PageResponse;
import com.doruk.presentation.dto.PageQueryMapper;
import com.doruk.presentation.dto.PageQueryRequest;
import com.doruk.presentation.license.dto.AssignSeatRequest;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/licenses")
@Tag(name = "Licenses")
public class LicenseController {
    private final LicenseService licenseService;

    @Operation(summary = "Get all active licenses of organization")
    @Get
    public PageResponse<LicenseResponse> getLicenses(Authentication user, @Valid PageQueryRequest req) {
        return licenseService.getLicenses(user.getName(), PageQueryMapper.toQuery(req));
    }

    @Operation(summary = "List all seats for a license")
    @Get("/{licenseKey}/seats")
    public PageResponse<SeatDto> listSeats(Authentication user, String licenseKey, @Valid PageQueryRequest req) {
        return licenseService.listSeats(user.getName(), licenseKey, PageQueryMapper.toQuery(req));
    }

    @Operation(summary = "Assign a seat to a user")
    @Post("/{licenseKey}/seats")
    public void assignSeat(Authentication user, String licenseKey, @Body @Valid AssignSeatRequest req) {
        licenseService.assignSeat(user.getName(), licenseKey, req.userId());
    }

    @Operation(summary = "Revoke a seat from a user")
    @Delete("/{licenseKey}/seats/{userId}")
    public void revokeSeat(Authentication user, String licenseKey, UUID userId) {
        licenseService.revokeSeat(user.getName(), licenseKey, userId);
    }
}
