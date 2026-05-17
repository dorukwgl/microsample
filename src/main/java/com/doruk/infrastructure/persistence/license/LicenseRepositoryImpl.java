package com.doruk.infrastructure.persistence.license;

import com.doruk.application.app.license.dto.*;
import com.doruk.application.app.license.repo.LicenseServiceRepository;
import com.doruk.application.dto.PageQuery;
import com.doruk.application.dto.PageResponse;
import com.doruk.application.enums.SortOrder;
import com.doruk.domain.license.entity.License;
import com.doruk.domain.shared.enums.LicenseStatus;
import com.doruk.infrastructure.persistence.entity.*;
import com.doruk.infrastructure.persistence.mapper.PageMapper;
import com.doruk.jooq.tables.LicenseSeats;
import com.doruk.jooq.tables.Licenses;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.babyfish.jimmer.sql.ast.mutation.SaveMode;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Singleton
@RequiredArgsConstructor
public class LicenseRepositoryImpl implements LicenseServiceRepository {
    private final JSqlClient slqClient;
    private final DSLContext dsl;

    private License selectLicenseForUpdate(DSLContext ctx, UUID licenseId) {
        var l = Licenses.LICENSES;
        return ctx.select(
                        l.ID,
                        l.STATUS,
                        l.TOTAL_SEATS,
                        l.ASSIGNED_SEATS
                )
                .from(l)
                .where(l.ID.eq(licenseId))
                .forUpdate()
                .fetchOptional()
                .map(r -> License.builder()
                        .id(r.value1())
                        .status(LicenseStatus.valueOf(r.value2().name()))
                        .totalSeats(r.value3())
                        .assignedSeats(r.value4())
                        .build())
                .orElseThrow();
    }

    private LicenseCmd upsertAddonLicense(String parentLicenseId, String skuId, String entitlements, int seats, Long tierId, java.time.OffsetDateTime validUntil, LicenseStatus status, String productName, String tierName) {
        var draft = LicenseAddonsDraft.$.produce(d -> {
            d.setLicenseId(UUID.fromString(parentLicenseId));
            d.setSkuId(UUID.fromString(skuId));
            d.setEntitlements(entitlements);
            d.setTotalSeats(seats);
            d.setTierId(tierId);
            d.setValidUntil(validUntil);
            d.setStatus(status);
            d.setProductName(productName);
            d.setTierName(tierName);
        });

        var created = slqClient.saveCommand(draft)
                .setMode(SaveMode.UPSERT)
                .setUpsertMask(LicenseAddonsTable.LICENSE_ID, LicenseAddonsTable.SKU_ID)
                .execute()
                .getModifiedEntity();

        var parentSkuId = dsl.select(Licenses.LICENSES.SKU_ID)
                .from(Licenses.LICENSES)
                .where(Licenses.LICENSES.ID.eq(UUID.fromString(parentLicenseId)))
                .fetchOptional()
                .map(Licenses.LICENSES.SKU_ID::get)
                .map(UUID::toString);

        return LicenseCmd.builder()
                .id(created.id().toString())
                .isAddon(true)
                .parentSkuId(parentSkuId)
                .seats(created.totalSeats())
                .tierId(created.tierId())
                .validUntil(created.validUntil())
                .status(created.status())
                .skuId(created.skuId().toString())
                .entitlements(created.entitlements())
                .productName(created.productName())
                .tierName(created.tierName())
                .build();
    }

    private LicenseCmd mapLicense(com.doruk.infrastructure.persistence.entity.Licenses created) {
        return LicenseCmd.builder()
                .id(created.id().toString())
                .licenseKey(created.licenseKey())
                .orgId(created.organizationId().toString())
                .skuId(created.skuId().toString())
                .seats(created.totalSeats())
                .tierId(created.tierId())
                .validUntil(created.validUntil())
                .status(created.status())
                .isAddon(false)
                .parentSkuId(Optional.empty())
                .entitlements(created.entitlements())
                .productName(created.productName())
                .tierName(created.tierName())
                .build();
    }

    @Override
    public boolean orgExists(String orgId) {
        var t = OrganizationsTable.$;
        return slqClient.createQuery(t)
                .where(t.id().eq(UUID.fromString(orgId)))
                .exists();
    }

    @Override
    public Optional<LicenseCmd> findLicense(String orgId, String skuId) {
        var t = LicensesTable.$;
        return slqClient.createQuery(t)
                .where(Predicate.and(
                        t.organizationId().eq(UUID.fromString(orgId)),
                        t.skuId().eq(UUID.fromString(skuId))
                )).select(t)
                .execute()
                .stream().map(l -> LicenseCmd.builder()
                        .id(l.id().toString())
                        .seats(l.totalSeats())
                        .licenseKey(l.licenseKey())
                        .tierId(l.tierId())
                        .skuId(l.skuId().toString())
                        .orgId(l.organizationId().toString())
                        .status(l.status())
                        .validUntil(l.validUntil())
                        .isAddon(false)
                        .parentSkuId(Optional.empty())
                        .entitlements(l.entitlements())
                        .productName(l.productName())
                        .tierName(l.tierName())
                        .build()
                )
                .findFirst();
    }

