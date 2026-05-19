package com.doruk.infrastructure.persistence.entity;

import java.lang.Override;
import java.util.function.Consumer;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.lang.NewChain;
import org.babyfish.jimmer.meta.ImmutableProp;
import org.babyfish.jimmer.sql.ast.table.Table;
import org.babyfish.jimmer.sql.fetcher.Fetcher;
import org.babyfish.jimmer.sql.fetcher.FieldConfig;
import org.babyfish.jimmer.sql.fetcher.IdOnlyFetchType;
import org.babyfish.jimmer.sql.fetcher.ListFieldConfig;
import org.babyfish.jimmer.sql.fetcher.ReferenceFetchType;
import org.babyfish.jimmer.sql.fetcher.ReferenceFieldConfig;
import org.babyfish.jimmer.sql.fetcher.impl.FetcherImpl;
import org.babyfish.jimmer.sql.fetcher.spi.AbstractTypedFetcher;

@GeneratedBy(
        type = User.class
)
public class UserFetcher extends AbstractTypedFetcher<User, UserFetcher> {
    public static final UserFetcher $ = new UserFetcher(null);

    private UserFetcher(FetcherImpl<User> base) {
        super(User.class, base);
    }

    private UserFetcher(UserFetcher prev, ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        super(prev, prop, negative, idOnlyFetchType);
    }

    private UserFetcher(UserFetcher prev, ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        super(prev, prop, fieldConfig);
    }

    public static UserFetcher $from(Fetcher<User> base) {
        return base instanceof UserFetcher ? 
        	(UserFetcher)base : 
        	new UserFetcher((FetcherImpl<User>)base);
    }

    @NewChain
    public UserFetcher username() {
        return add("username");
    }

    @NewChain
    public UserFetcher username(boolean enabled) {
        return enabled ? add("username") : remove("username");
    }

    @NewChain
    public UserFetcher email() {
        return add("email");
    }

    @NewChain
    public UserFetcher email(boolean enabled) {
        return enabled ? add("email") : remove("email");
    }

    @NewChain
    public UserFetcher phone() {
        return add("phone");
    }

    @NewChain
    public UserFetcher phone(boolean enabled) {
        return enabled ? add("phone") : remove("phone");
    }

    @NewChain
    public UserFetcher password() {
        return add("password");
    }

    @NewChain
    public UserFetcher password(boolean enabled) {
        return enabled ? add("password") : remove("password");
    }

    @NewChain
    public UserFetcher multiFactorAuth() {
        return add("multiFactorAuth");
    }

    @NewChain
    public UserFetcher multiFactorAuth(boolean enabled) {
        return enabled ? add("multiFactorAuth") : remove("multiFactorAuth");
    }

    @NewChain
    public UserFetcher emailVerified() {
        return add("emailVerified");
    }

    @NewChain
    public UserFetcher emailVerified(boolean enabled) {
        return enabled ? add("emailVerified") : remove("emailVerified");
    }

    @NewChain
    public UserFetcher phoneVerified() {
        return add("phoneVerified");
    }

    @NewChain
    public UserFetcher phoneVerified(boolean enabled) {
        return enabled ? add("phoneVerified") : remove("phoneVerified");
    }

    @NewChain
    public UserFetcher status() {
        return add("status");
    }

    @NewChain
    public UserFetcher status(boolean enabled) {
        return enabled ? add("status") : remove("status");
    }

    @NewChain
    public UserFetcher updatedAt() {
        return add("updatedAt");
    }

    @NewChain
    public UserFetcher updatedAt(boolean enabled) {
        return enabled ? add("updatedAt") : remove("updatedAt");
    }

    @NewChain
    public UserFetcher createdAt() {
        return add("createdAt");
    }

    @NewChain
    public UserFetcher createdAt(boolean enabled) {
        return enabled ? add("createdAt") : remove("createdAt");
    }

    @NewChain
    public UserFetcher organizationId() {
        return add("organizationId");
    }

    @NewChain
    public UserFetcher organizationId(boolean enabled) {
        return enabled ? add("organizationId") : remove("organizationId");
    }

