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
import org.babyfish.jimmer.sql.fetcher.impl.FetcherImpl;
import org.babyfish.jimmer.sql.fetcher.spi.AbstractTypedFetcher;

@GeneratedBy(
        type = Organizations.class
)
public class OrganizationsFetcher extends AbstractTypedFetcher<Organizations, OrganizationsFetcher> {
    public static final OrganizationsFetcher $ = new OrganizationsFetcher(null);

    private OrganizationsFetcher(FetcherImpl<Organizations> base) {
        super(Organizations.class, base);
    }

    private OrganizationsFetcher(OrganizationsFetcher prev, ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        super(prev, prop, negative, idOnlyFetchType);
    }

    private OrganizationsFetcher(OrganizationsFetcher prev, ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        super(prev, prop, fieldConfig);
    }

    public static OrganizationsFetcher $from(Fetcher<Organizations> base) {
        return base instanceof OrganizationsFetcher ? 
        	(OrganizationsFetcher)base : 
        	new OrganizationsFetcher((FetcherImpl<Organizations>)base);
    }

    @NewChain
    public OrganizationsFetcher name() {
        return add("name");
    }

    @NewChain
    public OrganizationsFetcher name(boolean enabled) {
        return enabled ? add("name") : remove("name");
    }

    @NewChain
    public OrganizationsFetcher type() {
        return add("type");
    }

    @NewChain
    public OrganizationsFetcher type(boolean enabled) {
        return enabled ? add("type") : remove("type");
    }

    @NewChain
    public OrganizationsFetcher orgCode() {
        return add("orgCode");
    }

    @NewChain
    public OrganizationsFetcher orgCode(boolean enabled) {
        return enabled ? add("orgCode") : remove("orgCode");
    }

    @NewChain
    public OrganizationsFetcher createdAt() {
        return add("createdAt");
    }

    @NewChain
    public OrganizationsFetcher createdAt(boolean enabled) {
        return enabled ? add("createdAt") : remove("createdAt");
    }

    @NewChain
    public OrganizationsFetcher licenses() {
        return add("licenses");
    }

    @NewChain
    public OrganizationsFetcher licenses(boolean enabled) {
        return enabled ? add("licenses") : remove("licenses");
    }

    @NewChain
    public OrganizationsFetcher licenses(Fetcher<Licenses> childFetcher) {
        return add("licenses", childFetcher);
    }

    @NewChain
    public OrganizationsFetcher licenses(Fetcher<Licenses> childFetcher,
            Consumer<ListFieldConfig<Licenses, LicensesTable>> fieldConfig) {
        return add("licenses", childFetcher, fieldConfig);
    }

    @NewChain
    public OrganizationsFetcher users() {
        return add("users");
    }

    @NewChain
    public OrganizationsFetcher users(boolean enabled) {
        return enabled ? add("users") : remove("users");
    }

    @NewChain
    public OrganizationsFetcher users(Fetcher<User> childFetcher) {
        return add("users", childFetcher);
    }

    @NewChain
    public OrganizationsFetcher users(Fetcher<User> childFetcher,
            Consumer<ListFieldConfig<User, UserTable>> fieldConfig) {
        return add("users", childFetcher, fieldConfig);
    }

    @Override
    protected OrganizationsFetcher createFetcher(ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        return new OrganizationsFetcher(this, prop, negative, idOnlyFetchType);
    }

    @Override
    protected OrganizationsFetcher createFetcher(ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        return new OrganizationsFetcher(this, prop, fieldConfig);
    }
}
