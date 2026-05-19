package com.doruk.infrastructure.persistence.entity;

import java.lang.Deprecated;
import java.lang.Override;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.function.Function;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.babyfish.jimmer.sql.ast.PropExpression;
import org.babyfish.jimmer.sql.ast.impl.base.BaseTableOwner;
import org.babyfish.jimmer.sql.ast.impl.table.TableImplementor;
import org.babyfish.jimmer.sql.ast.table.TableEx;
import org.babyfish.jimmer.sql.ast.table.spi.AbstractTypedTable;

@GeneratedBy(
        type = Role.class
)
public class RoleTable extends AbstractTypedTable<Role> implements RoleProps {
    public static final RoleTable $ = new RoleTable();

    public RoleTable() {
        super(Role.class);
    }

    public RoleTable(AbstractTypedTable.DelayedOperation<Role> delayedOperation) {
        super(Role.class, delayedOperation);
    }

    public RoleTable(TableImplementor<Role> table) {
        super(table);
    }

    protected RoleTable(RoleTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected RoleTable(RoleTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    @Override
    public PropExpression.Str name() {
        return __get(RoleProps.NAME.unwrap());
    }

    @Override
    public Predicate users(Function<UserTableEx, Predicate> block) {
        return exists(RoleProps.USERS.unwrap(), block);
    }

    @Override
    public Predicate permissions(Function<PermissionTableEx, Predicate> block) {
        return exists(RoleProps.PERMISSIONS.unwrap(), block);
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> deletedAt() {
        return __get(RoleProps.DELETED_AT.unwrap());
    }

    @Override
    public RoleTableEx asTableEx() {
        return new RoleTableEx(this, (String)null);
    }

    @Override
    public RoleTable __disableJoin(String reason) {
        return new RoleTable(this, reason);
    }

    @Override
    public RoleTable __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new RoleTable(this, baseTableOwner);
    }

    @GeneratedBy(
            type = Role.class
    )
    public static class Remote extends AbstractTypedTable<Role> {
        public Remote(AbstractTypedTable.DelayedOperation delayedOperation) {
            super(Role.class, delayedOperation);
        }

        public Remote(TableImplementor<Role> table) {
            super(table);
        }

        public Remote(Remote base, BaseTableOwner baseTableOwner) {
            super(base, baseTableOwner);
        }

        public PropExpression.Str name() {
            return (org.babyfish.jimmer.sql.ast.PropExpression.Str)this.<String>get(RoleProps.NAME.unwrap());
        }

        @Override
        @Deprecated
        public TableEx<Role> asTableEx() {
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
