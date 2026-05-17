package com.doruk.presentation.admin;

import com.doruk.application.app.admin.dto.KeyPairDto;
import com.doruk.application.app.admin.dto.ProductServerResponse;
import com.doruk.application.app.admin.service.AdminService;
import com.doruk.application.app.admin.dto.LicenseResponse;
import com.doruk.application.dto.PageResponse;
import com.doruk.domain.shared.enums.Permissions;
import com.doruk.infrastructure.dto.InfoResponse;
import com.doruk.infrastructure.security.annotation.RequiresPermission;
import com.doruk.presentation.admin.dto.CreateProductServerRequest;
import com.doruk.presentation.admin.dto.GrantLicenseRequest;
import com.doruk.presentation.admin.mapper.ProductServerAdminMapper;
import com.doruk.presentation.dto.PageQueryMapper;
import com.doruk.presentation.dto.PageQueryRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Controller("/admin")
@Secured(SecurityRule.IS_AUTHENTICATED)
@RequiresPermission(Permissions.SYSTEM_PERMISSION)
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final ProductServerAdminMapper productServerMapper;

    /**
     *
     * # Manual License Operations
     * POST   /admin/licenses/grant               manually create license (bypass billing)
     * PATCH  /admin/licenses/:licenseId/revoke  revoke
     * PATCH  /admin/licenses/:licenseId/suspend suspend (new status)
     * PATCH  /admin/licenses/:licenseId/reactivate
     *
     * # Considerations
     * Most operations are already implemented, so reuse them
     * while grant license: call existing payment completed methods which also fires webhook
     * same with any license operations: as this is just plugin point for admin manual control bypassing billing
     */

    @Status(HttpStatus.CREATED)
    @Post("/product-servers")
    public ProductServerResponse createProductServer(@Valid @Body CreateProductServerRequest req) {
        return adminService.create(productServerMapper.toCreateCmd(req));
    }

    @Get("/product-servers")
    public PageResponse<ProductServerResponse> listProductServers(
            @Valid PageQueryRequest page,
            @Nullable @QueryValue("name") String name) {
        return adminService.list(PageQueryMapper.toQuery(page), name);
    }

    @Delete("/product-servers/{skuId}")
    public void deleteProductServer(@PathVariable String skuId) {
        adminService.deleteProductServer(skuId);
    }

    @Get("/orgs/{orgId}/licenses")
    public PageResponse<LicenseResponse> getOrgLicenses(
            @PathVariable UUID orgId,
            @Valid PageQueryRequest page) {
        return adminService.getLicensesByOrgId(orgId, PageQueryMapper.toQuery(page));
    }

    @Get("/key-pair")
    public KeyPairDto generateKeyPair()  {
        return adminService.generateEd25519KeyPair();
    }

    @Status(HttpStatus.CREATED)
    @Post("/licenses/grant")
    public InfoResponse grantLicense(@Valid @Body GrantLicenseRequest req) {
        return adminService.grantLicense(productServerMapper.toGrantCmd(req));
    }

    @Patch("/licenses/{licenseId}/revoke")
    public InfoResponse revokeLicense(@PathVariable String licenseId) {
        return adminService.revokeLicense(licenseId);
    }

    @Patch("/licenses/{licenseId}/suspend")
    public InfoResponse suspendLicense(@PathVariable String licenseId) {
        return adminService.suspendLicense(licenseId);
    }

    @Patch("/licenses/{licenseId}/reactivate")
    public InfoResponse reactivateLicense(@PathVariable String licenseId) {
        return adminService.reactivateLicense(licenseId);
    }
}
