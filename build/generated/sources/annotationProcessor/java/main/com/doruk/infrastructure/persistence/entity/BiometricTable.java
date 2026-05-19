package com.doruk.infrastructure.persistence.entity;

import java.lang.Deprecated;
import java.lang.Override;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.sql.JoinType;
import org.babyfish.jimmer.sql.ast.PropExpression;
import org.babyfish.jimmer.sql.ast.impl.base.BaseTableOwner;
import org.babyfish.jimmer.sql.ast.impl.table.TableImplementor;
import org.babyfish.jimmer.sql.ast.table.TableEx;
import org.babyfish.jimmer.sql.ast.table.spi.AbstractTypedTable;

@GeneratedBy(
        type = Biometric.class
)
public class BiometricTable extends AbstractTypedTable<Biometric> implements BiometricProps {
    public static final BiometricTable $ = new BiometricTable();

    public BiometricTable() {
        super(Biometric.class);
    }

    public BiometricTable(AbstractTypedTable.DelayedOperation<Biometric> delayedOperation) {
        super(Biometric.class, delayedOperation);
    }

    public BiometricTable(TableImplementor<Biometric> table) {
        super(table);
    }

    protected BiometricTable(BiometricTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected BiometricTable(BiometricTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    @Override
    public PropExpression.Cmp<UUID> id() {
        return __get(BiometricProps.ID.unwrap());
    }

    @Override
    public UserTable user() {
        __beforeJoin();
        if (raw != null) {
            return new UserTable(raw.joinImplementor(BiometricProps.USER.unwrap()));
        }
        return new UserTable(joinOperation(BiometricProps.USER.unwrap()));
    }

    @Override
    public UserTable user(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new UserTable(raw.joinImplementor(BiometricProps.USER.unwrap(), joinType));
        }
        return new UserTable(joinOperation(BiometricProps.USER.unwrap(), joinType));
    }

    @Override
    public PropExpression.Cmp<UUID> userId() {
        return __getAssociatedId(BiometricProps.USER.unwrap());
    }

    @Override
    public PropExpression<byte[]> publicKey() {
        return __get(BiometricProps.PUBLIC_KEY.unwrap());
    }

    @Override
    public PropExpression.Str deviceId() {
        return __get(BiometricProps.DEVICE_ID.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> lastUsedAt() {
        return __get(BiometricProps.LAST_USED_AT.unwrap());
    }

    @Override
    public BiometricTableEx asTableEx() {
        return new BiometricTableEx(this, (String)null);
    }

    @Override
    public BiometricTable __disableJoin(String reason) {
        return new BiometricTable(this, reason);
    }

    @Override
    public BiometricTable __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new BiometricTable(this, baseTableOwner);
    }

    @GeneratedBy(
            type = Biometric.class
    )
    public static class Remote extends AbstractTypedTable<Biometric> {
        public Remote(AbstractTypedTable.DelayedOperation delayedOperation) {
            super(Biometric.class, delayedOperation);
        }

        public Remote(TableImplementor<Biometric> table) {
            super(table);
        }

        public Remote(Remote base, BaseTableOwner baseTableOwner) {
            super(base, baseTableOwner);
        }

        public PropExpression.Cmp<UUID> id() {
            return (org.babyfish.jimmer.sql.ast.PropExpression.Cmp<java.util.UUID>)this.<UUID>get(BiometricProps.ID.unwrap());
        }

        @Override
        @Deprecated
        public TableEx<Biometric> asTableEx() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Remote __disableJoin(String reason) {
            return this;
        }

        @Override
        public Remote __baseTableOwner(BaseTableOwner baseTableOwner) {
            return new Remote(this, baseTableOwner);
        }
    }
}
