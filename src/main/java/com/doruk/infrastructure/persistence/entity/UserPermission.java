package com.doruk.infrastructure.persistence.entity;

import jakarta.annotation.Nullable;
import org.babyfish.jimmer.sql.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_permissions")
public interface UserPermission {
    @Embeddable
    record UserPermissionId(
            @Column(name = "user_id")
            UUID userId,

            @Column(name = "permission_name")
            String permissionName
    ) {}

    @Id
    UserPermissionId id();

    // Link to User (Part of PK)
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Key // Part of the unique business key
    User user();

    @Id
    @ManyToOne
    @JoinColumn(name = "permission_name")
    @Key // Part of the unique business key
    Permission permission();

    LocalDateTime createdAt();

    @Nullable
    LocalDateTime expiresAt();
}
