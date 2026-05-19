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
        type = LicenseSeats.class
)
public class LicenseSeatsFetcher extends AbstractTypedFetcher<LicenseSeats, LicenseSeatsFetcher> {
    public static final LicenseSeatsFetcher $ = new LicenseSeatsFetcher(null);

    private LicenseSeatsFetcher(FetcherImpl<LicenseSeats> base) {
        super(LicenseSeats.class, base);
    }

    private LicenseSeatsFetcher(LicenseSeatsFetcher prev, ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        super(prev, prop, negative, idOnlyFetchType);
    }

    private LicenseSeatsFetcher(LicenseSeatsFetcher prev, ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        super(prev, prop, fieldConfig);
    }

    public static LicenseSeatsFetcher $from(Fetcher<LicenseSeats> base) {
        return base instanceof LicenseSeatsFetcher ? 
        	(LicenseSeatsFetcher)base : 
        	new LicenseSeatsFetcher((FetcherImpl<LicenseSeats>)base);
    }

    @NewChain
    public LicenseSeatsFetcher licenses() {
        return add("licenses");
    }

    @NewChain
    public LicenseSeatsFetcher licenses(boolean enabled) {
        return enabled ? add("licenses") : remove("licenses");
    }

    @NewChain
    public LicenseSeatsFetcher licenses(Fetcher<Licenses> childFetcher) {
        return add("licenses", childFetcher);
    }

    @NewChain
    public LicenseSeatsFetcher licenses(IdOnlyFetchType idOnlyFetchType) {
        return add("licenses", idOnlyFetchType);
    }

    @NewChain
    public LicenseSeatsFetcher licenses(Fetcher<Licenses> childFetcher,
            Consumer<ReferenceFieldConfig<Licenses, LicensesTable>> fieldConfig) {
        return add("licenses", childFetcher, fieldConfig);
    }

    @NewChain
    public LicenseSeatsFetcher licenses(ReferenceFetchType fetchType,
            Fetcher<Licenses> childFetcher) {
        return licenses(childFetcher, cfg -> cfg.fetchType(fetchType));
    }

    @NewChain
    public LicenseSeatsFetcher licenseId() {
        return add("licenseId");
    }

    @NewChain
    public LicenseSeatsFetcher licenseId(boolean enabled) {
        return enabled ? add("licenseId") : remove("licenseId");
    }

    @NewChain
    public LicenseSeatsFetcher user() {
        return add("user");
    }

    @NewChain
    public LicenseSeatsFetcher user(boolean enabled) {
        return enabled ? add("user") : remove("user");
    }

    @NewChain
    public LicenseSeatsFetcher user(Fetcher<User> childFetcher) {
        return add("user", childFetcher);
    }

    @NewChain
    public LicenseSeatsFetcher user(IdOnlyFetchType idOnlyFetchType) {
        return add("user", idOnlyFetchType);
    }

    @NewChain
    public LicenseSeatsFetcher user(Fetcher<User> childFetcher,
            Consumer<ReferenceFieldConfig<User, UserTable>> fieldConfig) {
        return add("user", childFetcher, fieldConfig);
    }

    @NewChain
    public LicenseSeatsFetcher user(ReferenceFetchType fetchType, Fetcher<User> childFetcher) {
        return user(childFetcher, cfg -> cfg.fetchType(fetchType));
    }

    @NewChain
    public LicenseSeatsFetcher userId() {
        return add("userId");
    }

    @NewChain
    public LicenseSeatsFetcher userId(boolean enabled) {
        return enabled ? add("userId") : remove("userId");
    }

    @NewChain
    public LicenseSeatsFetcher assignedAt() {
        return add("assignedAt");
    }

    @NewChain
    public LicenseSeatsFetcher assignedAt(boolean enabled) {
        return enabled ? add("assignedAt") : remove("assignedAt");
    }

    @NewChain
    public LicenseSeatsFetcher assignedBy() {
        return add("assignedBy");
    }

    @NewChain
    public LicenseSeatsFetcher assignedBy(boolean enabled) {
        return enabled ? add("assignedBy") : remove("assignedBy");
    }

    @NewChain
    public LicenseSeatsFetcher assignedBy(Fetcher<User> childFetcher) {
        return add("assignedBy", childFetcher);
    }

    @NewChain
    public LicenseSeatsFetcher assignedBy(IdOnlyFetchType idOnlyFetchType) {
        return add("assignedBy", idOnlyFetchType);
    }

    @NewChain
    public LicenseSeatsFetcher assignedBy(Fetcher<User> childFetcher,
            Consumer<ReferenceFieldConfig<User, UserTable>> fieldConfig) {
        return add("assignedBy", childFetcher, fieldConfig);
    }

    @NewChain
    public LicenseSeatsFetcher assignedBy(ReferenceFetchType fetchType,
            Fetcher<User> childFetcher) {
        return assignedBy(childFetcher, cfg -> cfg.fetchType(fetchType));
    }

    @NewChain
    public LicenseSeatsFetcher assignedById() {
        return add("assignedById");
    }

    @NewChain
    public LicenseSeatsFetcher assignedById(boolean enabled) {
        return enabled ? add("assignedById") : remove("assignedById");
    }

    @Override
    protected LicenseSeatsFetcher createFetcher(ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        return new LicenseSeatsFetcher(this, prop, negative, idOnlyFetchType);
    }

    @Override
    protected LicenseSeatsFetcher createFetcher(ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        return new LicenseSeatsFetcher(this, prop, fieldConfig);
    }
}
