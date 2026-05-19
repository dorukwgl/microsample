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
        type = Role.class
)
@PropsFor(Role.class)
public interface RoleProps extends Props, Selection<Role> {
    TypedProp.Scalar<Role, String> NAME = 
        TypedProp.scalar(ImmutableType.get(Role.class).getProp("name"));

    TypedProp.ReferenceList<Role, User> USERS = 
        TypedProp.referenceList(ImmutableType.get(Role.class).getProp("users"));

    TypedProp.ReferenceList<Role, Permission> PERMISSIONS = 
        TypedProp.referenceList(ImmutableType.get(Role.class).getProp("permissions"));

    TypedProp.Scalar<Role, OffsetDateTime> DELETED_AT = 
        TypedProp.scalar(ImmutableType.get(Role.class).getProp("deletedAt"));

    PropExpression.Str name();

    Predicate users(Function<UserTableEx, Predicate> block);

    Predicate permissions(Function<PermissionTableEx, Predicate> block);

    PropExpression.Tp<OffsetDateTime> deletedAt();
}
