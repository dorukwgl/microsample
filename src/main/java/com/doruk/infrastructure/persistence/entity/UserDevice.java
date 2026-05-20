package com.doruk.infrastructure.persistence.entity;

import io.micronaut.core.annotation.Introspected;
import jakarta.annotation.Nullable;
import org.babyfish.jimmer.sql.*;

import java.time.OffsetDateTime;

@Introspected
@Entity
@Table(name = "user_devices")
public interface UserDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user();

    // Unique enforced by partial index (nullable column) — see V4 migration
    @Column(name = "notification_device_id")
    String notificationDeviceId();

    @Nullable
    @Column(name = "bio_device_id")
    String bioDeviceId();

    @Nullable
    @Column(name = "device_info")
    String deviceInfo();

    @Column(name = "last_login_at")
    OffsetDateTime lastLoginAt();

    @Column(name = "created_at")
    OffsetDateTime createdAt();
}