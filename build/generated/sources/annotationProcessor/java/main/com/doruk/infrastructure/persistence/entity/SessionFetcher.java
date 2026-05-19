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
        type = Session.class
)
public class SessionFetcher extends AbstractTypedFetcher<Session, SessionFetcher> {
    public static final SessionFetcher $ = new SessionFetcher(null);

    private SessionFetcher(FetcherImpl<Session> base) {
        super(Session.class, base);
    }

    private SessionFetcher(SessionFetcher prev, ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        super(prev, prop, negative, idOnlyFetchType);
    }

    private SessionFetcher(SessionFetcher prev, ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        super(prev, prop, fieldConfig);
    }

    public static SessionFetcher $from(Fetcher<Session> base) {
        return base instanceof SessionFetcher ? 
        	(SessionFetcher)base : 
        	new SessionFetcher((FetcherImpl<Session>)base);
    }

    @NewChain
    public SessionFetcher user() {
        return add("user");
    }

    @NewChain
    public SessionFetcher user(boolean enabled) {
        return enabled ? add("user") : remove("user");
    }

    @NewChain
    public SessionFetcher user(Fetcher<User> childFetcher) {
        return add("user", childFetcher);
    }

    @NewChain
    public SessionFetcher user(IdOnlyFetchType idOnlyFetchType) {
        return add("user", idOnlyFetchType);
    }

    @NewChain
    public SessionFetcher user(Fetcher<User> childFetcher,
            Consumer<ReferenceFieldConfig<User, UserTable>> fieldConfig) {
        return add("user", childFetcher, fieldConfig);
    }

    @NewChain
    public SessionFetcher user(ReferenceFetchType fetchType, Fetcher<User> childFetcher) {
        return user(childFetcher, cfg -> cfg.fetchType(fetchType));
    }

    @NewChain
    public SessionFetcher sessionId() {
        return add("sessionId");
    }

    @NewChain
    public SessionFetcher sessionId(boolean enabled) {
        return enabled ? add("sessionId") : remove("sessionId");
    }

    @NewChain
    public SessionFetcher deviceInfo() {
        return add("deviceInfo");
    }

    @NewChain
    public SessionFetcher deviceInfo(boolean enabled) {
        return enabled ? add("deviceInfo") : remove("deviceInfo");
    }

    @NewChain
    public SessionFetcher deviceId() {
        return add("deviceId");
    }

    @NewChain
    public SessionFetcher deviceId(boolean enabled) {
        return enabled ? add("deviceId") : remove("deviceId");
    }

    @NewChain
    public SessionFetcher expiresAt() {
        return add("expiresAt");
    }

    @NewChain
    public SessionFetcher expiresAt(boolean enabled) {
        return enabled ? add("expiresAt") : remove("expiresAt");
    }

    @NewChain
    public SessionFetcher createdAt() {
        return add("createdAt");
    }

    @NewChain
    public SessionFetcher createdAt(boolean enabled) {
        return enabled ? add("createdAt") : remove("createdAt");
    }

    @NewChain
    public SessionFetcher cachedPermissions() {
        return add("cachedPermissions");
    }

    @NewChain
    public SessionFetcher cachedPermissions(boolean enabled) {
        return enabled ? add("cachedPermissions") : remove("cachedPermissions");
    }

    @Override
    protected SessionFetcher createFetcher(ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        return new SessionFetcher(this, prop, negative, idOnlyFetchType);
    }

    @Override
    protected SessionFetcher createFetcher(ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        return new SessionFetcher(this, prop, fieldConfig);
    }
}
