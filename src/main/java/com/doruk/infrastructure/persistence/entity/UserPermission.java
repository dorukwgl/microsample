package com.doruk.infrastructure.persistence.entity;

import jakarta.annotation.Nullable;
import org.babyfish.jimmer.sql.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_permissions")
public interface UserPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    BigInteger id();

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user();

    @ManyToOne
    @JoinColumn(name = "permission_name")
    Permission permission();

    LocalDateTime createdAt();

    @Nullable
    LocalDateTime expiresAt();
}