    @Override
    public Optional<LicenseCmd> findAddon(String parentLicenseId, String skuId) {
        var t = LicenseAddonsTable.$;
        return slqClient.createQuery(t)
                .where(Predicate.and(
                        t.licenseId().eq(UUID.fromString(parentLicenseId)),
                        t.skuId().eq(UUID.fromString(skuId))
                )).select(t)
                .execute().stream()
                .map(a -> LicenseCmd.builder()
                        .id(a.id().toString())
                        .seats(a.totalSeats())
                        .tierId(a.tierId())
                        .skuId(a.skuId().toString())
                        .validUntil(a.validUntil())
                        .status(a.status())
                        .isAddon(true)
                        .parentSkuId(Optional.of(a.licenseId().toString()))
                        .entitlements(a.entitlements())
                        .productName(a.productName())
                        .tierName(a.tierName())
                        .build())
                .findFirst();
    }

    @Override
    public LicenseCmd upsertProductLicense(String licenseKey, PaymentCompletedEvent dto) {
        // upsert on org id and sku id
        var draft = LicensesDraft.$.produce(d -> {
            d.setLicenseKey(licenseKey);
            d.setOrganizationId(UUID.fromString(dto.externalOrgId()));
            d.setSkuId(UUID.fromString(dto.skuId()));
            d.setTotalSeats(dto.seats());
            d.setTierId(dto.tierId());
            d.setValidUntil(dto.validUntil());
            d.setStatus(LicenseStatus.ACTIVE);
            d.setEntitlements(dto.entitlements());
            d.setProductName(dto.productName());
            d.setTierName(dto.tierName());
        });

        var created = slqClient.saveCommand(draft)
                .setMode(SaveMode.UPSERT)
                .setUpsertMask(LicensesTable.ORGANIZATION_ID, LicensesTable.SKU_ID)
                .execute()
                .getModifiedEntity();

        return mapLicense(created);
    }

    @Override
    public LicenseCmd upsertAddonLicense(String licenseId, PaymentCompletedEvent dto) {
        return upsertAddonLicense(licenseId, dto.skuId(), dto.entitlements(), dto.seats(), dto.tierId(), dto.validUntil(), LicenseStatus.ACTIVE, dto.productName(), dto.tierName());
    }

    @Override
    public LicenseCmd upsertProductLicenseRenew(String licenseId, PaymentRenewedEvent dto, LicenseStatus status) {
        var draft = LicensesDraft.$.produce(d -> {
            d.setId(UUID.fromString(licenseId));
            d.setValidUntil(dto.validUntil());
            d.setStatus(status);
            d.setTotalSeats(dto.seats());
            d.setEntitlements(dto.entitlements());
            d.setTierId(dto.tierId());
            d.setSkuId(UUID.fromString(dto.skuId()));
            d.setOrganizationId(UUID.fromString(dto.externalOrgId()));
            d.setProductName(dto.productName());
            d.setTierName(dto.tierName());
        });

        var created = slqClient.saveCommand(draft)
                .setMode(SaveMode.UPSERT)
                .setUpsertMask(LicensesTable.ID, LicensesTable.SKU_ID, LicensesTable.ORGANIZATION_ID)
                .execute()
                .getModifiedEntity();

        return LicenseCmd.builder()
                .id(created.id().toString())
                .validUntil(created.validUntil())
                .status(created.status())
                .entitlements(created.entitlements())
                .seats(created.totalSeats())
                .tierId(created.tierId())
                .skuId(created.skuId().toString())
                .isAddon(false)
                .parentSkuId(Optional.empty())
                .orgId(created.organizationId().toString())
                .productName(created.productName())
                .tierName(created.tierName())
                .build();
    }

    @Override
    public LicenseCmd upsertAddonLicenseRenew(String parentLicenseId, PaymentRenewedEvent dto, LicenseStatus status) {
        return upsertAddonLicense(parentLicenseId, dto.skuId(), dto.entitlements(), dto.seats(), dto.tierId(), dto.validUntil(), status, dto.productName(), dto.tierName());
    }

    @Override
    public LicenseCmd updateLicenseStatus(String licenseId, LicenseStatus status) {
        var draft = LicensesDraft.$.produce(d -> {
            d.setId(UUID.fromString(licenseId));
            d.setStatus(status);
        });

        var updated = slqClient.saveCommand(draft)
                .setMode(SaveMode.UPDATE_ONLY)
                .execute()
                .getModifiedEntity();

        return mapLicense(updated);
    }

