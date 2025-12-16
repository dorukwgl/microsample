package com.doruk.infrastructure.security;

import com.doruk.application.security.PermissionEvaluator;
import com.doruk.application.security.UserScope;
import com.doruk.domain.shared.enums.Permissions;
import com.doruk.infrastructure.security.annotation.RequiresPermission;
import jakarta.inject.Singleton;

import java.util.Arrays;

@Singleton
public class RbacPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean evaluate(
            UserScope scope,
            Permissions[] required,
            RequiresPermission.Logical logical
    ) {
        if (required.length == 0) {
            return true;
        }

        return switch (logical) {
            case AND -> hasAll(scope, required);
            case OR -> hasAny(scope, required);
        };
    }

    private boolean isGhost(UserScope scope) {
        return scope.permissions().contains(Permissions.GHOST_PERMISSION);
    }

    private boolean isDictator(UserScope scope) {
        return scope.permissions().contains(Permissions.DICTATOR_PERMISSION) ||
                scope.permissions().contains(Permissions.GHOST_PERMISSION);
    }

    private boolean isGhostRequired(Permissions[] required) {
        return Arrays.stream(required).anyMatch(p -> p == Permissions.GHOST_PERMISSION);
    }

    private boolean isDictatorRequired(Permissions[] required) {
        return Arrays.stream(required).anyMatch(p -> p == Permissions.DICTATOR_PERMISSION);
    }

    private boolean hasAll(UserScope scope, Permissions[] required) {
        if (isGhostRequired(required) && isGhost(scope))
            return true;

        if (isDictatorRequired(required) && isDictator(scope))
            return true;

        for (Permissions p : required) {
            if (!scope.permissions().contains(p)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasAny(UserScope scope, Permissions[] required) {
        if (isGhostRequired(required) && isGhost(scope))
            return true;

        if (isDictatorRequired(required) && isDictator(scope))
            return true;

        for (Permissions p : required) {
            if (scope.permissions().contains(p)) {
                return true;
            }
        }
        return false;
    }
}
