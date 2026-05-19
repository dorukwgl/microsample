package com.doruk.infrastructure.persistence.entity;

import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.sql.JoinType;
import org.babyfish.jimmer.sql.ast.PropExpression;
import org.babyfish.jimmer.sql.ast.impl.base.BaseTableOwner;
import org.babyfish.jimmer.sql.ast.impl.table.TableImplementor;
import org.babyfish.jimmer.sql.ast.table.TableEx;
import org.babyfish.jimmer.sql.ast.table.spi.AbstractTypedTable;

@GeneratedBy(
        type = Session.class
)
public class SessionTable extends AbstractTypedTable<Session> implements SessionProps {
    public static final SessionTable $ = new SessionTable();

    public SessionTable() {
        super(Session.class);
    }

    public SessionTable(AbstractTypedTable.DelayedOperation<Session> delayedOperation) {
        super(Session.class, delayedOperation);
    }

    public SessionTable(TableImplementor<Session> table) {
        super(table);
    }

    protected SessionTable(SessionTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected SessionTable(SessionTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    @Override
    public PropExpression.Num<Long> id() {
        return __get(SessionProps.ID.unwrap());
    }

    @Override
    public UserTable user() {
        __beforeJoin();
        if (raw != null) {
            return new UserTable(raw.joinImplementor(SessionProps.USER.unwrap()));
        }
        return new UserTable(joinOperation(SessionProps.USER.unwrap()));
    }

    @Override
    public UserTable user(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new UserTable(raw.joinImplementor(SessionProps.USER.unwrap(), joinType));
        }
        return new UserTable(joinOperation(SessionProps.USER.unwrap(), joinType));
    }

    @Override
    public PropExpression.Cmp<UUID> userId() {
        return __getAssociatedId(SessionProps.USER.unwrap());
    }

    @Override
    public PropExpression.Str sessionId() {
        return __get(SessionProps.SESSION_ID.unwrap());
    }

    @Override
    public PropExpression.Str deviceInfo() {
        return __get(SessionProps.DEVICE_INFO.unwrap());
    }

    @Override
    public PropExpression.Str deviceId() {
        return __get(SessionProps.DEVICE_ID.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> expiresAt() {
        return __get(SessionProps.EXPIRES_AT.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> createdAt() {
        return __get(SessionProps.CREATED_AT.unwrap());
    }

    @Override
    public PropExpression<List<Integer>> cachedPermissions() {
        return __get(SessionProps.CACHED_PERMISSIONS.unwrap());
    }

    @Override
    public SessionTableEx asTableEx() {
        return new SessionTableEx(this, (String)null);
    }

    @Override
    public SessionTable __disableJoin(String reason) {
        return new SessionTable(this, reason);
    }

    @Override
    public SessionTable __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new SessionTable(this, baseTableOwner);
    }

    @GeneratedBy(
            type = Session.class
    )
    public static class Remote extends AbstractTypedTable<Session> {
        public Remote(AbstractTypedTable.DelayedOperation delayedOperation) {
            super(Session.class, delayedOperation);
        }

        public Remote(TableImplementor<Session> table) {
            super(table);
        }

        public Remote(Remote base, BaseTableOwner baseTableOwner) {
            super(base, baseTableOwner);
        }

        public PropExpression.Num<Long> id() {
            return (org.babyfish.jimmer.sql.ast.PropExpression.Num<java.lang.Long>)this.<Long>get(SessionProps.ID.unwrap());
        }

        @Override
        @Deprecated
        public TableEx<Session> asTableEx() {
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