    @Override
    public LicenseCmd updateAddonLicenseStatus(String addonId, LicenseStatus status) {
        var draft = LicenseAddonsDraft.$.produce(d -> {
            d.setId(UUID.fromString(addonId));
            d.setStatus(status);
        });

        var updated = slqClient.saveCommand(draft)
                .setMode(SaveMode.UPDATE_ONLY)
                .execute()
                .getModifiedEntity();

        var parentSkuId = dsl.select(Licenses.LICENSES.SKU_ID)
                .from(Licenses.LICENSES)
                .where(Licenses.LICENSES.ID.eq(updated.licenseId()))
                .fetchOptional()
                .map(Licenses.LICENSES.SKU_ID::get)
                .map(UUID::toString);

        return LicenseCmd.builder()
                .id(updated.id().toString())
                .isAddon(true)
                .parentSkuId(parentSkuId)
                .seats(updated.totalSeats())
                .tierId(updated.tierId())
                .validUntil(updated.validUntil())
                .status(updated.status())
                .skuId(updated.skuId().toString())
                .entitlements(updated.entitlements())
                .productName(updated.productName())
                .tierName(updated.tierName())
                .build();
    }

    @Override
    public PageResponse<LicenseResponse> getLicenses(String userId, PageQuery query) {
        var orgId = slqClient.createQuery(UserTable.$)
                .where(UserTable.$.id().eq(UUID.fromString(userId)))
                .select(UserTable.$.organizationId())
                .execute()
                .stream().findFirst().orElseThrow(() -> new RuntimeException("User not found: " + userId));

        var t = LicensesTable.$;
        var res = slqClient.createQuery(t)
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

    @Override
    public Optional<LicenseResponse> findLicenseByKey(String licenseKey) {
        var t = LicensesTable.$;
        return slqClient.createQuery(t)
                .where(t.licenseKey().eq(licenseKey))
                .select(t.fetch(
                        LicensesFetcher.$
                                .allScalarFields()
                                .addons(LicenseAddonsFetcher.$.allScalarFields())
                ))
                .execute()
                .stream()
                .findFirst()
                .map(l -> LicenseResponse.builder()
                        .id(l.id().toString())
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

    @Override
    public PageResponse<SeatDto> getAllocatedSeats(String licenseId, PageQuery query) {
        var t = LicenseSeatsTable.$;
        var page = slqClient.createQuery(t)
                .where(t.licenseId().eq(UUID.fromString(licenseId)))
                .orderBy(query.order() == SortOrder.DESC ? t.assignedAt().desc() : t.assignedAt().asc())
                .select(t.fetch(
                        LicenseSeatsFetcher.$
                                .assignedAt()
                                .user(
                                        UserFetcher.$
                                                .username()
                                                .email()
                                )
                                .assignedBy(
                                        UserFetcher.$
                                                .username()
                                )
                ))
                .fetchPage(query.page(), query.size());

        return PageMapper.toResponse(page, row -> SeatDto.builder()
                .userId(row.user().id().toString())
                .username(row.user().username())
                .email(row.user().email())
                .assignedAt(row.assignedAt())
                .assignedBy(row.assignedBy().username())
                .build());
    }

    @Override
    public boolean userExistsOnOrg(UUID orgId, UUID userId) {
        return false;
    }

    @Override
    public boolean isSeatAssigned(UUID licenseId, UUID userId) {
        return false;
    }

    @Override
    public void assignSeatTransactional(UUID adminId, UUID licenseId, UUID userId, Consumer<License> domainFn) {
        dsl.transaction(txn -> {
            var ctx = DSL.using(txn);
            var rec = selectLicenseForUpdate(ctx, licenseId);

            domainFn.accept(rec);

            // assign license to user
            var la = LicenseSeats.LICENSE_SEATS;
            ctx.insertInto(la)
                    .set(la.LICENSE_ID, licenseId)
                    .set(la.USER_ID, userId)
                    .set(la.ASSIGNED_AT, OffsetDateTime.now())
                    .set(la.ASSIGNED_BY, adminId)
                    .execute();

            // increment/update the assigned seats
            var l = Licenses.LICENSES;
            ctx.update(l)
                    .set(l.ASSIGNED_SEATS, rec.getAssignedSeats())
                    .set(l.UPDATED_AT, OffsetDateTime.now())
                    .where(l.ID.eq(licenseId))
                    .execute();
        });
    }

    @Override
    public void releaseSeatTransactional(UUID licenseId, UUID userId, Consumer<License> domainFn) {
        // forgiving deletion, if not found avoid update
        dsl.transaction(txn -> {
            var ctx = DSL.using(txn);
            var rec = selectLicenseForUpdate(ctx, licenseId);

            // delete license seat
            var la = LicenseSeats.LICENSE_SEATS;
            var deleted = ctx.deleteFrom(la)
                    .where(la.LICENSE_ID.eq(licenseId).and(la.USER_ID.eq(userId)))
                    .returning(la.ID)
                    .fetchOptional();

            if (deleted.isEmpty())
                return;
            
            // perform seat release
            domainFn.accept(rec);
            
            // increment/update the assigned seats
            var l = Licenses.LICENSES;
            ctx.update(l)
                    .set(l.ASSIGNED_SEATS, rec.getAssignedSeats())
                    .set(l.UPDATED_AT, OffsetDateTime.now())
                    .where(l.ID.eq(licenseId))
                    .execute();

        });
    }
}
