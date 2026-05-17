package com.doruk.application.app.admin.repo;

import com.doruk.application.app.admin.dto.CreateProductServerCmd;
import com.doruk.application.app.admin.dto.ProductServerResponse;
import com.doruk.application.app.admin.dto.LicenseResponse;
import com.doruk.application.app.license.dto.LicenseCmd;
import com.doruk.application.dto.PageQuery;
import com.doruk.application.dto.PageResponse;
import jakarta.annotation.Nullable;

import java.util.Optional;
import java.util.UUID;

public interface AdminServiceRepo {
    ProductServerResponse create(CreateProductServerCmd cmd);

    PageResponse<ProductServerResponse> list(PageQuery query, @Nullable String nameSearch);

    void deleteBySkuId(String skuId);

    Optional<ProductServerResponse> findBySkuId(String skuId);

    PageResponse<LicenseResponse> getLicensesByOrgId(UUID orgId, PageQuery query);

    Optional<LicenseCmd> findLicenseById(String licenseId);
}
