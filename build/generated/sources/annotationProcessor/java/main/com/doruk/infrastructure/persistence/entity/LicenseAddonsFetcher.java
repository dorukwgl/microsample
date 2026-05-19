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
        type = LicenseAddons.class
)
public class LicenseAddonsFetcher extends AbstractTypedFetcher<LicenseAddons, LicenseAddonsFetcher> {
    public static final LicenseAddonsFetcher $ = new LicenseAddonsFetcher(null);

    private LicenseAddonsFetcher(FetcherImpl<LicenseAddons> base) {
        super(LicenseAddons.class, base);
    }

    private LicenseAddonsFetcher(LicenseAddonsFetcher prev, ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        super(prev, prop, negative, idOnlyFetchType);
    }

    private LicenseAddonsFetcher(LicenseAddonsFetcher prev, ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        super(prev, prop, fieldConfig);
    }

    public static LicenseAddonsFetcher $from(Fetcher<LicenseAddons> base) {
        return base instanceof LicenseAddonsFetcher ? 
        	(LicenseAddonsFetcher)base : 
        	new LicenseAddonsFetcher((FetcherImpl<LicenseAddons>)base);
    }

    @NewChain
    public LicenseAddonsFetcher licenseId() {
        return add("licenseId");
    }

    @NewChain
    public LicenseAddonsFetcher licenseId(boolean enabled) {
        return enabled ? add("licenseId") : remove("licenseId");
    }

    @NewChain
    public LicenseAddonsFetcher skuId() {
        return add("skuId");
    }

    @NewChain
    public LicenseAddonsFetcher skuId(boolean enabled) {
        return enabled ? add("skuId") : remove("skuId");
    }

    @NewChain
    public LicenseAddonsFetcher tierId() {
        return add("tierId");
    }

    @NewChain
    public LicenseAddonsFetcher tierId(boolean enabled) {
        return enabled ? add("tierId") : remove("tierId");
    }

    @NewChain
    public LicenseAddonsFetcher validUntil() {
        return add("validUntil");
    }

    @NewChain
    public LicenseAddonsFetcher validUntil(boolean enabled) {
        return enabled ? add("validUntil") : remove("validUntil");
    }

    @NewChain
    public LicenseAddonsFetcher status() {
        return add("status");
    }

    @NewChain
    public LicenseAddonsFetcher status(boolean enabled) {
        return enabled ? add("status") : remove("status");
    }

    @NewChain
    public LicenseAddonsFetcher productName() {
        return add("productName");
    }

    @NewChain
    public LicenseAddonsFetcher productName(boolean enabled) {
        return enabled ? add("productName") : remove("productName");
    }

    @NewChain
    public LicenseAddonsFetcher tierName() {
        return add("tierName");
    }

    @NewChain
    public LicenseAddonsFetcher tierName(boolean enabled) {
        return enabled ? add("tierName") : remove("tierName");
    }

    @NewChain
    public LicenseAddonsFetcher entitlements() {
        return add("entitlements");
    }

    @NewChain
    public LicenseAddonsFetcher entitlements(boolean enabled) {
        return enabled ? add("entitlements") : remove("entitlements");
    }

    @NewChain
    public LicenseAddonsFetcher totalSeats() {
        return add("totalSeats");
    }

    @NewChain
    public LicenseAddonsFetcher totalSeats(boolean enabled) {
        return enabled ? add("totalSeats") : remove("totalSeats");
    }

    @NewChain
    public LicenseAddonsFetcher createdAt() {
        return add("createdAt");
    }

    @NewChain
    public LicenseAddonsFetcher createdAt(boolean enabled) {
        return enabled ? add("createdAt") : remove("createdAt");
    }

    @NewChain
    public LicenseAddonsFetcher licenses() {
        return add("licenses");
    }

    @NewChain
    public LicenseAddonsFetcher licenses(boolean enabled) {
        return enabled ? add("licenses") : remove("licenses");
    }

    @NewChain
    public LicenseAddonsFetcher licenses(Fetcher<Licenses> childFetcher) {
        return add("licenses", childFetcher);
    }

    @NewChain
    public LicenseAddonsFetcher licenses(IdOnlyFetchType idOnlyFetchType) {
        return add("licenses", idOnlyFetchType);
    }

    @NewChain
    public LicenseAddonsFetcher licenses(Fetcher<Licenses> childFetcher,
            Consumer<ReferenceFieldConfig<Licenses, LicensesTable>> fieldConfig) {
        return add("licenses", childFetcher, fieldConfig);
    }

    @NewChain
    public LicenseAddonsFetcher licenses(ReferenceFetchType fetchType,
            Fetcher<Licenses> childFetcher) {
        return licenses(childFetcher, cfg -> cfg.fetchType(fetchType));
    }

    @Override
    protected LicenseAddonsFetcher createFetcher(ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        return new LicenseAddonsFetcher(this, prop, negative, idOnlyFetchType);
    }

    @Override
    protected LicenseAddonsFetcher createFetcher(ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        return new LicenseAddonsFetcher(this, prop, fieldConfig);
    }
}
