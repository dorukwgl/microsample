package com.doruk.application.policies;

import com.doruk.application.app.organization.dto.OrganizationInfoDto;
import com.doruk.application.app.users.dto.CurrentUserDto;
import com.doruk.application.dto.OrganizationDto;
import com.doruk.domain.shared.enums.OrganizationType;

public class OrganizationPolicy {

    public static boolean canUserEditOrganization(CurrentUserDto user, OrganizationInfoDto org) {
        return user.organization().id().equals(org.id()) && user.isOrgAdmin();
    }

    public static boolean canUserViewOrganization(CurrentUserDto user, OrganizationInfoDto org) {
        return user.organization().id().equals(org.id());
    }

    public static boolean canUserJoinOrganization(CurrentUserDto user) {
        return user.organization().type() == OrganizationType.PERSONAL;
    }

    public static boolean canUserLeaveOrganization(OrganizationDto org) {
        return org.type() == OrganizationType.ENTERPRISE;
    }

    public static boolean canUserManageMembers(CurrentUserDto user) {
        return user.isOrgAdmin();
    }
}