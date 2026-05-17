package com.doruk.infrastructure.persistence.entity;

import com.doruk.domain.shared.enums.LicenseStatus;
import io.micronaut.core.annotation.Introspected;
import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.util.UUID;

@Introspected
@Entity
@Table(name = "license_addons")
public interface LicenseAddons {
    @Id
    UUID id();

    @Column(name = "license_id")
    UUID licenseId();

    @Column(name = "sku_id")
    UUID skuId();

    @Nullable
    @Column(name = "tier_id")
    Long tierId();

    @Nullable
    @Column(name = "valid_until")
    OffsetDateTime validUntil();

    @Column(name = "status")
    LicenseStatus status();

    @Column(name = "name")
    String productName();

    @Column(name = "tier_name")
    String tierName();

    @Nullable
    @Column(name = "entitlements")
    String entitlements();

    @Column(name = "total_seats")
    int totalSeats();

    @Column(name = "created_at")
    OffsetDateTime createdAt();

    // --- Relationships ---

    @ManyToOne
    @JoinColumn(name = "license_id")
    Licenses licenses();
}
