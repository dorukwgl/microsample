package com.doruk.infrastructure.persistence.entity;

import io.micronaut.core.annotation.Introspected;
import org.babyfish.jimmer.sql.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Introspected
@Entity
@Table(name = "license_seats")
public interface LicenseSeats {
    @Id
    UUID id();

    @ManyToOne
    @JoinColumn(name = "license_id")
    Licenses licenses();

    @IdView("licenses")
    UUID licenseId();

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user();

    @IdView
    UUID userId();

    @Column(name = "assigned_at")
    OffsetDateTime assignedAt();

    @ManyToOne
    @JoinColumn(name = "assigned_by")
    User assignedBy();

    @IdView("assignedBy")
    UUID assignedById();
}