    @NewChain
    public UserFetcher orgAdmin() {
        return add("orgAdmin");
    }

    @NewChain
    public UserFetcher orgAdmin(boolean enabled) {
        return enabled ? add("orgAdmin") : remove("orgAdmin");
    }

    @NewChain
    public UserFetcher profile() {
        return add("profile");
    }

    @NewChain
    public UserFetcher profile(boolean enabled) {
        return enabled ? add("profile") : remove("profile");
    }

    @NewChain
    public UserFetcher profile(Fetcher<UserProfile> childFetcher) {
        return add("profile", childFetcher);
    }

    @NewChain
    public UserFetcher profile(Fetcher<UserProfile> childFetcher,
            Consumer<ReferenceFieldConfig<UserProfile, UserProfileTable>> fieldConfig) {
        return add("profile", childFetcher, fieldConfig);
    }

    @NewChain
    public UserFetcher profile(ReferenceFetchType fetchType, Fetcher<UserProfile> childFetcher) {
        return profile(childFetcher, cfg -> cfg.fetchType(fetchType));
    }

    @NewChain
    public UserFetcher roles() {
        return add("roles");
    }

    @NewChain
    public UserFetcher roles(boolean enabled) {
        return enabled ? add("roles") : remove("roles");
    }

    @NewChain
    public UserFetcher roles(Fetcher<Role> childFetcher) {
        return add("roles", childFetcher);
    }

    @NewChain
    public UserFetcher roles(Fetcher<Role> childFetcher,
            Consumer<ListFieldConfig<Role, RoleTable>> fieldConfig) {
        return add("roles", childFetcher, fieldConfig);
    }

    @NewChain
    public UserFetcher sessions() {
        return add("sessions");
    }

    @NewChain
    public UserFetcher sessions(boolean enabled) {
        return enabled ? add("sessions") : remove("sessions");
    }

    @NewChain
    public UserFetcher sessions(Fetcher<Session> childFetcher) {
        return add("sessions", childFetcher);
    }

    @NewChain
    public UserFetcher sessions(Fetcher<Session> childFetcher,
            Consumer<ListFieldConfig<Session, SessionTable>> fieldConfig) {
        return add("sessions", childFetcher, fieldConfig);
    }

    @NewChain
    public UserFetcher biometrics() {
        return add("biometrics");
    }

    @NewChain
    public UserFetcher biometrics(boolean enabled) {
        return enabled ? add("biometrics") : remove("biometrics");
    }

    @NewChain
    public UserFetcher biometrics(Fetcher<Biometric> childFetcher) {
        return add("biometrics", childFetcher);
    }

    @NewChain
    public UserFetcher biometrics(Fetcher<Biometric> childFetcher,
            Consumer<ListFieldConfig<Biometric, BiometricTable>> fieldConfig) {
        return add("biometrics", childFetcher, fieldConfig);
    }

    @NewChain
    public UserFetcher organization() {
        return add("organization");
    }

    @NewChain
    public UserFetcher organization(boolean enabled) {
        return enabled ? add("organization") : remove("organization");
    }

    @NewChain
    public UserFetcher organization(Fetcher<Organizations> childFetcher) {
        return add("organization", childFetcher);
    }

    @NewChain
    public UserFetcher organization(IdOnlyFetchType idOnlyFetchType) {
        return add("organization", idOnlyFetchType);
    }

    @NewChain
    public UserFetcher organization(Fetcher<Organizations> childFetcher,
            Consumer<ReferenceFieldConfig<Organizations, OrganizationsTable>> fieldConfig) {
        return add("organization", childFetcher, fieldConfig);
    }

    @NewChain
    public UserFetcher organization(ReferenceFetchType fetchType,
            Fetcher<Organizations> childFetcher) {
        return organization(childFetcher, cfg -> cfg.fetchType(fetchType));
    }

    @Override
    protected UserFetcher createFetcher(ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        return new UserFetcher(this, prop, negative, idOnlyFetchType);
    }

    @Override
    protected UserFetcher createFetcher(ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        return new UserFetcher(this, prop, fieldConfig);
    }
}
