package com.doruk.presentation.interop;

import com.doruk.application.app.interop.InteropService;
import com.doruk.application.app.interop.dto.LicenseInfoResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Singleton
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/interop")
public class InteropController {
    private final InteropService service;

    @Get("/license/{userId}/{skuId}")
    public LicenseInfoResponse getLicenseInfo(
            @NotBlank @NotNull UUID userId,
            @Parameter(description = "SKU identifier for the license. Product SKU. Do not confuse for addon sku")
            @NotBlank @NotNull UUID skuId) {
        return service.getLicenseInfo(userId, skuId);
    }
}
