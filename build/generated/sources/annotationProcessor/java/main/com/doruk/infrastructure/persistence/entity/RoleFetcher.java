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
        type = Role.class
)
public class RoleFetcher extends AbstractTypedFetcher<Role, RoleFetcher> {
    public static final RoleFetcher $ = new RoleFetcher(null);

    private RoleFetcher(FetcherImpl<Role> base) {
        super(Role.class, base);
    }

    private RoleFetcher(RoleFetcher prev, ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        super(prev, prop, negative, idOnlyFetchType);
    }

    private RoleFetcher(RoleFetcher prev, ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        super(prev, prop, fieldConfig);
    }

    public static RoleFetcher $from(Fetcher<Role> base) {
        return base instanceof RoleFetcher ? 
        	(RoleFetcher)base : 
        	new RoleFetcher((FetcherImpl<Role>)base);
    }

    @NewChain
    public RoleFetcher users() {
        return add("users");
    }

    @NewChain
    public RoleFetcher users(boolean enabled) {
        return enabled ? add("users") : remove("users");
    }

    @NewChain
    public RoleFetcher users(Fetcher<User> childFetcher) {
        return add("users", childFetcher);
    }

    @NewChain
    public RoleFetcher users(Fetcher<User> childFetcher,
            Consumer<ListFieldConfig<User, UserTable>> fieldConfig) {
        return add("users", childFetcher, fieldConfig);
    }

    @NewChain
    public RoleFetcher permissions() {
        return add("permissions");
    }

    @NewChain
    public RoleFetcher permissions(boolean enabled) {
        return enabled ? add("permissions") : remove("permissions");
    }

    @NewChain
    public RoleFetcher permissions(Fetcher<Permission> childFetcher) {
        return add("permissions", childFetcher);
    }

    @NewChain
    public RoleFetcher permissions(Fetcher<Permission> childFetcher,
            Consumer<ListFieldConfig<Permission, PermissionTable>> fieldConfig) {
        return add("permissions", childFetcher, fieldConfig);
    }

    @NewChain
    public RoleFetcher deletedAt() {
        return add("deletedAt");
    }

    @NewChain
    public RoleFetcher deletedAt(boolean enabled) {
        return enabled ? add("deletedAt") : remove("deletedAt");
    }

    @Override
    protected RoleFetcher createFetcher(ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        return new RoleFetcher(this, prop, negative, idOnlyFetchType);
    }

    @Override
    protected RoleFetcher createFetcher(ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        return new RoleFetcher(this, prop, fieldConfig);
    }
}
