package com.doruk.infrastructure.persistence.entity;

import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.List;
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
        type = Session.class
)
@PropsFor(Session.class)
public interface SessionProps extends Props, Selection<Session> {
    TypedProp.Scalar<Session, Long> ID = 
        TypedProp.scalar(ImmutableType.get(Session.class).getProp("id"));

    TypedProp.Reference<Session, User> USER = 
        TypedProp.reference(ImmutableType.get(Session.class).getProp("user"));

    TypedProp.Scalar<Session, String> SESSION_ID = 
        TypedProp.scalar(ImmutableType.get(Session.class).getProp("sessionId"));

    TypedProp.Scalar<Session, String> DEVICE_INFO = 
        TypedProp.scalar(ImmutableType.get(Session.class).getProp("deviceInfo"));

    TypedProp.Scalar<Session, String> DEVICE_ID = 
        TypedProp.scalar(ImmutableType.get(Session.class).getProp("deviceId"));

    TypedProp.Scalar<Session, OffsetDateTime> EXPIRES_AT = 
        TypedProp.scalar(ImmutableType.get(Session.class).getProp("expiresAt"));

    TypedProp.Scalar<Session, OffsetDateTime> CREATED_AT = 
        TypedProp.scalar(ImmutableType.get(Session.class).getProp("createdAt"));

    TypedProp.ScalarList<Session, Integer> CACHED_PERMISSIONS = 
        TypedProp.scalarList(ImmutableType.get(Session.class).getProp("cachedPermissions"));

    PropExpression.Num<Long> id();

    UserTable user();

    UserTable user(JoinType joinType);

    PropExpression.Cmp<UUID> userId();

    PropExpression.Str sessionId();

    PropExpression.Str deviceInfo();

    PropExpression.Str deviceId();

    PropExpression.Tp<OffsetDateTime> expiresAt();

    PropExpression.Tp<OffsetDateTime> createdAt();

    PropExpression<List<Integer>> cachedPermissions();
}
