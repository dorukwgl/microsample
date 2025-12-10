package com.doruk.infrastructure.persistence.entity;

import jakarta.annotation.Nullable;
import org.babyfish.jimmer.sql.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "biometrics")
public interface Biometric {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id();

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user();

    @Key
    String publicKey();

    @Key
    String deviceId();

    @Nullable
    LocalDateTime deletedAt();
}
