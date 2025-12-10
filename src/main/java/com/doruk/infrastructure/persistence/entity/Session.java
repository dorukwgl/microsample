package com.doruk.infrastructure.persistence.entity;

import jakarta.annotation.Nullable;
import org.babyfish.jimmer.sql.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sessions")
public interface Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id();

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user();

    @Key
    String sessionId();

    String deviceInfo();

    @Nullable
    @Key
    String deviceId();

    // Jimmer maps Postgres Arrays to Java Arrays/Lists
    // Warning: SQL said "integer[]", but Role PK is String.
    // If SQL column stores strings, change this to String[]
    @Column(name = "roles")
    Integer[] cachedRoles();

    @Column(name = "permissions")
    Integer[] cachedPermissions();
}
