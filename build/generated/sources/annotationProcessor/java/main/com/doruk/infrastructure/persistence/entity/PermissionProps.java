package com.doruk.infrastructure.persistence.entity;

import java.lang.String;
import java.time.OffsetDateTime;
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
        type = Permission.class
)
@PropsFor(Permission.class)
public interface PermissionProps extends Props, Selection<Permission> {
    TypedProp.Scalar<Permission, String> NAME = 
        TypedProp.scalar(ImmutableType.get(Permission.class).getProp("name"));

    TypedProp.ReferenceList<Permission, Role> ROLES = 
        TypedProp.referenceList(ImmutableType.get(Permission.class).getProp("roles"));

    TypedProp.Scalar<Permission, OffsetDateTime> DELETED_AT = 
        TypedProp.scalar(ImmutableType.get(Permission.class).getProp("deletedAt"));

    PropExpression.Str name();

    Predicate roles(Function<RoleTableEx, Predicate> block);

    PropExpression.Tp<OffsetDateTime> deletedAt();
}
