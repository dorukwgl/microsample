package com.doruk.infrastructure.persistence.entity;

import com.doruk.domain.shared.enums.LicenseStatus;
import com.doruk.domain.shared.enums.LicenseType;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.function.Function;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.meta.ImmutableType;
import org.babyfish.jimmer.meta.TypedProp;
import org.babyfish.jimmer.sql.JoinType;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.babyfish.jimmer.sql.ast.PropExpression;
import org.babyfish.jimmer.sql.ast.Selection;
import org.babyfish.jimmer.sql.ast.table.Props;
import org.babyfish.jimmer.sql.ast.table.PropsFor;

@GeneratedBy(
        type = Licenses.class
)
@PropsFor(Licenses.class)
public interface LicensesProps extends Props, Selection<Licenses> {
    TypedProp.Scalar<Licenses, UUID> ID = 
        TypedProp.scalar(ImmutableType.get(Licenses.class).getProp("id"));

    TypedProp.Scalar<Licenses, String> LICENSE_KEY = 
        TypedProp.scalar(ImmutableType.get(Licenses.class).getProp("licenseKey"));

    TypedProp.Scalar<Licenses, UUID> ORGANIZATION_ID = 
        TypedProp.scalar(ImmutableType.get(Licenses.class).getProp("organizationId"));

    TypedProp.Scalar<Licenses, UUID> SKU_ID = 
        TypedProp.scalar(ImmutableType.get(Licenses.class).getProp("skuId"));

    TypedProp.Scalar<Licenses, Long> TIER_ID = 
        TypedProp.scalar(ImmutableType.get(Licenses.class).getProp("tierId"));

    TypedProp.Scalar<Licenses, LicenseType> TYPE = 
        TypedProp.scalar(ImmutableType.get(Licenses.class).getProp("type"));

    TypedProp.Scalar<Licenses, LicenseStatus> STATUS = 
        TypedProp.scalar(ImmutableType.get(Licenses.class).getProp("status"));

    TypedProp.Scalar<Licenses, String> PRODUCT_NAME = 
        TypedProp.scalar(ImmutableType.get(Licenses.class).getProp("productName"));

    TypedProp.Scalar<Licenses, String> TIER_NAME = 
        TypedProp.scalar(ImmutableType.get(Licenses.class).getProp("tierName"));

    TypedProp.Scalar<Licenses, Integer> TOTAL_SEATS = 
        TypedProp.scalar(ImmutableType.get(Licenses.class).getProp("totalSeats"));

    TypedProp.Scalar<Licenses, Integer> ASSIGNED_SEATS = 
        TypedProp.scalar(ImmutableType.get(Licenses.class).getProp("assignedSeats"));

    TypedProp.Scalar<Licenses, OffsetDateTime> VALID_FROM = 
        TypedProp.scalar(ImmutableType.get(Licenses.class).getProp("validFrom"));

    TypedProp.Scalar<Licenses, OffsetDateTime> VALID_UNTIL = 
        TypedProp.scalar(ImmutableType.get(Licenses.class).getProp("validUntil"));

    TypedProp.Scalar<Licenses, String> ENTITLEMENTS = 
        TypedProp.scalar(ImmutableType.get(Licenses.class).getProp("entitlements"));

    TypedProp.Scalar<Licenses, OffsetDateTime> CREATED_AT = 
        TypedProp.scalar(ImmutableType.get(Licenses.class).getProp("createdAt"));

    TypedProp.Scalar<Licenses, OffsetDateTime> UPDATED_AT = 
        TypedProp.scalar(ImmutableType.get(Licenses.class).getProp("updatedAt"));

    TypedProp.Reference<Licenses, Organizations> ORGANIZATIONS = 
        TypedProp.reference(ImmutableType.get(Licenses.class).getProp("organizations"));

    TypedProp.ReferenceList<Licenses, LicenseAddons> ADDONS = 
        TypedProp.referenceList(ImmutableType.get(Licenses.class).getProp("addons"));

    PropExpression.Cmp<UUID> id();

    PropExpression.Str licenseKey();

    PropExpression.Cmp<UUID> organizationId();

    PropExpression.Cmp<UUID> skuId();

    PropExpression.Num<Long> tierId();

    PropExpression.Cmp<LicenseType> type();

    PropExpression.Cmp<LicenseStatus> status();

    PropExpression.Str productName();

    PropExpression.Str tierName();

    PropExpression.Num<Integer> totalSeats();

    PropExpression.Num<Integer> assignedSeats();

    PropExpression.Tp<OffsetDateTime> validFrom();

    PropExpression.Tp<OffsetDateTime> validUntil();

    PropExpression.Str entitlements();

    PropExpression.Tp<OffsetDateTime> createdAt();

    PropExpression.Tp<OffsetDateTime> updatedAt();

    OrganizationsTable organizations();

    OrganizationsTable organizations(JoinType joinType);

    PropExpression.Cmp<UUID> organizationsId();

    Predicate addons(Function<LicenseAddonsTableEx, Predicate> block);
}
