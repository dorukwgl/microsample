package com.doruk.infrastructure.persistence.entity;

import com.doruk.domain.shared.enums.LicenseStatus;
import com.doruk.domain.shared.enums.LicenseType;
import io.micronaut.core.annotation.Introspected;
import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.util.UUID;

@Introspected
@Entity
@Table(name = "licenses")
public interface Licenses {
    @Id
    UUID id();

    @Column(name = "license_key")
    String licenseKey();

    @Column(name = "organization_id")
    UUID organizationId();

    @Column(name = "sku_id")
    UUID skuId();

    @Column(name = "tier_id")
    Long tierId();

    LicenseType type();

    LicenseStatus status();

    @Column(name = "name")
    String productName();

    @Column(name = "tier_name")
    String tierName();

    @Column(name = "total_seats")
    int totalSeats();

    @Column(name = "assigned_seats")
    int assignedSeats();

    @Column(name = "valid_from")
    OffsetDateTime validFrom();

    @Nullable
    @Column(name = "valid_until")
    OffsetDateTime validUntil();

    @Nullable
    @Column(name = "entitlements")
    String entitlements();

    @Column(name = "created_at")
    OffsetDateTime createdAt();

    @Column(name = "updated_at")
    OffsetDateTime updatedAt();

    // --- Relationships ---

    @ManyToOne
    @JoinColumn(name = "organization_id")
    Organizations organizations();

    @OneToMany(mappedBy = "licenses")
    java.util.List<LicenseAddons> addons();
}
