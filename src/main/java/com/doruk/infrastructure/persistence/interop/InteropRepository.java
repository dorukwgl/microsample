package com.doruk.infrastructure.persistence.interop;

import com.doruk.application.app.interop.dto.LicenseInfoResponse;
import com.doruk.domain.shared.enums.OrganizationType;
import com.doruk.infrastructure.persistence.entity.*;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.JoinType;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.jooq.DSLContext;

import java.util.Optional;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class InteropRepository {
    private final JSqlClient slqClient;
    private final DSLContext dsl;

    private Optional<Organizations> getUserOrg(UUID userId) {
        var t = UserTable.$;
        return slqClient.createQuery(t)
                .where(t.id().eq(userId))
                .select(t.organization(JoinType.INNER))
                .execute()
                .stream()
                .findFirst();
    }

    private LicenseInfoResponse buildAddonInfo(LicenseAddons rec) {
        return LicenseInfoResponse.builder()
                .id(rec.id().toString())
                .skuId(rec.skuId().toString())
                .tierId(rec.tierId().toString())
                .status(rec.status())
                .name(rec.productName())
                .tierName(rec.tierName())
                .entitlements(rec.entitlements())
                .validUntil(rec.validUntil())
                .createdAt(rec.createdAt())
                .build();
    }

    private LicenseInfoResponse.LicenseInfoResponseBuilder buildLicenseInfo(Licenses rec) {
        return LicenseInfoResponse.builder()
                .id(rec.id().toString())
                .skuId(rec.skuId().toString())
                .tierId(rec.tierId().toString())
                .type(rec.type())
                .status(rec.status())
                .name(rec.productName())
                .tierName(rec.tierName())
                .entitlements(rec.entitlements())
                .validFrom(rec.validFrom())
                .validUntil(rec.validUntil())
                .createdAt(rec.createdAt())
                .updatedAt(rec.updatedAt())
                .licenseKey(rec.licenseKey())
                .organizationId(rec.organizationId().toString())
                .totalSeats(rec.totalSeats())
                .assignedSeats(rec.assignedSeats());
    }

    private Optional<LicenseInfoResponse> fetchLicense(UUID orgId, UUID skuId) {
        var t = LicensesTable.$;
        return slqClient.createQuery(t)
                .where(Predicate.and(
                        t.organizationId().eq(orgId),
                        t.skuId().eq(skuId)
                ))
                .select(t.fetch(LicensesFetcher.$
                        .allScalarFields()
                        .addons()
                ))
                .execute()
                .stream()
                .map(rec -> this.buildLicenseInfo(rec)
                        .addons(rec.addons().stream().map(this::buildAddonInfo).toList())
                        .build())
                .findFirst();
    }

    private Optional<LicenseInfoResponse> fetchOrganizationLicense(UUID orgId, UUID userId, UUID skuId) {
        var l = com.doruk.jooq.tables.Licenses.LICENSES;
        var ls = com.doruk.jooq.tables.LicenseSeats.LICENSE_SEATS;

        var hasSeat = dsl.select(l.ID)
                .from(l)
                .join(ls).on(l.ID.eq(ls.LICENSE_ID))
                .where(l.ORGANIZATION_ID.eq(orgId))
                .and(l.SKU_ID.eq(skuId))
                .and(ls.USER_ID.eq(userId))
                .fetchAny() != null;

        return hasSeat ? fetchLicense(orgId, skuId)
                : Optional.empty();
    }

    public Optional<LicenseInfoResponse> getLicenseInfo(UUID userId, UUID skuId) {
        var org = getUserOrg(userId)
                .orElseThrow(() -> new IllegalStateException("User's organization not found"));

        return org.type().equals(OrganizationType.PERSONAL)
                ? fetchLicense(org.id(), skuId)
                : fetchOrganizationLicense(org.id(), userId, skuId);
    }
}
