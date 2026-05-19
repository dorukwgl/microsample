package com.doruk.infrastructure.persistence.entity;

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
        type = LicenseSeats.class
)
@PropsFor(LicenseSeats.class)
public interface LicenseSeatsProps extends Props, Selection<LicenseSeats> {
    TypedProp.Scalar<LicenseSeats, UUID> ID = 
        TypedProp.scalar(ImmutableType.get(LicenseSeats.class).getProp("id"));

    TypedProp.Reference<LicenseSeats, Licenses> LICENSES = 
        TypedProp.reference(ImmutableType.get(LicenseSeats.class).getProp("licenses"));

    TypedProp.Scalar<LicenseSeats, UUID> LICENSE_ID = 
        TypedProp.scalar(ImmutableType.get(LicenseSeats.class).getProp("licenseId"));

    TypedProp.Reference<LicenseSeats, User> USER = 
        TypedProp.reference(ImmutableType.get(LicenseSeats.class).getProp("user"));

    TypedProp.Scalar<LicenseSeats, UUID> USER_ID = 
        TypedProp.scalar(ImmutableType.get(LicenseSeats.class).getProp("userId"));

    TypedProp.Scalar<LicenseSeats, OffsetDateTime> ASSIGNED_AT = 
        TypedProp.scalar(ImmutableType.get(LicenseSeats.class).getProp("assignedAt"));

    TypedProp.Reference<LicenseSeats, User> ASSIGNED_BY = 
        TypedProp.reference(ImmutableType.get(LicenseSeats.class).getProp("assignedBy"));

    TypedProp.Scalar<LicenseSeats, UUID> ASSIGNED_BY_ID = 
        TypedProp.scalar(ImmutableType.get(LicenseSeats.class).getProp("assignedById"));

    PropExpression.Cmp<UUID> id();

    LicensesTable licenses();

    LicensesTable licenses(JoinType joinType);

    PropExpression.Cmp<UUID> licenseId();

    UserTable user();

    UserTable user(JoinType joinType);

    PropExpression.Cmp<UUID> userId();

    PropExpression.Tp<OffsetDateTime> assignedAt();

    UserTable assignedBy();

    UserTable assignedBy(JoinType joinType);

    PropExpression.Cmp<UUID> assignedById();
}
