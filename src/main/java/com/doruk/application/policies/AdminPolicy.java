package com.doruk.application.policies;

import com.doruk.domain.shared.enums.Permissions;

import java.util.Set;

public class AdminPolicy {
    public static boolean canManageProductServers(Set<Permissions> permissions) {
        return permissions.contains(Permissions.DICTATOR_PERMISSION)
                || permissions.contains(Permissions.SYSTEM_PERMISSION);
    }
}
