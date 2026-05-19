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
        type = Licenses.class
)
public class LicensesFetcher extends AbstractTypedFetcher<Licenses, LicensesFetcher> {
    public static final LicensesFetcher $ = new LicensesFetcher(null);

    private LicensesFetcher(FetcherImpl<Licenses> base) {
        super(Licenses.class, base);
    }

    private LicensesFetcher(LicensesFetcher prev, ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        super(prev, prop, negative, idOnlyFetchType);
    }

    private LicensesFetcher(LicensesFetcher prev, ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        super(prev, prop, fieldConfig);
    }

    public static LicensesFetcher $from(Fetcher<Licenses> base) {
        return base instanceof LicensesFetcher ? 
        	(LicensesFetcher)base : 
        	new LicensesFetcher((FetcherImpl<Licenses>)base);
    }

    @NewChain
    public LicensesFetcher licenseKey() {
        return add("licenseKey");
    }

    @NewChain
    public LicensesFetcher licenseKey(boolean enabled) {
        return enabled ? add("licenseKey") : remove("licenseKey");
    }

    @NewChain
    public LicensesFetcher organizationId() {
        return add("organizationId");
    }

    @NewChain
    public LicensesFetcher organizationId(boolean enabled) {
        return enabled ? add("organizationId") : remove("organizationId");
    }

    @NewChain
    public LicensesFetcher skuId() {
        return add("skuId");
    }

    @NewChain
    public LicensesFetcher skuId(boolean enabled) {
        return enabled ? add("skuId") : remove("skuId");
    }

    @NewChain
    public LicensesFetcher tierId() {
        return add("tierId");
    }

    @NewChain
    public LicensesFetcher tierId(boolean enabled) {
        return enabled ? add("tierId") : remove("tierId");
    }

    @NewChain
    public LicensesFetcher type() {
        return add("type");
    }

    @NewChain
    public LicensesFetcher type(boolean enabled) {
        return enabled ? add("type") : remove("type");
    }

    @NewChain
    public LicensesFetcher status() {
        return add("status");
    }

    @NewChain
    public LicensesFetcher status(boolean enabled) {
        return enabled ? add("status") : remove("status");
    }

    @NewChain
    public LicensesFetcher productName() {
        return add("productName");
    }

    @NewChain
    public LicensesFetcher productName(boolean enabled) {
        return enabled ? add("productName") : remove("productName");
    }

    @NewChain
    public LicensesFetcher tierName() {
        return add("tierName");
    }

    @NewChain
    public LicensesFetcher tierName(boolean enabled) {
        return enabled ? add("tierName") : remove("tierName");
    }

    @NewChain
    public LicensesFetcher totalSeats() {
        return add("totalSeats");
    }

    @NewChain
    public LicensesFetcher totalSeats(boolean enabled) {
        return enabled ? add("totalSeats") : remove("totalSeats");
    }

    @NewChain
    public LicensesFetcher assignedSeats() {
        return add("assignedSeats");
    }

    @NewChain
    public LicensesFetcher assignedSeats(boolean enabled) {
        return enabled ? add("assignedSeats") : remove("assignedSeats");
    }

    @NewChain
    public LicensesFetcher validFrom() {
        return add("validFrom");
    }

    @NewChain
    public LicensesFetcher validFrom(boolean enabled) {
        return enabled ? add("validFrom") : remove("validFrom");
    }

    @NewChain
    public LicensesFetcher validUntil() {
        return add("validUntil");
    }

    @NewChain
    public LicensesFetcher validUntil(boolean enabled) {
        return enabled ? add("validUntil") : remove("validUntil");
    }

    @NewChain
    public LicensesFetcher entitlements() {
        return add("entitlements");
    }

    @NewChain
    public LicensesFetcher entitlements(boolean enabled) {
        return enabled ? add("entitlements") : remove("entitlements");
    }

    @NewChain
    public LicensesFetcher createdAt() {
        return add("createdAt");
    }

    @NewChain
    public LicensesFetcher createdAt(boolean enabled) {
        return enabled ? add("createdAt") : remove("createdAt");
    }

    @NewChain
    public LicensesFetcher updatedAt() {
        return add("updatedAt");
    }

    @NewChain
    public LicensesFetcher updatedAt(boolean enabled) {
        return enabled ? add("updatedAt") : remove("updatedAt");
    }

    @NewChain
    public LicensesFetcher organizations() {
        return add("organizations");
    }

    @NewChain
    public LicensesFetcher organizations(boolean enabled) {
        return enabled ? add("organizations") : remove("organizations");
    }

    @NewChain
    public LicensesFetcher organizations(Fetcher<Organizations> childFetcher) {
        return add("organizations", childFetcher);
    }

    @NewChain
    public LicensesFetcher organizations(IdOnlyFetchType idOnlyFetchType) {
        return add("organizations", idOnlyFetchType);
    }

    @NewChain
    public LicensesFetcher organizations(Fetcher<Organizations> childFetcher,
            Consumer<ReferenceFieldConfig<Organizations, OrganizationsTable>> fieldConfig) {
        return add("organizations", childFetcher, fieldConfig);
    }

    @NewChain
    public LicensesFetcher organizations(ReferenceFetchType fetchType,
            Fetcher<Organizations> childFetcher) {
        return organizations(childFetcher, cfg -> cfg.fetchType(fetchType));
    }

    @NewChain
    public LicensesFetcher addons() {
        return add("addons");
    }

    @NewChain
    public LicensesFetcher addons(boolean enabled) {
        return enabled ? add("addons") : remove("addons");
    }

    @NewChain
    public LicensesFetcher addons(Fetcher<LicenseAddons> childFetcher) {
        return add("addons", childFetcher);
    }

    @NewChain
    public LicensesFetcher addons(Fetcher<LicenseAddons> childFetcher,
            Consumer<ListFieldConfig<LicenseAddons, LicenseAddonsTable>> fieldConfig) {
        return add("addons", childFetcher, fieldConfig);
    }

    @Override
    protected LicensesFetcher createFetcher(ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        return new LicensesFetcher(this, prop, negative, idOnlyFetchType);
    }

    @Override
    protected LicensesFetcher createFetcher(ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        return new LicensesFetcher(this, prop, fieldConfig);
    }
}
