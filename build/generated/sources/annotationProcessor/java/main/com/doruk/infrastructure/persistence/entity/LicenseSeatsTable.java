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
        type = LicenseSeats.class
)
public class LicenseSeatsTable extends AbstractTypedTable<LicenseSeats> implements LicenseSeatsProps {
    public static final LicenseSeatsTable $ = new LicenseSeatsTable();

    public LicenseSeatsTable() {
        super(LicenseSeats.class);
    }

    public LicenseSeatsTable(AbstractTypedTable.DelayedOperation<LicenseSeats> delayedOperation) {
        super(LicenseSeats.class, delayedOperation);
    }

    public LicenseSeatsTable(TableImplementor<LicenseSeats> table) {
        super(table);
    }

    protected LicenseSeatsTable(LicenseSeatsTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected LicenseSeatsTable(LicenseSeatsTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    @Override
    public PropExpression.Cmp<UUID> id() {
        return __get(LicenseSeatsProps.ID.unwrap());
    }

    @Override
    public LicensesTable licenses() {
        __beforeJoin();
        if (raw != null) {
            return new LicensesTable(raw.joinImplementor(LicenseSeatsProps.LICENSES.unwrap()));
        }
        return new LicensesTable(joinOperation(LicenseSeatsProps.LICENSES.unwrap()));
    }

    @Override
    public LicensesTable licenses(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new LicensesTable(raw.joinImplementor(LicenseSeatsProps.LICENSES.unwrap(), joinType));
        }
        return new LicensesTable(joinOperation(LicenseSeatsProps.LICENSES.unwrap(), joinType));
    }

    @Override
    public PropExpression.Cmp<UUID> licenseId() {
        return __getAssociatedId(LicenseSeatsProps.LICENSES.unwrap());
    }

    @Override
    public UserTable user() {
        __beforeJoin();
        if (raw != null) {
            return new UserTable(raw.joinImplementor(LicenseSeatsProps.USER.unwrap()));
        }
        return new UserTable(joinOperation(LicenseSeatsProps.USER.unwrap()));
    }

    @Override
    public UserTable user(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new UserTable(raw.joinImplementor(LicenseSeatsProps.USER.unwrap(), joinType));
        }
        return new UserTable(joinOperation(LicenseSeatsProps.USER.unwrap(), joinType));
    }

    @Override
    public PropExpression.Cmp<UUID> userId() {
        return __getAssociatedId(LicenseSeatsProps.USER.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> assignedAt() {
        return __get(LicenseSeatsProps.ASSIGNED_AT.unwrap());
    }

    @Override
    public UserTable assignedBy() {
        __beforeJoin();
        if (raw != null) {
            return new UserTable(raw.joinImplementor(LicenseSeatsProps.ASSIGNED_BY.unwrap()));
        }
        return new UserTable(joinOperation(LicenseSeatsProps.ASSIGNED_BY.unwrap()));
    }

    @Override
    public UserTable assignedBy(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new UserTable(raw.joinImplementor(LicenseSeatsProps.ASSIGNED_BY.unwrap(), joinType));
        }
        return new UserTable(joinOperation(LicenseSeatsProps.ASSIGNED_BY.unwrap(), joinType));
    }

    @Override
    public PropExpression.Cmp<UUID> assignedById() {
        return __getAssociatedId(LicenseSeatsProps.ASSIGNED_BY.unwrap());
    }

    @Override
    public LicenseSeatsTableEx asTableEx() {
        return new LicenseSeatsTableEx(this, (String)null);
    }

    @Override
    public LicenseSeatsTable __disableJoin(String reason) {
        return new LicenseSeatsTable(this, reason);
    }

    @Override
    public LicenseSeatsTable __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new LicenseSeatsTable(this, baseTableOwner);
    }

    @GeneratedBy(
            type = LicenseSeats.class
    )
    public static class Remote extends AbstractTypedTable<LicenseSeats> {
        public Remote(AbstractTypedTable.DelayedOperation delayedOperation) {
            super(LicenseSeats.class, delayedOperation);
        }

        public Remote(TableImplementor<LicenseSeats> table) {
            super(table);
        }

        public Remote(Remote base, BaseTableOwner baseTableOwner) {
            super(base, baseTableOwner);
        }

        public PropExpression.Cmp<UUID> id() {
            return (org.babyfish.jimmer.sql.ast.PropExpression.Cmp<java.util.UUID>)this.<UUID>get(LicenseSeatsProps.ID.unwrap());
        }

        @Override
        @Deprecated
        public TableEx<LicenseSeats> asTableEx() {
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
