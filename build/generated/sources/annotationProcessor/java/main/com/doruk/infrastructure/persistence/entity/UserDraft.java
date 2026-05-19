package com.doruk.infrastructure.persistence.entity;

import com.doruk.domain.shared.enums.MultiAuthType;
import com.doruk.domain.shared.enums.UserAccountStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.lang.Boolean;
import java.lang.CloneNotSupportedException;
import java.lang.Cloneable;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.System;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.babyfish.jimmer.CircularReferenceException;
import org.babyfish.jimmer.Draft;
import org.babyfish.jimmer.DraftConsumer;
import org.babyfish.jimmer.ImmutableObjects;
import org.babyfish.jimmer.UnloadedException;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.jackson.ImmutableModuleRequiredException;
import org.babyfish.jimmer.lang.OldChain;
import org.babyfish.jimmer.meta.ImmutablePropCategory;
import org.babyfish.jimmer.meta.ImmutableType;
import org.babyfish.jimmer.meta.PropId;
import org.babyfish.jimmer.runtime.DraftContext;
import org.babyfish.jimmer.runtime.DraftSpi;
import org.babyfish.jimmer.runtime.ImmutableSpi;
import org.babyfish.jimmer.runtime.Internal;
import org.babyfish.jimmer.runtime.NonSharedList;
import org.babyfish.jimmer.runtime.Visibility;
import org.babyfish.jimmer.sql.ManyToMany;
import org.babyfish.jimmer.sql.ManyToOne;
import org.babyfish.jimmer.sql.OneToMany;
import org.babyfish.jimmer.sql.OneToOne;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@GeneratedBy(
        type = User.class
)
public interface UserDraft extends User, Draft {
    UserDraft.Producer $ = Producer.INSTANCE;

    @OldChain
    UserDraft setId(UUID id);

    @OldChain
    UserDraft setUsername(String username);

    @OldChain
    UserDraft setEmail(String email);

    @OldChain
    UserDraft setPhone(String phone);

    @OldChain
    UserDraft setPassword(String password);

    @OldChain
    UserDraft setMultiFactorAuth(MultiAuthType multiFactorAuth);

    @OldChain
    UserDraft setEmailVerified(boolean emailVerified);

    @OldChain
    UserDraft setPhoneVerified(boolean phoneVerified);

    @OldChain
    UserDraft setStatus(UserAccountStatus status);

    @OldChain
    UserDraft setUpdatedAt(OffsetDateTime updatedAt);

    @OldChain
    UserDraft setCreatedAt(OffsetDateTime createdAt);

    @OldChain
    UserDraft setOrganizationId(UUID organizationId);

    @OldChain
    UserDraft setOrgAdmin(boolean orgAdmin);

    @Nullable
    UserProfileDraft profile();

    UserProfileDraft profile(boolean autoCreate);

    @OldChain
    UserDraft setProfile(UserProfile profile);

    @Nullable
    @JsonIgnore
    UUID profileId();

    @OldChain
    UserDraft setProfileId(@Nullable UUID profileId);

    @OldChain
    UserDraft applyProfile(DraftConsumer<UserProfileDraft> block);

    @OldChain
    UserDraft applyProfile(UserProfile base, DraftConsumer<UserProfileDraft> block);

    List<RoleDraft> roles(boolean autoCreate);

    @OldChain
    UserDraft setRoles(List<Role> roles);

    @OldChain
    UserDraft addIntoRoles(DraftConsumer<RoleDraft> block);

    @OldChain
    UserDraft addIntoRoles(Role base, DraftConsumer<RoleDraft> block);

    List<SessionDraft> sessions(boolean autoCreate);

    @OldChain
    UserDraft setSessions(List<Session> sessions);

    @OldChain
    UserDraft addIntoSessions(DraftConsumer<SessionDraft> block);

    @OldChain
    UserDraft addIntoSessions(Session base, DraftConsumer<SessionDraft> block);

    List<BiometricDraft> biometrics(boolean autoCreate);

    @OldChain
    UserDraft setBiometrics(List<Biometric> biometrics);

    @OldChain
    UserDraft addIntoBiometrics(DraftConsumer<BiometricDraft> block);

    @OldChain
    UserDraft addIntoBiometrics(Biometric base, DraftConsumer<BiometricDraft> block);

    OrganizationsDraft organization();

    OrganizationsDraft organization(boolean autoCreate);

    @OldChain
    UserDraft setOrganization(Organizations organization);

    @OldChain
    UserDraft applyOrganization(DraftConsumer<OrganizationsDraft> block);

    @OldChain
    UserDraft applyOrganization(Organizations base, DraftConsumer<OrganizationsDraft> block);

    @GeneratedBy(
            type = User.class
    )
    class Producer {
        static final Producer INSTANCE = new Producer();

        public static final int SLOT_ID = 0;

        public static final int SLOT_USERNAME = 1;

        public static final int SLOT_EMAIL = 2;

        public static final int SLOT_PHONE = 3;

        public static final int SLOT_PASSWORD = 4;

        public static final int SLOT_MULTI_FACTOR_AUTH = 5;

        public static final int SLOT_EMAIL_VERIFIED = 6;

        public static final int SLOT_PHONE_VERIFIED = 7;

        public static final int SLOT_STATUS = 8;

        public static final int SLOT_UPDATED_AT = 9;

        public static final int SLOT_CREATED_AT = 10;

        public static final int SLOT_ORGANIZATION_ID = 11;

        public static final int SLOT_ORG_ADMIN = 12;

        public static final int SLOT_PROFILE = 13;

        public static final int SLOT_ROLES = 14;

        public static final int SLOT_SESSIONS = 15;

        public static final int SLOT_BIOMETRICS = 16;

        public static final int SLOT_ORGANIZATION = 17;

        public static final ImmutableType TYPE = ImmutableType
            .newBuilder(
                "0.10.7",
                User.class,
                Collections.emptyList(),
                (ctx, base) -> new DraftImpl(ctx, (User)base)
            )
            .id(SLOT_ID, "id", UUID.class)
            .key(SLOT_USERNAME, "username", String.class, false)
            .key(SLOT_EMAIL, "email", String.class, false)
            .add(SLOT_PHONE, "phone", ImmutablePropCategory.SCALAR, String.class, true)
            .add(SLOT_PASSWORD, "password", ImmutablePropCategory.SCALAR, String.class, false)
            .add(SLOT_MULTI_FACTOR_AUTH, "multiFactorAuth", ImmutablePropCategory.SCALAR, MultiAuthType.class, false)
            .add(SLOT_EMAIL_VERIFIED, "emailVerified", ImmutablePropCategory.SCALAR, boolean.class, false)
            .add(SLOT_PHONE_VERIFIED, "phoneVerified", ImmutablePropCategory.SCALAR, boolean.class, false)
            .add(SLOT_STATUS, "status", ImmutablePropCategory.SCALAR, UserAccountStatus.class, false)
            .add(SLOT_UPDATED_AT, "updatedAt", ImmutablePropCategory.SCALAR, OffsetDateTime.class, true)
            .add(SLOT_CREATED_AT, "createdAt", ImmutablePropCategory.SCALAR, OffsetDateTime.class, true)
            .add(SLOT_ORGANIZATION_ID, "organizationId", ImmutablePropCategory.SCALAR, UUID.class, false)
            .add(SLOT_ORG_ADMIN, "orgAdmin", ImmutablePropCategory.SCALAR, boolean.class, false)
            .add(SLOT_PROFILE, "profile", OneToOne.class, UserProfile.class, true)
            .add(SLOT_ROLES, "roles", ManyToMany.class, Role.class, false)
            .add(SLOT_SESSIONS, "sessions", OneToMany.class, Session.class, false)
            .add(SLOT_BIOMETRICS, "biometrics", OneToMany.class, Biometric.class, false)
            .add(SLOT_ORGANIZATION, "organization", ManyToOne.class, Organizations.class, false)
            .build();

        private Producer() {
        }

        public User produce(DraftConsumer<UserDraft> block) {
            return (User)Internal.produce(TYPE, null, block);
        }

        public User produce(User base, DraftConsumer<UserDraft> block) {
            return (User)Internal.produce(TYPE, base, block);
        }

        public User produce(boolean resolveImmediately, DraftConsumer<UserDraft> block) {
            return (User)Internal.produce(TYPE, null, resolveImmediately, block);
        }

        public User produce(User base, boolean resolveImmediately, DraftConsumer<UserDraft> block) {
            return (User)Internal.produce(TYPE, base, resolveImmediately, block);
        }

        /**
         * Class, not interface, for free-marker
         */
        @GeneratedBy(
                type = User.class
        )
        @JsonPropertyOrder({"dummyPropForJacksonError__", "id", "username", "email", "phone", "password", "multiFactorAuth", "emailVerified", "phoneVerified", "status", "updatedAt", "createdAt", "organizationId", "orgAdmin", "profile", "roles", "sessions", "biometrics", "organization"})
        public abstract static class Implementor implements User, ImmutableSpi {
            @Override
            public final Object __get(PropId prop) {
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		return __get(prop.asName());
                    case SLOT_ID:
                    		return id();
                    case SLOT_USERNAME:
                    		return username();
                    case SLOT_EMAIL:
                    		return email();
                    case SLOT_PHONE:
                    		return phone();
                    case SLOT_PASSWORD:
                    		return password();
                    case SLOT_MULTI_FACTOR_AUTH:
                    		return multiFactorAuth();
                    case SLOT_EMAIL_VERIFIED:
                    		return (Boolean)emailVerified();
                    case SLOT_PHONE_VERIFIED:
                    		return (Boolean)phoneVerified();
                    case SLOT_STATUS:
                    		return status();
                    case SLOT_UPDATED_AT:
                    		return updatedAt();
                    case SLOT_CREATED_AT:
                    		return createdAt();
                    case SLOT_ORGANIZATION_ID:
                    		return organizationId();
                    case SLOT_ORG_ADMIN:
                    		return (Boolean)isOrgAdmin();
                    case SLOT_PROFILE:
                    		return profile();
                    case SLOT_ROLES:
                    		return roles();
                    case SLOT_SESSIONS:
                    		return sessions();
                    case SLOT_BIOMETRICS:
                    		return biometrics();
                    case SLOT_ORGANIZATION:
                    		return organization();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.User\": \"" + prop + "\"");
                }
            }

