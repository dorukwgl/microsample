package com.doruk.infrastructure.persistence.entity;

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
        type = Biometric.class
)
@PropsFor(Biometric.class)
public interface BiometricProps extends Props, Selection<Biometric> {
    TypedProp.Scalar<Biometric, UUID> ID = 
        TypedProp.scalar(ImmutableType.get(Biometric.class).getProp("id"));

    TypedProp.Reference<Biometric, User> USER = 
        TypedProp.reference(ImmutableType.get(Biometric.class).getProp("user"));

    TypedProp.Scalar<Biometric, byte[]> PUBLIC_KEY = 
        TypedProp.scalar(ImmutableType.get(Biometric.class).getProp("publicKey"));

    TypedProp.Scalar<Biometric, String> DEVICE_ID = 
        TypedProp.scalar(ImmutableType.get(Biometric.class).getProp("deviceId"));

    TypedProp.Scalar<Biometric, OffsetDateTime> LAST_USED_AT = 
        TypedProp.scalar(ImmutableType.get(Biometric.class).getProp("lastUsedAt"));

    PropExpression.Cmp<UUID> id();

    UserTable user();

    UserTable user(JoinType joinType);

    PropExpression.Cmp<UUID> userId();

    PropExpression<byte[]> publicKey();

    PropExpression.Str deviceId();

    PropExpression.Tp<OffsetDateTime> lastUsedAt();
}
