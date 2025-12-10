package com.doruk.infrastructure.persistence.entity;

import java.lang.String;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.doruk.domain.shared.enums.MultiAuthType;
import com.doruk.domain.shared.enums.UserAccountStatus;
import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

@Entity
@Table(name = "users")
public interface User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id();

    @Key
    String username();

    @Key
    String email();

    @Nullable
    String phone();

    String password();

    // SQL defaults to 'NONE', mapped via Enum name
    @Column(name = "multi_factor_auth")
    MultiAuthType multiFactorAuth();

    @Column(name = "is_email_verified")
    boolean isEmailVerified();

    @Column(name = "is_phone_verified")
    boolean isPhoneVerified();

    @Column(name = "status")
    UserAccountStatus status();

    @Column(name = "elevated_until")
    @Nullable
    LocalDateTime elevatedUntil();

    @Nullable
    @Column(name = "updated_at")
    LocalDateTime updatedAt();

    @Nullable
    @LogicalDeleted
    @Column(name = "deleted_at")
    LocalDateTime deletedAt();

    // --- Relationships ---

    @OneToOne(mappedBy = "user")
    @Nullable
    UserProfile profile();

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumnName = "user_id",
            inverseJoinColumnName = "name"
    )
    List<Role> roles();

    @OneToMany(mappedBy = "user")
    List<UserPermission> elevatedPermissions();

    @OneToMany(mappedBy = "user")
    List<Session> sessions();

    @OneToMany(mappedBy = "user")
    List<Biometric> biometrics();
}
