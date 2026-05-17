package com.doruk.infrastructure.persistence.admin;

import com.doruk.application.app.admin.dto.CreateProductServerCmd;
import com.doruk.application.app.admin.dto.ProductServerResponse;
import com.doruk.application.app.admin.repo.AdminServiceRepo;
import com.doruk.application.app.admin.dto.Addon;
import com.doruk.application.app.admin.dto.LicenseResponse;
import com.doruk.application.app.license.dto.LicenseCmd;
import com.doruk.application.dto.PageQuery;
import com.doruk.application.dto.PageResponse;
import com.doruk.application.enums.SortOrder;
import com.doruk.infrastructure.persistence.admin.mapper.ProductServerAdminMapper;
import com.doruk.infrastructure.persistence.entity.*;
import com.doruk.infrastructure.persistence.mapper.PageMapper;
import jakarta.inject.Singleton;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.LikeMode;
import org.jooq.DSLContext;

import java.util.Optional;
import java.util.UUID;

import static com.doruk.jooq.tables.ProductServers.PRODUCT_SERVERS;

@Singleton
@RequiredArgsConstructor
public class AdminRepoImpl implements AdminServiceRepo {
    private final JSqlClient sqlClient;
    private final DSLContext dsl;
    private final ProductServerAdminMapper mapper;

    @Override
    public ProductServerResponse create(CreateProductServerCmd cmd) {
        var draft = ProductServersDraft.$.produce(p -> p
                .setName(cmd.name())
                .setSkuId(cmd.skuId())
                .setHostUrl(cmd.hostUrl())
                .setPublicKey(cmd.publicKey())
        );
        var saved = sqlClient.saveCommand(draft).execute().getModifiedEntity();
        return mapper.toResponse(saved);
    }

    @Override
    public PageResponse<ProductServerResponse> list(PageQuery query, @Nullable String nameSearch) {
        var t = ProductServersTable.$;
        var res = sqlClient.createQuery(t)
                .where(t.name().ilikeIf(nameSearch, LikeMode.ANYWHERE))
                .orderBy(query.order() == SortOrder.ASC ? t.id().asc() : t.id().desc())
                .select(t.fetch(ProductServersFetcher.$.name().skuId().hostUrl().publicKey().createdAt().updatedAt()))
                .fetchPage(query.page(), query.size());

        return PageMapper.toResponse(res, mapper::toResponse);
    }

    @Override
    public void deleteBySkuId(String skuId) {
        dsl.deleteFrom(PRODUCT_SERVERS)
                .where(PRODUCT_SERVERS.SKU_ID.eq(skuId))
                .execute();
    }

    @Override
    public Optional<ProductServerResponse> findBySkuId(String skuId) {
        var t = ProductServersTable.$;
        return sqlClient.createQuery(t)
                .where(t.skuId().eq(skuId))
                .select(t.fetch(ProductServersFetcher.$
                        .name()
                        .skuId()
                        .hostUrl()
                        .publicKey()
                        .createdAt()
                        .updatedAt()))
                .execute()
                .stream()
                .findFirst()
                .map(mapper::toResponse);
    }

    @Override
    public Optional<LicenseCmd> findLicenseById(String licenseId) {
        var t = LicensesTable.$;
        return sqlClient.createQuery(t)
                .where(t.id().eq(UUID.fromString(licenseId)))
                .select(t)
                .execute()
                .stream()
                .findFirst()
                .map(l -> LicenseCmd.builder()
                        .id(l.id().toString())
                        .licenseKey(l.licenseKey())
                        .orgId(l.organizationId().toString())
                        .skuId(l.skuId().toString())
                        .seats(l.totalSeats())
                        .tierId(l.tierId())
                        .validUntil(l.validUntil())
                        .status(l.status())
                        .isAddon(false)
                        .parentSkuId(Optional.empty())
                        .entitlements(l.entitlements())
                        .productName(l.productName())
                        .tierName(l.tierName())
                        .build());
    }

    @Override
    public PageResponse<LicenseResponse> getLicensesByOrgId(UUID orgId, PageQuery query) {
        var t = LicensesTable.$;
        var res = sqlClient.createQuery(t)
                .where(t.organizationId().eq(orgId))
                .orderBy(query.order() == SortOrder.ASC ? t.id().asc() : t.id().desc())
                .select(t.fetch(
                        LicensesFetcher.$
                                .allScalarFields()
                                .addons(LicenseAddonsFetcher.$.allScalarFields())
                ))
                .fetchPage(query.page(), query.size());

        return PageMapper.toResponse(res, l -> LicenseResponse.builder()
                .productName(l.productName())
                .tierName(l.tierName())
                .licenseKey(l.licenseKey())
                .skuId(l.skuId().toString())
                .tierId(String.valueOf(l.tierId()))
                .status(l.status().name())
                .entitlements(l.entitlements())
                .seats(l.totalSeats())
                .totalSeats(l.totalSeats())
                .assignedSeats(l.assignedSeats())
                .organizationId(l.organizationId())
                .validFrom(l.validFrom())
                .validUntil(l.validUntil())
                .addons(l.addons().stream().map(a -> Addon.builder()
                        .productName(a.productName())
                        .tierName(a.tierName())
                        .skuId(a.skuId().toString())
                        .tierId(String.valueOf(a.tierId()))
                        .status(a.status().name())
                        .entitlements(a.entitlements())
                        .validUntil(a.validUntil())
                        .build()).toArray(Addon[]::new))
                .build());
    }
}
