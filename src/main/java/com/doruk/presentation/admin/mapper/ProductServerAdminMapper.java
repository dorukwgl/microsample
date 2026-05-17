package com.doruk.presentation.admin.mapper;

import com.doruk.application.app.admin.dto.CreateProductServerCmd;
import com.doruk.application.app.admin.dto.GrantLicenseCmd;
import com.doruk.presentation.admin.dto.CreateProductServerRequest;
import com.doruk.presentation.admin.dto.GrantLicenseRequest;
import io.micronaut.context.annotation.Mapper;

public interface ProductServerAdminMapper {
    @Mapper
    CreateProductServerCmd toCreateCmd(CreateProductServerRequest request);

    @Mapper
    GrantLicenseCmd toGrantCmd(GrantLicenseRequest request);
}
