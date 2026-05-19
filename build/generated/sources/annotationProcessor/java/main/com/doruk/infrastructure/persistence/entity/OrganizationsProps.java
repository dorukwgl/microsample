package com.doruk.infrastructure.persistence.entity;

import com.doruk.domain.shared.enums.OrganizationType;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.function.Function;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.meta.ImmutableType;
import org.babyfish.jimmer.meta.TypedProp;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.babyfish.jimmer.sql.ast.PropExpression;
import org.babyfish.jimmer.sql.ast.Selection;
import org.babyfish.jimmer.sql.ast.table.Props;
import org.babyfish.jimmer.sql.ast.table.PropsFor;

@GeneratedBy(
        type = Organizations.class
)
@PropsFor(Organizations.class)
public interface OrganizationsProps extends Props, Selection<Organizations> {
    TypedProp.Scalar<Organizations, UUID> ID = 
        TypedProp.scalar(ImmutableType.get(Organizations.class).getProp("id"));

    TypedProp.Scalar<Organizations, String> NAME = 
        TypedProp.scalar(ImmutableType.get(Organizations.class).getProp("name"));

    TypedProp.Scalar<Organizations, OrganizationType> TYPE = 
        TypedProp.scalar(ImmutableType.get(Organizations.class).getProp("type"));

    TypedProp.Scalar<Organizations, String> ORG_CODE = 
        TypedProp.scalar(ImmutableType.get(Organizations.class).getProp("orgCode"));

    TypedProp.Scalar<Organizations, OffsetDateTime> CREATED_AT = 
        TypedProp.scalar(ImmutableType.get(Organizations.class).getProp("createdAt"));

    TypedProp.ReferenceList<Organizations, Licenses> LICENSES = 
        TypedProp.referenceList(ImmutableType.get(Organizations.class).getProp("licenses"));

    TypedProp.ReferenceList<Organizations, User> USERS = 
        TypedProp.referenceList(ImmutableType.get(Organizations.class).getProp("users"));

    PropExpression.Cmp<UUID> id();

    PropExpression.Str name();

    PropExpression.Cmp<OrganizationType> type();

    PropExpression.Str orgCode();

    PropExpression.Tp<OffsetDateTime> createdAt();

    Predicate licenses(Function<LicensesTableEx, Predicate> block);

    Predicate users(Function<UserTableEx, Predicate> block);
}
