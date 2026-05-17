package com.doruk.application.policies;

import com.doruk.application.app.license.dto.LicenseResponse;
import com.doruk.application.app.users.dto.CurrentUserDto;
import com.doruk.infrastructure.persistence.entity.Licenses;

import java.util.UUID;

public class LicensePolicy {
    public static boolean canManageLicense(CurrentUserDto user, LicenseResponse license) {
        return user.isOrgAdmin() && user.organization().id().equals(license.organizationId());
    }
}
