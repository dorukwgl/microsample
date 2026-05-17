package com.doruk.infrastructure.persistence.entity;

import com.doruk.domain.shared.enums.OrganizationType;
import io.micronaut.core.annotation.Introspected;
import org.babyfish.jimmer.sql.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Introspected
@Entity
@Table(name = "organizations")
public interface Organizations {
    @Id
    UUID id();

    String name();

    OrganizationType type();

    @Column(name = "org_code")
    String orgCode();

    @Column(name = "created_at")
    OffsetDateTime createdAt();

    // --- Relationships ---

    @OneToMany(mappedBy = "organizations")
    java.util.List<Licenses> licenses();

    @OneToMany(mappedBy = "organization")
    java.util.List<User> users();
}
