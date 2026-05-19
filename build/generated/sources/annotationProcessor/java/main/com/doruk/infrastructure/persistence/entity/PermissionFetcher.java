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
        type = Permission.class
)
public class PermissionFetcher extends AbstractTypedFetcher<Permission, PermissionFetcher> {
    public static final PermissionFetcher $ = new PermissionFetcher(null);

    private PermissionFetcher(FetcherImpl<Permission> base) {
        super(Permission.class, base);
    }

    private PermissionFetcher(PermissionFetcher prev, ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        super(prev, prop, negative, idOnlyFetchType);
    }

    private PermissionFetcher(PermissionFetcher prev, ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        super(prev, prop, fieldConfig);
    }

    public static PermissionFetcher $from(Fetcher<Permission> base) {
        return base instanceof PermissionFetcher ? 
        	(PermissionFetcher)base : 
        	new PermissionFetcher((FetcherImpl<Permission>)base);
    }

    @NewChain
    public PermissionFetcher roles() {
        return add("roles");
    }

    @NewChain
    public PermissionFetcher roles(boolean enabled) {
        return enabled ? add("roles") : remove("roles");
    }

    @NewChain
    public PermissionFetcher roles(Fetcher<Role> childFetcher) {
        return add("roles", childFetcher);
    }

    @NewChain
    public PermissionFetcher roles(Fetcher<Role> childFetcher,
            Consumer<ListFieldConfig<Role, RoleTable>> fieldConfig) {
        return add("roles", childFetcher, fieldConfig);
    }

    @NewChain
    public PermissionFetcher deletedAt() {
        return add("deletedAt");
    }

    @NewChain
    public PermissionFetcher deletedAt(boolean enabled) {
        return enabled ? add("deletedAt") : remove("deletedAt");
    }

    @Override
    protected PermissionFetcher createFetcher(ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        return new PermissionFetcher(this, prop, negative, idOnlyFetchType);
    }

    @Override
    protected PermissionFetcher createFetcher(ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        return new PermissionFetcher(this, prop, fieldConfig);
    }
}
