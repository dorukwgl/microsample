package com.doruk.infrastructure.security;

import com.doruk.application.security.PermissionEvaluator;
import com.doruk.application.security.UserScope;
import com.doruk.domain.shared.enums.Permissions;
import com.doruk.infrastructure.security.annotation.RequiresPermission;
import jakarta.inject.Singleton;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

@Singleton
public class RbacPermissionEvaluator implements PermissionEvaluator {
    private final Map<Permissions, Integer> roleDynamics = new EnumMap<>(Permissions.class);

    public RbacPermissionEvaluator () {
        roleDynamics.put(Permissions.GHOST_PERMISSION, 0);
        roleDynamics.put(Permissions.DICTATOR_PERMISSION, 1);
    }

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
        if (isGhost(scope))
            return true;

        if (isGhostRequired(required))
            return false;

        if (isDictator(scope))
            return true;

        if (isDictatorRequired(required))
            return false;

        return scope.permissions().containsAll(Arrays.stream(required).toList());
    }

    private boolean hasAny(UserScope scope, Permissions[] required) {
        if (isGhost(scope))
            return true;

        if (isDictator(scope))
            return true;

        if (isGhostRequired(required))
            return false;

        if (isDictatorRequired(required))
            return true;

        return Arrays.stream(required)
                .anyMatch(scope.permissions()::contains);
    }
}
