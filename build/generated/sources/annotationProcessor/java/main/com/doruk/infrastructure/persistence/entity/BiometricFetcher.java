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
        type = Biometric.class
)
public class BiometricFetcher extends AbstractTypedFetcher<Biometric, BiometricFetcher> {
    public static final BiometricFetcher $ = new BiometricFetcher(null);

    private BiometricFetcher(FetcherImpl<Biometric> base) {
        super(Biometric.class, base);
    }

    private BiometricFetcher(BiometricFetcher prev, ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        super(prev, prop, negative, idOnlyFetchType);
    }

    private BiometricFetcher(BiometricFetcher prev, ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        super(prev, prop, fieldConfig);
    }

    public static BiometricFetcher $from(Fetcher<Biometric> base) {
        return base instanceof BiometricFetcher ? 
        	(BiometricFetcher)base : 
        	new BiometricFetcher((FetcherImpl<Biometric>)base);
    }

    @NewChain
    public BiometricFetcher user() {
        return add("user");
    }

    @NewChain
    public BiometricFetcher user(boolean enabled) {
        return enabled ? add("user") : remove("user");
    }

    @NewChain
    public BiometricFetcher user(Fetcher<User> childFetcher) {
        return add("user", childFetcher);
    }

    @NewChain
    public BiometricFetcher user(IdOnlyFetchType idOnlyFetchType) {
        return add("user", idOnlyFetchType);
    }

    @NewChain
    public BiometricFetcher user(Fetcher<User> childFetcher,
            Consumer<ReferenceFieldConfig<User, UserTable>> fieldConfig) {
        return add("user", childFetcher, fieldConfig);
    }

    @NewChain
    public BiometricFetcher user(ReferenceFetchType fetchType, Fetcher<User> childFetcher) {
        return user(childFetcher, cfg -> cfg.fetchType(fetchType));
    }

    @NewChain
    public BiometricFetcher publicKey() {
        return add("publicKey");
    }

    @NewChain
    public BiometricFetcher publicKey(boolean enabled) {
        return enabled ? add("publicKey") : remove("publicKey");
    }

    @NewChain
    public BiometricFetcher deviceId() {
        return add("deviceId");
    }

    @NewChain
    public BiometricFetcher deviceId(boolean enabled) {
        return enabled ? add("deviceId") : remove("deviceId");
    }

    @NewChain
    public BiometricFetcher lastUsedAt() {
        return add("lastUsedAt");
    }

    @NewChain
    public BiometricFetcher lastUsedAt(boolean enabled) {
        return enabled ? add("lastUsedAt") : remove("lastUsedAt");
    }

    @Override
    protected BiometricFetcher createFetcher(ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        return new BiometricFetcher(this, prop, negative, idOnlyFetchType);
    }

    @Override
    protected BiometricFetcher createFetcher(ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        return new BiometricFetcher(this, prop, fieldConfig);
    }
}
