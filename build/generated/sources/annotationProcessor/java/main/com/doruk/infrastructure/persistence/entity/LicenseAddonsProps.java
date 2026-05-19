package com.doruk.infrastructure.persistence.entity;

import com.doruk.domain.shared.enums.LicenseStatus;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.meta.ImmutableType;
import org.babyfish.jimmer.meta.TypedProp;
import org.babyfish.jimmer.sql.JoinType;
import org.babyfish.jimmer.sql.ast.PropExpression;
import org.babyfish.jimmer.sql.ast.Selection;
import org.babyfish.jimmer.sql.ast.table.Props;
import org.babyfish.jimmer.sql.ast.table.PropsFor;

@GeneratedBy(
        type = LicenseAddons.class
)
@PropsFor(LicenseAddons.class)
public interface LicenseAddonsProps extends Props, Selection<LicenseAddons> {
    TypedProp.Scalar<LicenseAddons, UUID> ID = 
        TypedProp.scalar(ImmutableType.get(LicenseAddons.class).getProp("id"));

    TypedProp.Scalar<LicenseAddons, UUID> LICENSE_ID = 
        TypedProp.scalar(ImmutableType.get(LicenseAddons.class).getProp("licenseId"));

    TypedProp.Scalar<LicenseAddons, UUID> SKU_ID = 
        TypedProp.scalar(ImmutableType.get(LicenseAddons.class).getProp("skuId"));

    TypedProp.Scalar<LicenseAddons, Long> TIER_ID = 
        TypedProp.scalar(ImmutableType.get(LicenseAddons.class).getProp("tierId"));

    TypedProp.Scalar<LicenseAddons, OffsetDateTime> VALID_UNTIL = 
        TypedProp.scalar(ImmutableType.get(LicenseAddons.class).getProp("validUntil"));

    TypedProp.Scalar<LicenseAddons, LicenseStatus> STATUS = 
        TypedProp.scalar(ImmutableType.get(LicenseAddons.class).getProp("status"));

    TypedProp.Scalar<LicenseAddons, String> PRODUCT_NAME = 
        TypedProp.scalar(ImmutableType.get(LicenseAddons.class).getProp("productName"));

    TypedProp.Scalar<LicenseAddons, String> TIER_NAME = 
        TypedProp.scalar(ImmutableType.get(LicenseAddons.class).getProp("tierName"));

    TypedProp.Scalar<LicenseAddons, String> ENTITLEMENTS = 
        TypedProp.scalar(ImmutableType.get(LicenseAddons.class).getProp("entitlements"));

    TypedProp.Scalar<LicenseAddons, Integer> TOTAL_SEATS = 
        TypedProp.scalar(ImmutableType.get(LicenseAddons.class).getProp("totalSeats"));

    TypedProp.Scalar<LicenseAddons, OffsetDateTime> CREATED_AT = 
        TypedProp.scalar(ImmutableType.get(LicenseAddons.class).getProp("createdAt"));

    TypedProp.Reference<LicenseAddons, Licenses> LICENSES = 
        TypedProp.reference(ImmutableType.get(LicenseAddons.class).getProp("licenses"));

    PropExpression.Cmp<UUID> id();

    PropExpression.Cmp<UUID> licenseId();

    PropExpression.Cmp<UUID> skuId();

    PropExpression.Num<Long> tierId();

    PropExpression.Tp<OffsetDateTime> validUntil();

    PropExpression.Cmp<LicenseStatus> status();

    PropExpression.Str productName();

    PropExpression.Str tierName();

    PropExpression.Str entitlements();

    PropExpression.Num<Integer> totalSeats();

    PropExpression.Tp<OffsetDateTime> createdAt();

    LicensesTable licenses();

    LicensesTable licenses(JoinType joinType);

    PropExpression.Cmp<UUID> licensesId();
}
