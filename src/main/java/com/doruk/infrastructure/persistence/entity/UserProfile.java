package com.doruk.infrastructure.persistence.entity;

import jakarta.annotation.Nullable;
import org.babyfish.jimmer.sql.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_profiles")
public interface UserProfile {

    // The ID matches the User ID exactly
    @Id
    @Column(name = "user_id")
    UUID id();

    // The relationship to User
    @OneToOne
    @JoinColumn(name = "user_id")
    User user();

    @Nullable
    String fullName();
    @Nullable
    String iconUrl();

    // Address fields...
    @Nullable String address();
    @Nullable String city();
    @Nullable String state();
    @Nullable String country();

    @Column(name = "postal_code")
    @Nullable String postalCode();

    LocalDateTime createdAt();
    LocalDateTime updatedAt();

    @LogicalDeleted
    LocalDateTime deletedAt();
}