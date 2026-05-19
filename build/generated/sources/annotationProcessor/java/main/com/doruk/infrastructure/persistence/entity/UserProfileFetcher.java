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
import org.babyfish.jimmer.sql.fetcher.ReferenceFetchType;
import org.babyfish.jimmer.sql.fetcher.ReferenceFieldConfig;
import org.babyfish.jimmer.sql.fetcher.impl.FetcherImpl;
import org.babyfish.jimmer.sql.fetcher.spi.AbstractTypedFetcher;

@GeneratedBy(
        type = UserProfile.class
)
public class UserProfileFetcher extends AbstractTypedFetcher<UserProfile, UserProfileFetcher> {
    public static final UserProfileFetcher $ = new UserProfileFetcher(null);

    private UserProfileFetcher(FetcherImpl<UserProfile> base) {
        super(UserProfile.class, base);
    }

    private UserProfileFetcher(UserProfileFetcher prev, ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        super(prev, prop, negative, idOnlyFetchType);
    }

    private UserProfileFetcher(UserProfileFetcher prev, ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        super(prev, prop, fieldConfig);
    }

    public static UserProfileFetcher $from(Fetcher<UserProfile> base) {
        return base instanceof UserProfileFetcher ? 
        	(UserProfileFetcher)base : 
        	new UserProfileFetcher((FetcherImpl<UserProfile>)base);
    }

    @NewChain
    public UserProfileFetcher user() {
        return add("user");
    }

    @NewChain
    public UserProfileFetcher user(boolean enabled) {
        return enabled ? add("user") : remove("user");
    }

    @NewChain
    public UserProfileFetcher user(Fetcher<User> childFetcher) {
        return add("user", childFetcher);
    }

    @NewChain
    public UserProfileFetcher user(IdOnlyFetchType idOnlyFetchType) {
        return add("user", idOnlyFetchType);
    }

    @NewChain
    public UserProfileFetcher user(Fetcher<User> childFetcher,
            Consumer<ReferenceFieldConfig<User, UserTable>> fieldConfig) {
        return add("user", childFetcher, fieldConfig);
    }

    @NewChain
    public UserProfileFetcher user(ReferenceFetchType fetchType, Fetcher<User> childFetcher) {
        return user(childFetcher, cfg -> cfg.fetchType(fetchType));
    }

    @NewChain
    public UserProfileFetcher fullName() {
        return add("fullName");
    }

    @NewChain
    public UserProfileFetcher fullName(boolean enabled) {
        return enabled ? add("fullName") : remove("fullName");
    }

    @NewChain
    public UserProfileFetcher profileIcon() {
        return add("profileIcon");
    }

    @NewChain
    public UserProfileFetcher profileIcon(boolean enabled) {
        return enabled ? add("profileIcon") : remove("profileIcon");
    }

    @NewChain
    public UserProfileFetcher profileIcon(Fetcher<MediaStore> childFetcher) {
        return add("profileIcon", childFetcher);
    }

    @NewChain
    public UserProfileFetcher profileIcon(IdOnlyFetchType idOnlyFetchType) {
        return add("profileIcon", idOnlyFetchType);
    }

    @NewChain
    public UserProfileFetcher profileIcon(Fetcher<MediaStore> childFetcher,
            Consumer<ReferenceFieldConfig<MediaStore, MediaStoreTable>> fieldConfig) {
        return add("profileIcon", childFetcher, fieldConfig);
    }

    @NewChain
    public UserProfileFetcher profileIcon(ReferenceFetchType fetchType,
            Fetcher<MediaStore> childFetcher) {
        return profileIcon(childFetcher, cfg -> cfg.fetchType(fetchType));
    }

    @NewChain
    public UserProfileFetcher address() {
        return add("address");
    }

    @NewChain
    public UserProfileFetcher address(boolean enabled) {
        return enabled ? add("address") : remove("address");
    }

    @NewChain
    public UserProfileFetcher city() {
        return add("city");
    }

    @NewChain
    public UserProfileFetcher city(boolean enabled) {
        return enabled ? add("city") : remove("city");
    }

    @NewChain
    public UserProfileFetcher state() {
        return add("state");
    }

    @NewChain
    public UserProfileFetcher state(boolean enabled) {
        return enabled ? add("state") : remove("state");
    }

    @NewChain
    public UserProfileFetcher country() {
        return add("country");
    }

    @NewChain
    public UserProfileFetcher country(boolean enabled) {
        return enabled ? add("country") : remove("country");
    }

    @NewChain
    public UserProfileFetcher postalCode() {
        return add("postalCode");
    }

    @NewChain
    public UserProfileFetcher postalCode(boolean enabled) {
        return enabled ? add("postalCode") : remove("postalCode");
    }

    @NewChain
    public UserProfileFetcher createdAt() {
        return add("createdAt");
    }

    @NewChain
    public UserProfileFetcher createdAt(boolean enabled) {
        return enabled ? add("createdAt") : remove("createdAt");
    }

    @NewChain
    public UserProfileFetcher updatedAt() {
        return add("updatedAt");
    }

    @NewChain
    public UserProfileFetcher updatedAt(boolean enabled) {
        return enabled ? add("updatedAt") : remove("updatedAt");
    }

    @Override
    protected UserProfileFetcher createFetcher(ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        return new UserProfileFetcher(this, prop, negative, idOnlyFetchType);
    }

    @Override
    protected UserProfileFetcher createFetcher(ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        return new UserProfileFetcher(this, prop, fieldConfig);
    }
}
