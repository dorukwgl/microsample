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
        type = Permission.class
)
public class PermissionTable extends AbstractTypedTable<Permission> implements PermissionProps {
    public static final PermissionTable $ = new PermissionTable();

    public PermissionTable() {
        super(Permission.class);
    }

    public PermissionTable(AbstractTypedTable.DelayedOperation<Permission> delayedOperation) {
        super(Permission.class, delayedOperation);
    }

    public PermissionTable(TableImplementor<Permission> table) {
        super(table);
    }

    protected PermissionTable(PermissionTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected PermissionTable(PermissionTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    @Override
    public PropExpression.Str name() {
        return __get(PermissionProps.NAME.unwrap());
    }

    @Override
    public Predicate roles(Function<RoleTableEx, Predicate> block) {
        return exists(PermissionProps.ROLES.unwrap(), block);
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> deletedAt() {
        return __get(PermissionProps.DELETED_AT.unwrap());
    }

    @Override
    public PermissionTableEx asTableEx() {
        return new PermissionTableEx(this, (String)null);
    }

    @Override
    public PermissionTable __disableJoin(String reason) {
        return new PermissionTable(this, reason);
    }

    @Override
    public PermissionTable __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new PermissionTable(this, baseTableOwner);
    }

    @GeneratedBy(
            type = Permission.class
    )
    public static class Remote extends AbstractTypedTable<Permission> {
        public Remote(AbstractTypedTable.DelayedOperation delayedOperation) {
            super(Permission.class, delayedOperation);
        }

        public Remote(TableImplementor<Permission> table) {
            super(table);
        }

        public Remote(Remote base, BaseTableOwner baseTableOwner) {
            super(base, baseTableOwner);
        }

        public PropExpression.Str name() {
            return (org.babyfish.jimmer.sql.ast.PropExpression.Str)this.<String>get(PermissionProps.NAME.unwrap());
        }

        @Override
        @Deprecated
        public TableEx<Permission> asTableEx() {
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
