package com.doruk.infrastructure.persistence.entity;

import org.babyfish.jimmer.sql.Column;
import org.babyfish.jimmer.sql.Embeddable;

import java.util.UUID;

@Embeddable
public interface UserPermissionId {
    @Column(name = "user_id")
    UUID userId();

    @Column(name = "permission_name")
    String permissionName();
}
