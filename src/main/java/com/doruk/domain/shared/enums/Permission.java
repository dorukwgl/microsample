package com.doruk.domain.shared.enums;

import com.doruk.domain.exception.DomainException;

public enum Permission {
    GHOST(0),
    USER_STATUS_UPDATE (1),
    DELETE_USERS (3),
    UPDATE_OWN_PROFILE (4);

    private final int value;

    Permission(int value) {
        this.value = value;
    }

    public int id() {
        return this.value;
    }

    public static Permission fromId(int id) {
        for (Permission permission : Permission.values()) {
            if (permission.id() == id) {
                return permission;
            }
        }
        throw new DomainException(400, "Invalid permission id: " + id);
    }
}
