package com.doruk.infrastructure.persistence.entity;

import java.lang.Override;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.lang.NewChain;
import org.babyfish.jimmer.meta.ImmutableProp;
import org.babyfish.jimmer.sql.ast.table.Table;
import org.babyfish.jimmer.sql.fetcher.Fetcher;
import org.babyfish.jimmer.sql.fetcher.FieldConfig;
import org.babyfish.jimmer.sql.fetcher.IdOnlyFetchType;
import org.babyfish.jimmer.sql.fetcher.impl.FetcherImpl;
import org.babyfish.jimmer.sql.fetcher.spi.AbstractTypedFetcher;

@GeneratedBy(
        type = ProductServers.class
)
public class ProductServersFetcher extends AbstractTypedFetcher<ProductServers, ProductServersFetcher> {
    public static final ProductServersFetcher $ = new ProductServersFetcher(null);

    private ProductServersFetcher(FetcherImpl<ProductServers> base) {
        super(ProductServers.class, base);
    }

    private ProductServersFetcher(ProductServersFetcher prev, ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        super(prev, prop, negative, idOnlyFetchType);
    }

    private ProductServersFetcher(ProductServersFetcher prev, ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        super(prev, prop, fieldConfig);
    }

    public static ProductServersFetcher $from(Fetcher<ProductServers> base) {
        return base instanceof ProductServersFetcher ? 
        	(ProductServersFetcher)base : 
        	new ProductServersFetcher((FetcherImpl<ProductServers>)base);
    }

    @NewChain
    public ProductServersFetcher name() {
        return add("name");
    }

    @NewChain
    public ProductServersFetcher name(boolean enabled) {
        return enabled ? add("name") : remove("name");
    }

    @NewChain
    public ProductServersFetcher skuId() {
        return add("skuId");
    }

    @NewChain
    public ProductServersFetcher skuId(boolean enabled) {
        return enabled ? add("skuId") : remove("skuId");
    }

    @NewChain
    public ProductServersFetcher hostUrl() {
        return add("hostUrl");
    }

    @NewChain
    public ProductServersFetcher hostUrl(boolean enabled) {
        return enabled ? add("hostUrl") : remove("hostUrl");
    }

    @NewChain
    public ProductServersFetcher publicKey() {
        return add("publicKey");
    }

    @NewChain
    public ProductServersFetcher publicKey(boolean enabled) {
        return enabled ? add("publicKey") : remove("publicKey");
    }

    @NewChain
    public ProductServersFetcher createdAt() {
        return add("createdAt");
    }

    @NewChain
    public ProductServersFetcher createdAt(boolean enabled) {
        return enabled ? add("createdAt") : remove("createdAt");
    }

    @NewChain
    public ProductServersFetcher updatedAt() {
        return add("updatedAt");
    }

    @NewChain
    public ProductServersFetcher updatedAt(boolean enabled) {
        return enabled ? add("updatedAt") : remove("updatedAt");
    }

    @Override
    protected ProductServersFetcher createFetcher(ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        return new ProductServersFetcher(this, prop, negative, idOnlyFetchType);
    }

    @Override
    protected ProductServersFetcher createFetcher(ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        return new ProductServersFetcher(this, prop, fieldConfig);
    }
}
