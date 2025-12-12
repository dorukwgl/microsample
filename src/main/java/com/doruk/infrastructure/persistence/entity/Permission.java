package com.doruk.infrastructure.persistence.entity;

import io.micronaut.core.annotation.Introspected;
import jakarta.annotation.Nullable;
import org.babyfish.jimmer.sql.*;

import java.time.LocalDateTime;
import java.util.List;

@Introspected
@Entity
@Table(name = "permissions")
public interface Permission {
    @Id
    String name();

    @ManyToMany(mappedBy = "permissions")
    List<Role> roles();

    // Inverse of the special UserPermission entity
    @OneToMany(mappedBy = "permission")
    List<UserPermission> elevatedGrants();

    @LogicalDeleted("now")
    @Nullable
    @Column(name = "deleted_at")
    LocalDateTime deletedAt();
}