            @Override
            public final Object __get(String prop) {
                switch (prop) {
                    case "id":
                    		return id();
                    case "username":
                    		return username();
                    case "email":
                    		return email();
                    case "phone":
                    		return phone();
                    case "password":
                    		return password();
                    case "multiFactorAuth":
                    		return multiFactorAuth();
                    case "emailVerified":
                    		return (Boolean)emailVerified();
                    case "phoneVerified":
                    		return (Boolean)phoneVerified();
                    case "status":
                    		return status();
                    case "updatedAt":
                    		return updatedAt();
                    case "createdAt":
                    		return createdAt();
                    case "organizationId":
                    		return organizationId();
                    case "orgAdmin":
                    		return (Boolean)isOrgAdmin();
                    case "profile":
                    		return profile();
                    case "roles":
                    		return roles();
                    case "sessions":
                    		return sessions();
                    case "biometrics":
                    		return biometrics();
                    case "organization":
                    		return organization();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.User\": \"" + prop + "\"");
                }
            }

            public final UUID getId() {
                return id();
            }

            public final String getUsername() {
                return username();
            }

            public final String getEmail() {
                return email();
            }

            @org.jetbrains.annotations.Nullable
            public final String getPhone() {
                return phone();
            }

            public final String getPassword() {
                return password();
            }

            public final MultiAuthType getMultiFactorAuth() {
                return multiFactorAuth();
            }

            public final boolean isEmailVerified() {
                return emailVerified();
            }

            public final boolean isPhoneVerified() {
                return phoneVerified();
            }

            public final UserAccountStatus getStatus() {
                return status();
            }

            @org.jetbrains.annotations.Nullable
            public final OffsetDateTime getUpdatedAt() {
                return updatedAt();
            }

            @org.jetbrains.annotations.Nullable
            public final OffsetDateTime getCreatedAt() {
                return createdAt();
            }

            public final UUID getOrganizationId() {
                return organizationId();
            }

            @org.jetbrains.annotations.Nullable
            public final UserProfile getProfile() {
                return profile();
            }

            public final List<Role> getRoles() {
                return roles();
            }

            public final List<Session> getSessions() {
                return sessions();
            }

            public final List<Biometric> getBiometrics() {
                return biometrics();
            }

            public final Organizations getOrganization() {
                return organization();
            }

            @Override
            public final ImmutableType __type() {
                return TYPE;
            }

            public final int getDummyPropForJacksonError__() {
                throw new ImmutableModuleRequiredException();
            }
        }

        @GeneratedBy(
                type = User.class
        )
        private static class Impl extends Implementor implements Cloneable, Serializable {
            private Visibility __visibility;

            UUID __idValue;

            String __usernameValue;

            String __emailValue;

            String __phoneValue;

            boolean __phoneLoaded = false;

            String __passwordValue;

            MultiAuthType __multiFactorAuthValue;

            boolean __emailVerifiedValue;

            boolean __emailVerifiedLoaded = false;

            boolean __phoneVerifiedValue;

            boolean __phoneVerifiedLoaded = false;

            UserAccountStatus __statusValue;

            OffsetDateTime __updatedAtValue;

            boolean __updatedAtLoaded = false;

            OffsetDateTime __createdAtValue;

            boolean __createdAtLoaded = false;

            boolean __orgAdminValue;

            boolean __orgAdminLoaded = false;

            UserProfile __profileValue;

            boolean __profileLoaded = false;

            NonSharedList<Role> __rolesValue;

            NonSharedList<Session> __sessionsValue;

            NonSharedList<Biometric> __biometricsValue;

            Organizations __organizationValue;

            Impl() {
                __visibility = Visibility.of(18);
                __visibility.show(SLOT_ORGANIZATION_ID, false);
            }

            @Override
            @JsonIgnore
            public UUID id() {
                if (__idValue == null) {
                    throw new UnloadedException(User.class, "id");
                }
                return __idValue;
            }

            @Override
            @JsonIgnore
            public String username() {
                if (__usernameValue == null) {
                    throw new UnloadedException(User.class, "username");
                }
                return __usernameValue;
            }

            @Override
            @JsonIgnore
            public String email() {
                if (__emailValue == null) {
                    throw new UnloadedException(User.class, "email");
                }
                return __emailValue;
            }

            @Override
            @JsonIgnore
            @Nullable
            public String phone() {
                if (!__phoneLoaded) {
                    throw new UnloadedException(User.class, "phone");
                }
                return __phoneValue;
            }

            @Override
            @JsonIgnore
            public String password() {
                if (__passwordValue == null) {
                    throw new UnloadedException(User.class, "password");
                }
                return __passwordValue;
            }

            @Override
            @JsonIgnore
            public MultiAuthType multiFactorAuth() {
                if (__multiFactorAuthValue == null) {
                    throw new UnloadedException(User.class, "multiFactorAuth");
                }
                return __multiFactorAuthValue;
            }

            @Override
            @JsonIgnore
            public boolean emailVerified() {
                if (!__emailVerifiedLoaded) {
                    throw new UnloadedException(User.class, "emailVerified");
                }
                return __emailVerifiedValue;
            }

            @Override
            @JsonIgnore
            public boolean phoneVerified() {
                if (!__phoneVerifiedLoaded) {
                    throw new UnloadedException(User.class, "phoneVerified");
                }
                return __phoneVerifiedValue;
            }

            @Override
            @JsonIgnore
            public UserAccountStatus status() {
                if (__statusValue == null) {
                    throw new UnloadedException(User.class, "status");
                }
                return __statusValue;
            }

            @Override
            @JsonIgnore
            @Nullable
            public OffsetDateTime updatedAt() {
                if (!__updatedAtLoaded) {
                    throw new UnloadedException(User.class, "updatedAt");
                }
                return __updatedAtValue;
            }

            @Override
            @JsonIgnore
            @Nullable
            public OffsetDateTime createdAt() {
                if (!__createdAtLoaded) {
                    throw new UnloadedException(User.class, "createdAt");
                }
                return __createdAtValue;
            }

            @Override
            @JsonIgnore
            public UUID organizationId() {
                Organizations __target = organization();
                return __target.id();
            }

            @Override
            public boolean isOrgAdmin() {
                if (!__orgAdminLoaded) {
                    throw new UnloadedException(User.class, "orgAdmin");
                }
                return __orgAdminValue;
            }

            @Override
            @JsonIgnore
            @Nullable
            public UserProfile profile() {
                if (!__profileLoaded) {
                    throw new UnloadedException(User.class, "profile");
                }
                return __profileValue;
            }

            @Override
            @JsonIgnore
            public List<Role> roles() {
                if (__rolesValue == null) {
                    throw new UnloadedException(User.class, "roles");
                }
                return __rolesValue;
            }

            @Override
            @JsonIgnore
            public List<Session> sessions() {
                if (__sessionsValue == null) {
                    throw new UnloadedException(User.class, "sessions");
                }
                return __sessionsValue;
            }

            @Override
            @JsonIgnore
            public List<Biometric> biometrics() {
                if (__biometricsValue == null) {
                    throw new UnloadedException(User.class, "biometrics");
                }
                return __biometricsValue;
            }

            @Override
            @JsonIgnore
            public Organizations organization() {
                if (__organizationValue == null) {
                    throw new UnloadedException(User.class, "organization");
                }
                return __organizationValue;
            }

            @Override
            public Impl clone() {
                try {
                    Impl copy = (Impl) super.clone();
                    Visibility originalVisibility = this.__visibility;
                    if (originalVisibility != null) {
                        Visibility newVisibility = Visibility.of(18);
                        for (int propId = 0; propId < 18; propId++) {
                            newVisibility.show(propId, originalVisibility.visible(propId));
                        }
                        copy.__visibility = newVisibility;
                    } else {
                        copy.__visibility = null;
                    }
                    return copy;
                } catch(CloneNotSupportedException ex) {
                    throw new AssertionError(ex);
                }
            }

            @Override
            public boolean __isLoaded(PropId prop) {
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		return __isLoaded(prop.asName());
                    case SLOT_ID:
                    		return __idValue != null;
                    case SLOT_USERNAME:
                    		return __usernameValue != null;
                    case SLOT_EMAIL:
                    		return __emailValue != null;
                    case SLOT_PHONE:
                    		return __phoneLoaded;
                    case SLOT_PASSWORD:
                    		return __passwordValue != null;
                    case SLOT_MULTI_FACTOR_AUTH:
                    		return __multiFactorAuthValue != null;
                    case SLOT_EMAIL_VERIFIED:
                    		return __emailVerifiedLoaded;
                    case SLOT_PHONE_VERIFIED:
                    		return __phoneVerifiedLoaded;
                    case SLOT_STATUS:
                    		return __statusValue != null;
                    case SLOT_UPDATED_AT:
                    		return __updatedAtLoaded;
                    case SLOT_CREATED_AT:
                    		return __createdAtLoaded;
                    case SLOT_ORGANIZATION_ID:
                    		return __isLoaded(PropId.byIndex(SLOT_ORGANIZATION)) && (organization() == null || 
                            	((ImmutableSpi)organization()).__isLoaded(PropId.byIndex(OrganizationsDraft.Producer.SLOT_ID)));
                    case SLOT_ORG_ADMIN:
                    		return __orgAdminLoaded;
                    case SLOT_PROFILE:
                    		return __profileLoaded;
                    case SLOT_ROLES:
                    		return __rolesValue != null;
                    case SLOT_SESSIONS:
                    		return __sessionsValue != null;
                    case SLOT_BIOMETRICS:
                    		return __biometricsValue != null;
                    case SLOT_ORGANIZATION:
                    		return __organizationValue != null;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.User\": \"" + prop + "\"");
                }
            }

            @Override
            public boolean __isLoaded(String prop) {
                switch (prop) {
                    case "id":
                    		return __idValue != null;
                    case "username":
                    		return __usernameValue != null;
                    case "email":
                    		return __emailValue != null;
                    case "phone":
                    		return __phoneLoaded;
                    case "password":
                    		return __passwordValue != null;
                    case "multiFactorAuth":
                    		return __multiFactorAuthValue != null;
                    case "emailVerified":
                    		return __emailVerifiedLoaded;
                    case "phoneVerified":
                    		return __phoneVerifiedLoaded;
                    case "status":
                    		return __statusValue != null;
                    case "updatedAt":
                    		return __updatedAtLoaded;
                    case "createdAt":
                    		return __createdAtLoaded;
                    case "organizationId":
                    		return __isLoaded(PropId.byIndex(SLOT_ORGANIZATION)) && (organization() == null || 
                            	((ImmutableSpi)organization()).__isLoaded(PropId.byIndex(OrganizationsDraft.Producer.SLOT_ID)));
                    case "orgAdmin":
                    		return __orgAdminLoaded;
                    case "profile":
                    		return __profileLoaded;
                    case "roles":
                    		return __rolesValue != null;
                    case "sessions":
                    		return __sessionsValue != null;
                    case "biometrics":
                    		return __biometricsValue != null;
                    case "organization":
                    		return __organizationValue != null;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.User\": \"" + prop + "\"");
                }
            }

            @Override
            public boolean __isVisible(PropId prop) {
                if (__visibility == null) {
                    return true;
                }
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		return __isVisible(prop.asName());
                    case SLOT_ID:
                    		return __visibility.visible(SLOT_ID);
                    case SLOT_USERNAME:
                    		return __visibility.visible(SLOT_USERNAME);
                    case SLOT_EMAIL:
                    		return __visibility.visible(SLOT_EMAIL);
                    case SLOT_PHONE:
                    		return __visibility.visible(SLOT_PHONE);
                    case SLOT_PASSWORD:
                    		return __visibility.visible(SLOT_PASSWORD);
                    case SLOT_MULTI_FACTOR_AUTH:
                    		return __visibility.visible(SLOT_MULTI_FACTOR_AUTH);
                    case SLOT_EMAIL_VERIFIED:
                    		return __visibility.visible(SLOT_EMAIL_VERIFIED);
                    case SLOT_PHONE_VERIFIED:
                    		return __visibility.visible(SLOT_PHONE_VERIFIED);
                    case SLOT_STATUS:
                    		return __visibility.visible(SLOT_STATUS);
                    case SLOT_UPDATED_AT:
                    		return __visibility.visible(SLOT_UPDATED_AT);
                    case SLOT_CREATED_AT:
                    		return __visibility.visible(SLOT_CREATED_AT);
                    case SLOT_ORGANIZATION_ID:
                    		return __visibility.visible(SLOT_ORGANIZATION_ID);
                    case SLOT_ORG_ADMIN:
                    		return __visibility.visible(SLOT_ORG_ADMIN);
                    case SLOT_PROFILE:
                    		return __visibility.visible(SLOT_PROFILE);
                    case SLOT_ROLES:
                    		return __visibility.visible(SLOT_ROLES);
                    case SLOT_SESSIONS:
                    		return __visibility.visible(SLOT_SESSIONS);
                    case SLOT_BIOMETRICS:
                    		return __visibility.visible(SLOT_BIOMETRICS);
                    case SLOT_ORGANIZATION:
                    		return __visibility.visible(SLOT_ORGANIZATION);
                    default: return true;
                }
            }

            @Override
            public boolean __isVisible(String prop) {
                if (__visibility == null) {
                    return true;
                }
                switch (prop) {
                    case "id":
                    		return __visibility.visible(SLOT_ID);
                    case "username":
                    		return __visibility.visible(SLOT_USERNAME);
                    case "email":
                    		return __visibility.visible(SLOT_EMAIL);
                    case "phone":
                    		return __visibility.visible(SLOT_PHONE);
                    case "password":
                    		return __visibility.visible(SLOT_PASSWORD);
                    case "multiFactorAuth":
                    		return __visibility.visible(SLOT_MULTI_FACTOR_AUTH);
                    case "emailVerified":
                    		return __visibility.visible(SLOT_EMAIL_VERIFIED);
                    case "phoneVerified":
                    		return __visibility.visible(SLOT_PHONE_VERIFIED);
                    case "status":
                    		return __visibility.visible(SLOT_STATUS);
                    case "updatedAt":
                    		return __visibility.visible(SLOT_UPDATED_AT);
                    case "createdAt":
                    		return __visibility.visible(SLOT_CREATED_AT);
                    case "organizationId":
                    		return __visibility.visible(SLOT_ORGANIZATION_ID);
                    case "orgAdmin":
                    		return __visibility.visible(SLOT_ORG_ADMIN);
                    case "profile":
                    		return __visibility.visible(SLOT_PROFILE);
                    case "roles":
                    		return __visibility.visible(SLOT_ROLES);
                    case "sessions":
                    		return __visibility.visible(SLOT_SESSIONS);
                    case "biometrics":
                    		return __visibility.visible(SLOT_BIOMETRICS);
                    case "organization":
                    		return __visibility.visible(SLOT_ORGANIZATION);
                    default: return true;
                }
            }

            @Override
            public int hashCode() {
                int hash = __visibility != null ? __visibility.hashCode() : 0;
                if (__idValue != null) {
                    hash = 31 * hash + __idValue.hashCode();
                    // If entity-id is loaded, return directly
                    return hash;
                }
                if (__usernameValue != null) {
                    hash = 31 * hash + __usernameValue.hashCode();
                }
                if (__emailValue != null) {
                    hash = 31 * hash + __emailValue.hashCode();
                }
                if (__phoneLoaded && __phoneValue != null) {
                    hash = 31 * hash + __phoneValue.hashCode();
                }
                if (__passwordValue != null) {
                    hash = 31 * hash + __passwordValue.hashCode();
                }
                if (__multiFactorAuthValue != null) {
                    hash = 31 * hash + __multiFactorAuthValue.hashCode();
                }
                if (__emailVerifiedLoaded) {
                    hash = 31 * hash + Boolean.hashCode(__emailVerifiedValue);
                }
                if (__phoneVerifiedLoaded) {
                    hash = 31 * hash + Boolean.hashCode(__phoneVerifiedValue);
                }
                if (__statusValue != null) {
                    hash = 31 * hash + __statusValue.hashCode();
                }
                if (__updatedAtLoaded && __updatedAtValue != null) {
                    hash = 31 * hash + __updatedAtValue.hashCode();
                }
                if (__createdAtLoaded && __createdAtValue != null) {
                    hash = 31 * hash + __createdAtValue.hashCode();
                }
                if (__orgAdminLoaded) {
                    hash = 31 * hash + Boolean.hashCode(__orgAdminValue);
                }
                if (__profileLoaded && __profileValue != null) {
                    hash = 31 * hash + __profileValue.hashCode();
                }
                if (__rolesValue != null) {
                    hash = 31 * hash + __rolesValue.hashCode();
                }
                if (__sessionsValue != null) {
                    hash = 31 * hash + __sessionsValue.hashCode();
                }
                if (__biometricsValue != null) {
                    hash = 31 * hash + __biometricsValue.hashCode();
                }
                if (__organizationValue != null) {
                    hash = 31 * hash + __organizationValue.hashCode();
                }
                return hash;
            }

            private int __shallowHashCode() {
                int hash = __visibility != null ? __visibility.hashCode() : 0;
                if (__idValue != null) {
                    hash = 31 * hash + System.identityHashCode(__idValue);
                }
                if (__usernameValue != null) {
                    hash = 31 * hash + System.identityHashCode(__usernameValue);
                }
                if (__emailValue != null) {
                    hash = 31 * hash + System.identityHashCode(__emailValue);
                }
                if (__phoneLoaded) {
                    hash = 31 * hash + System.identityHashCode(__phoneValue);
                }
                if (__passwordValue != null) {
                    hash = 31 * hash + System.identityHashCode(__passwordValue);
                }
                if (__multiFactorAuthValue != null) {
                    hash = 31 * hash + System.identityHashCode(__multiFactorAuthValue);
                }
                if (__emailVerifiedLoaded) {
                    hash = 31 * hash + Boolean.hashCode(__emailVerifiedValue);
                }
                if (__phoneVerifiedLoaded) {
                    hash = 31 * hash + Boolean.hashCode(__phoneVerifiedValue);
                }
                if (__statusValue != null) {
                    hash = 31 * hash + System.identityHashCode(__statusValue);
                }
                if (__updatedAtLoaded) {
                    hash = 31 * hash + System.identityHashCode(__updatedAtValue);
                }
                if (__createdAtLoaded) {
                    hash = 31 * hash + System.identityHashCode(__createdAtValue);
                }
                if (__orgAdminLoaded) {
                    hash = 31 * hash + Boolean.hashCode(__orgAdminValue);
                }
                if (__profileLoaded) {
                    hash = 31 * hash + System.identityHashCode(__profileValue);
                }
                if (__rolesValue != null) {
                    hash = 31 * hash + System.identityHashCode(__rolesValue);
                }
                if (__sessionsValue != null) {
                    hash = 31 * hash + System.identityHashCode(__sessionsValue);
                }
                if (__biometricsValue != null) {
                    hash = 31 * hash + System.identityHashCode(__biometricsValue);
                }
                if (__organizationValue != null) {
                    hash = 31 * hash + System.identityHashCode(__organizationValue);
                }
                return hash;
            }

            @Override
            public int __hashCode(boolean shallow) {
                return shallow ? __shallowHashCode() : hashCode();
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null || !(obj instanceof Implementor)) {
                    return false;
                }
                Implementor __other = (Implementor)obj;
                if (__isVisible(PropId.byIndex(SLOT_ID)) != __other.__isVisible(PropId.byIndex(SLOT_ID))) {
                    return false;
                }
                boolean __idLoaded = __idValue != null;
                if (__idLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ID))) {
                    return false;
                }
                if (__idLoaded) {
                    // If entity-id is loaded, return directly
                    return Objects.equals(__idValue, __other.id());
                }
                if (__isVisible(PropId.byIndex(SLOT_USERNAME)) != __other.__isVisible(PropId.byIndex(SLOT_USERNAME))) {
                    return false;
                }
                boolean __usernameLoaded = __usernameValue != null;
                if (__usernameLoaded != __other.__isLoaded(PropId.byIndex(SLOT_USERNAME))) {
                    return false;
                }
                if (__usernameLoaded && !Objects.equals(__usernameValue, __other.username())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_EMAIL)) != __other.__isVisible(PropId.byIndex(SLOT_EMAIL))) {
                    return false;
                }
                boolean __emailLoaded = __emailValue != null;
                if (__emailLoaded != __other.__isLoaded(PropId.byIndex(SLOT_EMAIL))) {
                    return false;
                }
                if (__emailLoaded && !Objects.equals(__emailValue, __other.email())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_PHONE)) != __other.__isVisible(PropId.byIndex(SLOT_PHONE))) {
                    return false;
                }
                boolean __phoneLoaded = this.__phoneLoaded;
                if (__phoneLoaded != __other.__isLoaded(PropId.byIndex(SLOT_PHONE))) {
                    return false;
                }
                if (__phoneLoaded && !Objects.equals(__phoneValue, __other.phone())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_PASSWORD)) != __other.__isVisible(PropId.byIndex(SLOT_PASSWORD))) {
                    return false;
                }
                boolean __passwordLoaded = __passwordValue != null;
                if (__passwordLoaded != __other.__isLoaded(PropId.byIndex(SLOT_PASSWORD))) {
                    return false;
                }
                if (__passwordLoaded && !Objects.equals(__passwordValue, __other.password())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_MULTI_FACTOR_AUTH)) != __other.__isVisible(PropId.byIndex(SLOT_MULTI_FACTOR_AUTH))) {
                    return false;
                }
                boolean __multiFactorAuthLoaded = __multiFactorAuthValue != null;
                if (__multiFactorAuthLoaded != __other.__isLoaded(PropId.byIndex(SLOT_MULTI_FACTOR_AUTH))) {
                    return false;
                }
                if (__multiFactorAuthLoaded && !Objects.equals(__multiFactorAuthValue, __other.multiFactorAuth())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_EMAIL_VERIFIED)) != __other.__isVisible(PropId.byIndex(SLOT_EMAIL_VERIFIED))) {
                    return false;
                }
                boolean __emailVerifiedLoaded = this.__emailVerifiedLoaded;
                if (__emailVerifiedLoaded != __other.__isLoaded(PropId.byIndex(SLOT_EMAIL_VERIFIED))) {
                    return false;
                }
                if (__emailVerifiedLoaded && __emailVerifiedValue != __other.emailVerified()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_PHONE_VERIFIED)) != __other.__isVisible(PropId.byIndex(SLOT_PHONE_VERIFIED))) {
                    return false;
                }
                boolean __phoneVerifiedLoaded = this.__phoneVerifiedLoaded;
                if (__phoneVerifiedLoaded != __other.__isLoaded(PropId.byIndex(SLOT_PHONE_VERIFIED))) {
                    return false;
                }
                if (__phoneVerifiedLoaded && __phoneVerifiedValue != __other.phoneVerified()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_STATUS)) != __other.__isVisible(PropId.byIndex(SLOT_STATUS))) {
                    return false;
                }
                boolean __statusLoaded = __statusValue != null;
                if (__statusLoaded != __other.__isLoaded(PropId.byIndex(SLOT_STATUS))) {
                    return false;
                }
                if (__statusLoaded && !Objects.equals(__statusValue, __other.status())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_UPDATED_AT)) != __other.__isVisible(PropId.byIndex(SLOT_UPDATED_AT))) {
                    return false;
                }
                boolean __updatedAtLoaded = this.__updatedAtLoaded;
                if (__updatedAtLoaded != __other.__isLoaded(PropId.byIndex(SLOT_UPDATED_AT))) {
                    return false;
                }
                if (__updatedAtLoaded && !Objects.equals(__updatedAtValue, __other.updatedAt())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_CREATED_AT)) != __other.__isVisible(PropId.byIndex(SLOT_CREATED_AT))) {
                    return false;
                }
                boolean __createdAtLoaded = this.__createdAtLoaded;
                if (__createdAtLoaded != __other.__isLoaded(PropId.byIndex(SLOT_CREATED_AT))) {
                    return false;
                }
                if (__createdAtLoaded && !Objects.equals(__createdAtValue, __other.createdAt())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ORGANIZATION_ID)) != __other.__isVisible(PropId.byIndex(SLOT_ORGANIZATION_ID))) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ORG_ADMIN)) != __other.__isVisible(PropId.byIndex(SLOT_ORG_ADMIN))) {
                    return false;
                }
                boolean __orgAdminLoaded = this.__orgAdminLoaded;
                if (__orgAdminLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ORG_ADMIN))) {
                    return false;
                }
                if (__orgAdminLoaded && __orgAdminValue != __other.isOrgAdmin()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_PROFILE)) != __other.__isVisible(PropId.byIndex(SLOT_PROFILE))) {
                    return false;
                }
                boolean __profileLoaded = this.__profileLoaded;
                if (__profileLoaded != __other.__isLoaded(PropId.byIndex(SLOT_PROFILE))) {
                    return false;
                }
                if (__profileLoaded && !Objects.equals(__profileValue, __other.profile())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ROLES)) != __other.__isVisible(PropId.byIndex(SLOT_ROLES))) {
                    return false;
                }
                boolean __rolesLoaded = __rolesValue != null;
                if (__rolesLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ROLES))) {
                    return false;
                }
                if (__rolesLoaded && !Objects.equals(__rolesValue, __other.roles())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_SESSIONS)) != __other.__isVisible(PropId.byIndex(SLOT_SESSIONS))) {
                    return false;
                }
                boolean __sessionsLoaded = __sessionsValue != null;
                if (__sessionsLoaded != __other.__isLoaded(PropId.byIndex(SLOT_SESSIONS))) {
                    return false;
                }
                if (__sessionsLoaded && !Objects.equals(__sessionsValue, __other.sessions())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_BIOMETRICS)) != __other.__isVisible(PropId.byIndex(SLOT_BIOMETRICS))) {
                    return false;
                }
                boolean __biometricsLoaded = __biometricsValue != null;
                if (__biometricsLoaded != __other.__isLoaded(PropId.byIndex(SLOT_BIOMETRICS))) {
                    return false;
                }
                if (__biometricsLoaded && !Objects.equals(__biometricsValue, __other.biometrics())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ORGANIZATION)) != __other.__isVisible(PropId.byIndex(SLOT_ORGANIZATION))) {
                    return false;
                }
                boolean __organizationLoaded = __organizationValue != null;
                if (__organizationLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ORGANIZATION))) {
                    return false;
                }
                if (__organizationLoaded && !Objects.equals(__organizationValue, __other.organization())) {
                    return false;
                }
                return true;
            }

            private boolean __shallowEquals(Object obj) {
                if (obj == null || !(obj instanceof Implementor)) {
                    return false;
                }
                Implementor __other = (Implementor)obj;
                if (__isVisible(PropId.byIndex(SLOT_ID)) != __other.__isVisible(PropId.byIndex(SLOT_ID))) {
                    return false;
                }
                boolean __idLoaded = __idValue != null;
                if (__idLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ID))) {
                    return false;
                }
                if (__idLoaded && __idValue != __other.id()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_USERNAME)) != __other.__isVisible(PropId.byIndex(SLOT_USERNAME))) {
                    return false;
                }
                boolean __usernameLoaded = __usernameValue != null;
                if (__usernameLoaded != __other.__isLoaded(PropId.byIndex(SLOT_USERNAME))) {
                    return false;
                }
                if (__usernameLoaded && __usernameValue != __other.username()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_EMAIL)) != __other.__isVisible(PropId.byIndex(SLOT_EMAIL))) {
                    return false;
                }
                boolean __emailLoaded = __emailValue != null;
                if (__emailLoaded != __other.__isLoaded(PropId.byIndex(SLOT_EMAIL))) {
                    return false;
                }
                if (__emailLoaded && __emailValue != __other.email()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_PHONE)) != __other.__isVisible(PropId.byIndex(SLOT_PHONE))) {
                    return false;
                }
                boolean __phoneLoaded = this.__phoneLoaded;
                if (__phoneLoaded != __other.__isLoaded(PropId.byIndex(SLOT_PHONE))) {
                    return false;
                }
                if (__phoneLoaded && __phoneValue != __other.phone()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_PASSWORD)) != __other.__isVisible(PropId.byIndex(SLOT_PASSWORD))) {
                    return false;
                }
                boolean __passwordLoaded = __passwordValue != null;
                if (__passwordLoaded != __other.__isLoaded(PropId.byIndex(SLOT_PASSWORD))) {
                    return false;
                }
                if (__passwordLoaded && __passwordValue != __other.password()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_MULTI_FACTOR_AUTH)) != __other.__isVisible(PropId.byIndex(SLOT_MULTI_FACTOR_AUTH))) {
                    return false;
                }
                boolean __multiFactorAuthLoaded = __multiFactorAuthValue != null;
                if (__multiFactorAuthLoaded != __other.__isLoaded(PropId.byIndex(SLOT_MULTI_FACTOR_AUTH))) {
                    return false;
                }
                if (__multiFactorAuthLoaded && __multiFactorAuthValue != __other.multiFactorAuth()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_EMAIL_VERIFIED)) != __other.__isVisible(PropId.byIndex(SLOT_EMAIL_VERIFIED))) {
                    return false;
                }
                boolean __emailVerifiedLoaded = this.__emailVerifiedLoaded;
                if (__emailVerifiedLoaded != __other.__isLoaded(PropId.byIndex(SLOT_EMAIL_VERIFIED))) {
                    return false;
                }
                if (__emailVerifiedLoaded && __emailVerifiedValue != __other.emailVerified()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_PHONE_VERIFIED)) != __other.__isVisible(PropId.byIndex(SLOT_PHONE_VERIFIED))) {
                    return false;
                }
                boolean __phoneVerifiedLoaded = this.__phoneVerifiedLoaded;
                if (__phoneVerifiedLoaded != __other.__isLoaded(PropId.byIndex(SLOT_PHONE_VERIFIED))) {
                    return false;
                }
                if (__phoneVerifiedLoaded && __phoneVerifiedValue != __other.phoneVerified()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_STATUS)) != __other.__isVisible(PropId.byIndex(SLOT_STATUS))) {
                    return false;
                }
                boolean __statusLoaded = __statusValue != null;
                if (__statusLoaded != __other.__isLoaded(PropId.byIndex(SLOT_STATUS))) {
                    return false;
                }
                if (__statusLoaded && __statusValue != __other.status()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_UPDATED_AT)) != __other.__isVisible(PropId.byIndex(SLOT_UPDATED_AT))) {
                    return false;
                }
                boolean __updatedAtLoaded = this.__updatedAtLoaded;
                if (__updatedAtLoaded != __other.__isLoaded(PropId.byIndex(SLOT_UPDATED_AT))) {
                    return false;
                }
                if (__updatedAtLoaded && __updatedAtValue != __other.updatedAt()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_CREATED_AT)) != __other.__isVisible(PropId.byIndex(SLOT_CREATED_AT))) {
                    return false;
                }
                boolean __createdAtLoaded = this.__createdAtLoaded;
                if (__createdAtLoaded != __other.__isLoaded(PropId.byIndex(SLOT_CREATED_AT))) {
                    return false;
                }
                if (__createdAtLoaded && __createdAtValue != __other.createdAt()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ORGANIZATION_ID)) != __other.__isVisible(PropId.byIndex(SLOT_ORGANIZATION_ID))) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ORG_ADMIN)) != __other.__isVisible(PropId.byIndex(SLOT_ORG_ADMIN))) {
                    return false;
                }
                boolean __orgAdminLoaded = this.__orgAdminLoaded;
                if (__orgAdminLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ORG_ADMIN))) {
                    return false;
                }
                if (__orgAdminLoaded && __orgAdminValue != __other.isOrgAdmin()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_PROFILE)) != __other.__isVisible(PropId.byIndex(SLOT_PROFILE))) {
                    return false;
                }
                boolean __profileLoaded = this.__profileLoaded;
                if (__profileLoaded != __other.__isLoaded(PropId.byIndex(SLOT_PROFILE))) {
                    return false;
                }
                if (__profileLoaded && __profileValue != __other.profile()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ROLES)) != __other.__isVisible(PropId.byIndex(SLOT_ROLES))) {
                    return false;
                }
                boolean __rolesLoaded = __rolesValue != null;
                if (__rolesLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ROLES))) {
                    return false;
                }
                if (__rolesLoaded && __rolesValue != __other.roles()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_SESSIONS)) != __other.__isVisible(PropId.byIndex(SLOT_SESSIONS))) {
                    return false;
                }
                boolean __sessionsLoaded = __sessionsValue != null;
                if (__sessionsLoaded != __other.__isLoaded(PropId.byIndex(SLOT_SESSIONS))) {
                    return false;
                }
                if (__sessionsLoaded && __sessionsValue != __other.sessions()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_BIOMETRICS)) != __other.__isVisible(PropId.byIndex(SLOT_BIOMETRICS))) {
                    return false;
                }
                boolean __biometricsLoaded = __biometricsValue != null;
                if (__biometricsLoaded != __other.__isLoaded(PropId.byIndex(SLOT_BIOMETRICS))) {
                    return false;
                }
                if (__biometricsLoaded && __biometricsValue != __other.biometrics()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ORGANIZATION)) != __other.__isVisible(PropId.byIndex(SLOT_ORGANIZATION))) {
                    return false;
                }
                boolean __organizationLoaded = __organizationValue != null;
                if (__organizationLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ORGANIZATION))) {
                    return false;
                }
                if (__organizationLoaded && __organizationValue != __other.organization()) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean __equals(Object obj, boolean shallow) {
                return shallow ? __shallowEquals(obj) : equals(obj);
            }

            @Override
            public String toString() {
                return ImmutableObjects.toString(this);
            }
        }

        @GeneratedBy(
                type = User.class
        )
        private static class DraftImpl extends Implementor implements DraftSpi, UserDraft {
            private DraftContext __ctx;

            private Impl __base;

            private Impl __modified;

            private boolean __resolving;

            private User __resolved;

            DraftImpl(DraftContext ctx, User base) {
                __ctx = ctx;
                if (base != null) {
                    __base = (Impl)base;
                }
                else {
                    __modified = new Impl();
                }
            }

            @Override
            public boolean __isLoaded(PropId prop) {
                return (__modified!= null ? __modified : __base).__isLoaded(prop);
            }

            @Override
            public boolean __isLoaded(String prop) {
                return (__modified!= null ? __modified : __base).__isLoaded(prop);
            }

            @Override
            public boolean __isVisible(PropId prop) {
                return (__modified!= null ? __modified : __base).__isVisible(prop);
            }

            @Override
            public boolean __isVisible(String prop) {
                return (__modified!= null ? __modified : __base).__isVisible(prop);
            }

            @Override
            public int hashCode() {
                return (__modified!= null ? __modified : __base).hashCode();
            }

            @Override
            public int __hashCode(boolean shallow) {
                return (__modified!= null ? __modified : __base).__hashCode(shallow);
            }

            @Override
            public boolean equals(Object obj) {
                return (__modified!= null ? __modified : __base).equals(obj);
            }

            @Override
            public boolean __equals(Object obj, boolean shallow) {
                return (__modified!= null ? __modified : __base).__equals(obj, shallow);
            }

            @Override
            public String toString() {
                return ImmutableObjects.toString(this);
            }

            @Override
            @JsonIgnore
            public UUID id() {
                return (__modified!= null ? __modified : __base).id();
            }

            @Override
            public UserDraft setId(UUID id) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (id == null) {
                    throw new IllegalArgumentException(
                        "'id' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__idValue = id;
                return this;
            }

            @Override
            @JsonIgnore
            public String username() {
                return (__modified!= null ? __modified : __base).username();
            }

            @Override
            public UserDraft setUsername(String username) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (username == null) {
                    throw new IllegalArgumentException(
                        "'username' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__usernameValue = username;
                return this;
            }

            @Override
            @JsonIgnore
            public String email() {
                return (__modified!= null ? __modified : __base).email();
            }

            @Override
            public UserDraft setEmail(String email) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (email == null) {
                    throw new IllegalArgumentException(
                        "'email' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__emailValue = email;
                return this;
            }

            @Override
            @JsonIgnore
            @Nullable
            public String phone() {
                return (__modified!= null ? __modified : __base).phone();
            }

            @Override
            public UserDraft setPhone(String phone) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__phoneValue = phone;
                __tmpModified.__phoneLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            public String password() {
                return (__modified!= null ? __modified : __base).password();
            }

            @Override
            public UserDraft setPassword(String password) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (password == null) {
                    throw new IllegalArgumentException(
                        "'password' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__passwordValue = password;
                return this;
            }

            @Override
            @JsonIgnore
            public MultiAuthType multiFactorAuth() {
                return (__modified!= null ? __modified : __base).multiFactorAuth();
            }

            @Override
            public UserDraft setMultiFactorAuth(MultiAuthType multiFactorAuth) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (multiFactorAuth == null) {
                    throw new IllegalArgumentException(
                        "'multiFactorAuth' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__multiFactorAuthValue = multiFactorAuth;
                return this;
            }

            @Override
            @JsonIgnore
            public boolean emailVerified() {
                return (__modified!= null ? __modified : __base).emailVerified();
            }

            @Override
            public UserDraft setEmailVerified(boolean emailVerified) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__emailVerifiedValue = emailVerified;
                __tmpModified.__emailVerifiedLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            public boolean phoneVerified() {
                return (__modified!= null ? __modified : __base).phoneVerified();
            }

            @Override
            public UserDraft setPhoneVerified(boolean phoneVerified) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__phoneVerifiedValue = phoneVerified;
                __tmpModified.__phoneVerifiedLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            public UserAccountStatus status() {
                return (__modified!= null ? __modified : __base).status();
            }

            @Override
            public UserDraft setStatus(UserAccountStatus status) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (status == null) {
                    throw new IllegalArgumentException(
                        "'status' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__statusValue = status;
                return this;
            }

            @Override
            @JsonIgnore
            @Nullable
            public OffsetDateTime updatedAt() {
                return (__modified!= null ? __modified : __base).updatedAt();
            }

            @Override
            public UserDraft setUpdatedAt(OffsetDateTime updatedAt) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__updatedAtValue = updatedAt;
                __tmpModified.__updatedAtLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            @Nullable
            public OffsetDateTime createdAt() {
                return (__modified!= null ? __modified : __base).createdAt();
            }

            @Override
            public UserDraft setCreatedAt(OffsetDateTime createdAt) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__createdAtValue = createdAt;
                __tmpModified.__createdAtLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            public UUID organizationId() {
                Organizations __target = organization();
                return __target.id();
            }

            @Override
            public UserDraft setOrganizationId(UUID organizationId) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (organizationId != null) {
                    setOrganization(ImmutableObjects.makeIdOnly(Organizations.class, organizationId));
                } else {
                    setOrganization(null);
                }
                return this;
            }

            @Override
            public boolean isOrgAdmin() {
                return (__modified!= null ? __modified : __base).isOrgAdmin();
            }

            @Override
            public UserDraft setOrgAdmin(boolean orgAdmin) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__orgAdminValue = orgAdmin;
                __tmpModified.__orgAdminLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            @Nullable
            public UserProfileDraft profile() {
                return __ctx.toDraftObject((__modified!= null ? __modified : __base).profile());
            }

            @Override
            public UserProfileDraft profile(boolean autoCreate) {
                if (autoCreate && (!__isLoaded(PropId.byIndex(SLOT_PROFILE)) || profile() == null)) {
                    setProfile(UserProfileDraft.$.produce(null, null));
                }
                return __ctx.toDraftObject((__modified!= null ? __modified : __base).profile());
            }

            @Override
            public UserDraft setProfile(UserProfile profile) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__profileValue = profile;
                __tmpModified.__profileLoaded = true;
                return this;
            }

            @Nullable
            @JsonIgnore
            @Override
            public UUID profileId() {
                UserProfile profile = profile();
                if (profile == null) {
                    return null;
                }
                return profile.id();
            }

            @OldChain
            @Override
            public UserDraft setProfileId(@Nullable UUID profileId) {
                if (profileId == null) {
                    setProfile(null);
                    return this;
                }
                profile(true).setId(profileId);
                return this;
            }

            @Override
            public UserDraft applyProfile(DraftConsumer<UserProfileDraft> block) {
                applyProfile(null, block);
                return this;
            }

            @Override
            public UserDraft applyProfile(UserProfile base, DraftConsumer<UserProfileDraft> block) {
                setProfile(UserProfileDraft.$.produce(base, block));
                return this;
            }

            @Override
            @JsonIgnore
            public List<Role> roles() {
                return __ctx.toDraftList((__modified!= null ? __modified : __base).roles(), Role.class, true);
            }

            @Override
            public List<RoleDraft> roles(boolean autoCreate) {
                if (autoCreate && (!__isLoaded(PropId.byIndex(SLOT_ROLES)))) {
                    setRoles(new ArrayList<>());
                }
                return __ctx.toDraftList((__modified!= null ? __modified : __base).roles(), Role.class, true);
            }

            @Override
            public UserDraft setRoles(List<Role> roles) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (roles == null) {
                    throw new IllegalArgumentException(
                        "'roles' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__rolesValue = NonSharedList.of(__tmpModified.__rolesValue, roles);
                return this;
            }

            @Override
            public UserDraft addIntoRoles(DraftConsumer<RoleDraft> block) {
                addIntoRoles(null, block);
                return this;
            }

            @Override
            public UserDraft addIntoRoles(Role base, DraftConsumer<RoleDraft> block) {
                roles(true).add((RoleDraft)RoleDraft.$.produce(base, block));
                return this;
            }

            @Override
            @JsonIgnore
            public List<Session> sessions() {
                return __ctx.toDraftList((__modified!= null ? __modified : __base).sessions(), Session.class, true);
            }

            @Override
            public List<SessionDraft> sessions(boolean autoCreate) {
                if (autoCreate && (!__isLoaded(PropId.byIndex(SLOT_SESSIONS)))) {
                    setSessions(new ArrayList<>());
                }
                return __ctx.toDraftList((__modified!= null ? __modified : __base).sessions(), Session.class, true);
            }

            @Override
            public UserDraft setSessions(List<Session> sessions) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (sessions == null) {
                    throw new IllegalArgumentException(
                        "'sessions' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__sessionsValue = NonSharedList.of(__tmpModified.__sessionsValue, sessions);
                return this;
            }

            @Override
            public UserDraft addIntoSessions(DraftConsumer<SessionDraft> block) {
                addIntoSessions(null, block);
                return this;
            }

            @Override
            public UserDraft addIntoSessions(Session base, DraftConsumer<SessionDraft> block) {
                sessions(true).add((SessionDraft)SessionDraft.$.produce(base, block));
                return this;
            }

            @Override
            @JsonIgnore
            public List<Biometric> biometrics() {
                return __ctx.toDraftList((__modified!= null ? __modified : __base).biometrics(), Biometric.class, true);
            }

            @Override
            public List<BiometricDraft> biometrics(boolean autoCreate) {
                if (autoCreate && (!__isLoaded(PropId.byIndex(SLOT_BIOMETRICS)))) {
                    setBiometrics(new ArrayList<>());
                }
                return __ctx.toDraftList((__modified!= null ? __modified : __base).biometrics(), Biometric.class, true);
            }

            @Override
            public UserDraft setBiometrics(List<Biometric> biometrics) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (biometrics == null) {
                    throw new IllegalArgumentException(
                        "'biometrics' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__biometricsValue = NonSharedList.of(__tmpModified.__biometricsValue, biometrics);
                return this;
            }

            @Override
            public UserDraft addIntoBiometrics(DraftConsumer<BiometricDraft> block) {
                addIntoBiometrics(null, block);
                return this;
            }

            @Override
            public UserDraft addIntoBiometrics(Biometric base,
                    DraftConsumer<BiometricDraft> block) {
                biometrics(true).add((BiometricDraft)BiometricDraft.$.produce(base, block));
                return this;
            }

            @Override
            @JsonIgnore
            public OrganizationsDraft organization() {
                return __ctx.toDraftObject((__modified!= null ? __modified : __base).organization());
            }

            @Override
            public OrganizationsDraft organization(boolean autoCreate) {
                if (autoCreate && (!__isLoaded(PropId.byIndex(SLOT_ORGANIZATION)))) {
                    setOrganization(OrganizationsDraft.$.produce(null, null));
                }
                return __ctx.toDraftObject((__modified!= null ? __modified : __base).organization());
            }

            @Override
            public UserDraft setOrganization(Organizations organization) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (organization == null) {
                    throw new IllegalArgumentException(
                        "'organization' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__organizationValue = organization;
                return this;
            }

            @Override
            public UserDraft applyOrganization(DraftConsumer<OrganizationsDraft> block) {
                applyOrganization(null, block);
                return this;
            }

            @Override
            public UserDraft applyOrganization(Organizations base,
                    DraftConsumer<OrganizationsDraft> block) {
                setOrganization(OrganizationsDraft.$.produce(base, block));
                return this;
            }

            @SuppressWarnings("all")
            @Override
            public void __set(PropId prop, Object value) {
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		__set(prop.asName(), value);
                    return;
                    case SLOT_ID:
                    		setId((UUID)value);break;
                    case SLOT_USERNAME:
                    		setUsername((String)value);break;
                    case SLOT_EMAIL:
                    		setEmail((String)value);break;
                    case SLOT_PHONE:
                    		setPhone((String)value);break;
                    case SLOT_PASSWORD:
                    		setPassword((String)value);break;
                    case SLOT_MULTI_FACTOR_AUTH:
                    		setMultiFactorAuth((MultiAuthType)value);break;
                    case SLOT_EMAIL_VERIFIED:
                    		if (value == null) throw new IllegalArgumentException("'emailVerified' cannot be null, if you want to set null, please use any annotation whose simple name is \"Nullable\" to decorate the property");
                            setEmailVerified((Boolean)value);
                            break;
                    case SLOT_PHONE_VERIFIED:
                    		if (value == null) throw new IllegalArgumentException("'phoneVerified' cannot be null, if you want to set null, please use any annotation whose simple name is \"Nullable\" to decorate the property");
                            setPhoneVerified((Boolean)value);
                            break;
                    case SLOT_STATUS:
                    		setStatus((UserAccountStatus)value);break;
                    case SLOT_UPDATED_AT:
                    		setUpdatedAt((OffsetDateTime)value);break;
                    case SLOT_CREATED_AT:
                    		setCreatedAt((OffsetDateTime)value);break;
                    case SLOT_ORGANIZATION_ID:
                    		setOrganizationId((UUID)value);break;
                    case SLOT_ORG_ADMIN:
                    		if (value == null) throw new IllegalArgumentException("'orgAdmin' cannot be null, if you want to set null, please use any annotation whose simple name is \"Nullable\" to decorate the property");
                            setOrgAdmin((Boolean)value);
                            break;
                    case SLOT_PROFILE:
                    		setProfile((UserProfile)value);break;
                    case SLOT_ROLES:
                    		setRoles((List<Role>)value);break;
                    case SLOT_SESSIONS:
                    		setSessions((List<Session>)value);break;
                    case SLOT_BIOMETRICS:
                    		setBiometrics((List<Biometric>)value);break;
                    case SLOT_ORGANIZATION:
                    		setOrganization((Organizations)value);break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.User\": \"" + prop + "\"");
                }
            }

            @SuppressWarnings("all")
            @Override
            public void __set(String prop, Object value) {
                switch (prop) {
                    case "id":
                    		setId((UUID)value);break;
                    case "username":
                    		setUsername((String)value);break;
                    case "email":
                    		setEmail((String)value);break;
                    case "phone":
                    		setPhone((String)value);break;
                    case "password":
                    		setPassword((String)value);break;
                    case "multiFactorAuth":
                    		setMultiFactorAuth((MultiAuthType)value);break;
                    case "emailVerified":
                    		if (value == null) throw new IllegalArgumentException("'emailVerified' cannot be null, if you want to set null, please use any annotation whose simple name is \"Nullable\" to decorate the property");
                            setEmailVerified((Boolean)value);
                            break;
                    case "phoneVerified":
                    		if (value == null) throw new IllegalArgumentException("'phoneVerified' cannot be null, if you want to set null, please use any annotation whose simple name is \"Nullable\" to decorate the property");
                            setPhoneVerified((Boolean)value);
                            break;
                    case "status":
                    		setStatus((UserAccountStatus)value);break;
                    case "updatedAt":
                    		setUpdatedAt((OffsetDateTime)value);break;
                    case "createdAt":
                    		setCreatedAt((OffsetDateTime)value);break;
                    case "organizationId":
                    		setOrganizationId((UUID)value);break;
                    case "orgAdmin":
                    		if (value == null) throw new IllegalArgumentException("'orgAdmin' cannot be null, if you want to set null, please use any annotation whose simple name is \"Nullable\" to decorate the property");
                            setOrgAdmin((Boolean)value);
                            break;
                    case "profile":
                    		setProfile((UserProfile)value);break;
                    case "roles":
                    		setRoles((List<Role>)value);break;
                    case "sessions":
                    		setSessions((List<Session>)value);break;
                    case "biometrics":
                    		setBiometrics((List<Biometric>)value);break;
                    case "organization":
                    		setOrganization((Organizations)value);break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.User\": \"" + prop + "\"");
                }
            }

            @Override
            public void __show(PropId prop, boolean visible) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Visibility __visibility = (__modified!= null ? __modified : __base).__visibility;
                if (__visibility == null) {
                    if (visible) {
                        return;
                    }
                    __modified().__visibility = __visibility = Visibility.of(18);
                }
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		__show(prop.asName(), visible);
                    return;
                    case SLOT_ID:
                    		__visibility.show(SLOT_ID, visible);break;
                    case SLOT_USERNAME:
                    		__visibility.show(SLOT_USERNAME, visible);break;
                    case SLOT_EMAIL:
                    		__visibility.show(SLOT_EMAIL, visible);break;
                    case SLOT_PHONE:
                    		__visibility.show(SLOT_PHONE, visible);break;
                    case SLOT_PASSWORD:
                    		__visibility.show(SLOT_PASSWORD, visible);break;
                    case SLOT_MULTI_FACTOR_AUTH:
                    		__visibility.show(SLOT_MULTI_FACTOR_AUTH, visible);break;
                    case SLOT_EMAIL_VERIFIED:
                    		__visibility.show(SLOT_EMAIL_VERIFIED, visible);break;
                    case SLOT_PHONE_VERIFIED:
                    		__visibility.show(SLOT_PHONE_VERIFIED, visible);break;
                    case SLOT_STATUS:
                    		__visibility.show(SLOT_STATUS, visible);break;
                    case SLOT_UPDATED_AT:
                    		__visibility.show(SLOT_UPDATED_AT, visible);break;
                    case SLOT_CREATED_AT:
                    		__visibility.show(SLOT_CREATED_AT, visible);break;
                    case SLOT_ORGANIZATION_ID:
                    		__visibility.show(SLOT_ORGANIZATION_ID, visible);break;
                    case SLOT_ORG_ADMIN:
                    		__visibility.show(SLOT_ORG_ADMIN, visible);break;
                    case SLOT_PROFILE:
                    		__visibility.show(SLOT_PROFILE, visible);break;
                    case SLOT_ROLES:
                    		__visibility.show(SLOT_ROLES, visible);break;
                    case SLOT_SESSIONS:
                    		__visibility.show(SLOT_SESSIONS, visible);break;
                    case SLOT_BIOMETRICS:
                    		__visibility.show(SLOT_BIOMETRICS, visible);break;
                    case SLOT_ORGANIZATION:
                    		__visibility.show(SLOT_ORGANIZATION, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property id for \"com.doruk.infrastructure.persistence.entity.User\": \"" + 
                                prop + 
                                "\",it does not exists"
                            );
                }
            }

            @Override
            public void __show(String prop, boolean visible) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Visibility __visibility = (__modified!= null ? __modified : __base).__visibility;
                if (__visibility == null) {
                    if (visible) {
                        return;
                    }
                    __modified().__visibility = __visibility = Visibility.of(18);
                }
                switch (prop) {
                    case "id":
                    		__visibility.show(SLOT_ID, visible);break;
                    case "username":
                    		__visibility.show(SLOT_USERNAME, visible);break;
                    case "email":
                    		__visibility.show(SLOT_EMAIL, visible);break;
                    case "phone":
                    		__visibility.show(SLOT_PHONE, visible);break;
                    case "password":
                    		__visibility.show(SLOT_PASSWORD, visible);break;
                    case "multiFactorAuth":
                    		__visibility.show(SLOT_MULTI_FACTOR_AUTH, visible);break;
                    case "emailVerified":
                    		__visibility.show(SLOT_EMAIL_VERIFIED, visible);break;
                    case "phoneVerified":
                    		__visibility.show(SLOT_PHONE_VERIFIED, visible);break;
                    case "status":
                    		__visibility.show(SLOT_STATUS, visible);break;
                    case "updatedAt":
                    		__visibility.show(SLOT_UPDATED_AT, visible);break;
                    case "createdAt":
                    		__visibility.show(SLOT_CREATED_AT, visible);break;
                    case "organizationId":
                    		__visibility.show(SLOT_ORGANIZATION_ID, visible);break;
                    case "orgAdmin":
                    		__visibility.show(SLOT_ORG_ADMIN, visible);break;
                    case "profile":
                    		__visibility.show(SLOT_PROFILE, visible);break;
                    case "roles":
                    		__visibility.show(SLOT_ROLES, visible);break;
                    case "sessions":
                    		__visibility.show(SLOT_SESSIONS, visible);break;
                    case "biometrics":
                    		__visibility.show(SLOT_BIOMETRICS, visible);break;
                    case "organization":
                    		__visibility.show(SLOT_ORGANIZATION, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property name for \"com.doruk.infrastructure.persistence.entity.User\": \"" + 
                                prop + 
                                "\",it does not exists"
                            );
                }
            }

            @Override
            public void __unload(PropId prop) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		__unload(prop.asName());
                    return;
                    case SLOT_ID:
                    		__modified().__idValue = null;break;
                    case SLOT_USERNAME:
                    		__modified().__usernameValue = null;break;
                    case SLOT_EMAIL:
                    		__modified().__emailValue = null;break;
                    case SLOT_PHONE:
                    		__modified().__phoneValue = null;
                    __modified().__phoneLoaded = false;break;
                    case SLOT_PASSWORD:
                    		__modified().__passwordValue = null;break;
                    case SLOT_MULTI_FACTOR_AUTH:
                    		__modified().__multiFactorAuthValue = null;break;
                    case SLOT_EMAIL_VERIFIED:
                    		__modified().__emailVerifiedValue = false;
                    __modified().__emailVerifiedLoaded = false;break;
                    case SLOT_PHONE_VERIFIED:
                    		__modified().__phoneVerifiedValue = false;
                    __modified().__phoneVerifiedLoaded = false;break;
                    case SLOT_STATUS:
                    		__modified().__statusValue = null;break;
                    case SLOT_UPDATED_AT:
                    		__modified().__updatedAtValue = null;
                    __modified().__updatedAtLoaded = false;break;
                    case SLOT_CREATED_AT:
                    		__modified().__createdAtValue = null;
                    __modified().__createdAtLoaded = false;break;
                    case SLOT_ORGANIZATION_ID:
                    		__unload(PropId.byIndex(SLOT_ORGANIZATION));break;
                    case SLOT_ORG_ADMIN:
                    		__modified().__orgAdminValue = false;
                    __modified().__orgAdminLoaded = false;break;
                    case SLOT_PROFILE:
                    		__modified().__profileValue = null;
                    __modified().__profileLoaded = false;break;
                    case SLOT_ROLES:
                    		__modified().__rolesValue = null;break;
                    case SLOT_SESSIONS:
                    		__modified().__sessionsValue = null;break;
                    case SLOT_BIOMETRICS:
                    		__modified().__biometricsValue = null;break;
                    case SLOT_ORGANIZATION:
                    		__modified().__organizationValue = null;break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.User\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
                }
            }

            @Override
            public void __unload(String prop) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                switch (prop) {
                    case "id":
                    		__modified().__idValue = null;break;
                    case "username":
                    		__modified().__usernameValue = null;break;
                    case "email":
                    		__modified().__emailValue = null;break;
                    case "phone":
                    		__modified().__phoneValue = null;
                    __modified().__phoneLoaded = false;break;
                    case "password":
                    		__modified().__passwordValue = null;break;
                    case "multiFactorAuth":
                    		__modified().__multiFactorAuthValue = null;break;
                    case "emailVerified":
                    		__modified().__emailVerifiedValue = false;
                    __modified().__emailVerifiedLoaded = false;break;
                    case "phoneVerified":
                    		__modified().__phoneVerifiedValue = false;
                    __modified().__phoneVerifiedLoaded = false;break;
                    case "status":
                    		__modified().__statusValue = null;break;
                    case "updatedAt":
                    		__modified().__updatedAtValue = null;
                    __modified().__updatedAtLoaded = false;break;
                    case "createdAt":
                    		__modified().__createdAtValue = null;
                    __modified().__createdAtLoaded = false;break;
                    case "organizationId":
                    		__unload(PropId.byIndex(SLOT_ORGANIZATION));break;
                    case "orgAdmin":
                    		__modified().__orgAdminValue = false;
                    __modified().__orgAdminLoaded = false;break;
                    case "profile":
                    		__modified().__profileValue = null;
                    __modified().__profileLoaded = false;break;
                    case "roles":
                    		__modified().__rolesValue = null;break;
                    case "sessions":
                    		__modified().__sessionsValue = null;break;
                    case "biometrics":
                    		__modified().__biometricsValue = null;break;
                    case "organization":
                    		__modified().__organizationValue = null;break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.User\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
                }
            }

            @Override
            public DraftContext __draftContext() {
                return __ctx;
            }

            @Override
            public Object __resolve() {
                if (__resolved != null) {
                    return __resolved;
                }
                if (__resolving) {
                    throw new CircularReferenceException();
                }
                __resolving = true;
                try {
                    Implementor base = __base;
                    Impl __tmpModified = __modified;
                    if (__tmpModified == null) {
                        if (base.__isLoaded(PropId.byIndex(SLOT_PROFILE))) {
                            UserProfile oldValue = base.profile();
                            UserProfile newValue = __ctx.resolveObject(oldValue);
                            if (oldValue != newValue) {
                                setProfile(newValue);
                            }
                        }
                        if (base.__isLoaded(PropId.byIndex(SLOT_ROLES))) {
                            List<Role> oldValue = base.roles();
                            List<Role> newValue = __ctx.resolveList(oldValue);
                            if (oldValue != newValue) {
                                setRoles(newValue);
                            }
                        }
                        if (base.__isLoaded(PropId.byIndex(SLOT_SESSIONS))) {
                            List<Session> oldValue = base.sessions();
                            List<Session> newValue = __ctx.resolveList(oldValue);
                            if (oldValue != newValue) {
                                setSessions(newValue);
                            }
                        }
                        if (base.__isLoaded(PropId.byIndex(SLOT_BIOMETRICS))) {
                            List<Biometric> oldValue = base.biometrics();
                            List<Biometric> newValue = __ctx.resolveList(oldValue);
                            if (oldValue != newValue) {
                                setBiometrics(newValue);
                            }
                        }
                        if (base.__isLoaded(PropId.byIndex(SLOT_ORGANIZATION))) {
                            Organizations oldValue = base.organization();
                            Organizations newValue = __ctx.resolveObject(oldValue);
                            if (oldValue != newValue) {
                                setOrganization(newValue);
                            }
                        }
                        __tmpModified = __modified;
                    }
                    else {
                        __tmpModified.__profileValue = __ctx.resolveObject(__tmpModified.__profileValue);
                        __tmpModified.__rolesValue = NonSharedList.of(__tmpModified.__rolesValue, __ctx.resolveList(__tmpModified.__rolesValue));
                        __tmpModified.__sessionsValue = NonSharedList.of(__tmpModified.__sessionsValue, __ctx.resolveList(__tmpModified.__sessionsValue));
                        __tmpModified.__biometricsValue = NonSharedList.of(__tmpModified.__biometricsValue, __ctx.resolveList(__tmpModified.__biometricsValue));
                        __tmpModified.__organizationValue = __ctx.resolveObject(__tmpModified.__organizationValue);
                    }
                    if (__base != null && __tmpModified == null) {
                        this.__resolved = base;
                        return base;
                    }
                    this.__resolved = __tmpModified;
                    return __tmpModified;
                }
                finally {
                    __resolving = false;
                }
            }

            @Override
            public boolean __isResolved() {
                return __resolved != null;
            }

            Impl __modified() {
                Impl __tmpModified = __modified;
                if (__tmpModified == null) {
                    __tmpModified = __base.clone();
                    __modified = __tmpModified;
                }
                return __tmpModified;
            }
        }
    }

    @GeneratedBy(
            type = User.class
    )
    class Builder {
        private final Producer.DraftImpl __draft;

        public Builder() {
            this(null);
        }

        public Builder(@Nullable User base) {
            __draft = new Producer.DraftImpl(null, base);
            __draft.__show(PropId.byIndex(Producer.SLOT_ORGANIZATION_ID), false);
            __draft.__show(PropId.byIndex(Producer.SLOT_ORGANIZATION), false);
        }

        public Builder id(@NonNull UUID id) {
            if (id != null) {
                __draft.setId(id);
            }
            return this;
        }

        public Builder username(@NonNull String username) {
            if (username != null) {
                __draft.setUsername(username);
            }
            return this;
        }

        public Builder email(@NonNull String email) {
            if (email != null) {
                __draft.setEmail(email);
            }
            return this;
        }

        public Builder phone(@Nullable String phone) {
            __draft.setPhone(phone);
            return this;
        }

        public Builder password(@NonNull String password) {
            if (password != null) {
                __draft.setPassword(password);
            }
            return this;
        }

        public Builder multiFactorAuth(@NonNull MultiAuthType multiFactorAuth) {
            if (multiFactorAuth != null) {
                __draft.setMultiFactorAuth(multiFactorAuth);
            }
            return this;
        }

        public Builder emailVerified(@NonNull Boolean emailVerified) {
            if (emailVerified != null) {
                __draft.setEmailVerified(emailVerified);
            }
            return this;
        }

        public Builder phoneVerified(@NonNull Boolean phoneVerified) {
            if (phoneVerified != null) {
                __draft.setPhoneVerified(phoneVerified);
            }
            return this;
        }

        public Builder status(@NonNull UserAccountStatus status) {
            if (status != null) {
                __draft.setStatus(status);
            }
            return this;
        }

        public Builder updatedAt(@Nullable OffsetDateTime updatedAt) {
            __draft.setUpdatedAt(updatedAt);
            return this;
        }

        public Builder createdAt(@Nullable OffsetDateTime createdAt) {
            __draft.setCreatedAt(createdAt);
            return this;
        }

        public Builder organizationId(@NonNull UUID organizationId) {
            if (organizationId != null) {
                __draft.setOrganizationId(organizationId);
                __draft.__show(PropId.byIndex(Producer.SLOT_ORGANIZATION_ID), true);
            }
            return this;
        }

        public Builder orgAdmin(@NonNull Boolean orgAdmin) {
            if (orgAdmin != null) {
                __draft.setOrgAdmin(orgAdmin);
            }
            return this;
        }

        public Builder profile(@Nullable UserProfile profile) {
            __draft.setProfile(profile);
            return this;
        }

        public Builder roles(@NonNull List<Role> roles) {
            if (roles != null) {
                __draft.setRoles(roles);
            }
            return this;
        }

        public Builder sessions(@NonNull List<Session> sessions) {
            if (sessions != null) {
                __draft.setSessions(sessions);
            }
            return this;
        }

        public Builder biometrics(@NonNull List<Biometric> biometrics) {
            if (biometrics != null) {
                __draft.setBiometrics(biometrics);
            }
            return this;
        }

        public Builder organization(@NonNull Organizations organization) {
            if (organization != null) {
                __draft.setOrganization(organization);
                __draft.__show(PropId.byIndex(Producer.SLOT_ORGANIZATION), true);
            }
            return this;
        }

        public User build() {
            return (User)__draft.__modified();
        }
    }
}
