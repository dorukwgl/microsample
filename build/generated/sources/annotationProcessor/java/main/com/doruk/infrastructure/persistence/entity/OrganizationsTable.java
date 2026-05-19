package com.doruk.infrastructure.persistence.entity;

import com.doruk.domain.shared.enums.OrganizationType;
import java.lang.Deprecated;
import java.lang.Override;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.function.Function;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.babyfish.jimmer.sql.ast.PropExpression;
import org.babyfish.jimmer.sql.ast.impl.base.BaseTableOwner;
import org.babyfish.jimmer.sql.ast.impl.table.TableImplementor;
import org.babyfish.jimmer.sql.ast.table.TableEx;
import org.babyfish.jimmer.sql.ast.table.spi.AbstractTypedTable;

@GeneratedBy(
        type = Organizations.class
)
public class OrganizationsTable extends AbstractTypedTable<Organizations> implements OrganizationsProps {
    public static final OrganizationsTable $ = new OrganizationsTable();

    public OrganizationsTable() {
        super(Organizations.class);
    }

    public OrganizationsTable(AbstractTypedTable.DelayedOperation<Organizations> delayedOperation) {
        super(Organizations.class, delayedOperation);
    }

    public OrganizationsTable(TableImplementor<Organizations> table) {
        super(table);
    }

    protected OrganizationsTable(OrganizationsTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected OrganizationsTable(OrganizationsTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    @Override
    public PropExpression.Cmp<UUID> id() {
        return __get(OrganizationsProps.ID.unwrap());
    }

    @Override
    public PropExpression.Str name() {
        return __get(OrganizationsProps.NAME.unwrap());
    }

    @Override
    public PropExpression.Cmp<OrganizationType> type() {
        return __get(OrganizationsProps.TYPE.unwrap());
    }

    @Override
    public PropExpression.Str orgCode() {
        return __get(OrganizationsProps.ORG_CODE.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> createdAt() {
        return __get(OrganizationsProps.CREATED_AT.unwrap());
    }

    @Override
    public Predicate licenses(Function<LicensesTableEx, Predicate> block) {
        return exists(OrganizationsProps.LICENSES.unwrap(), block);
    }

    @Override
    public Predicate users(Function<UserTableEx, Predicate> block) {
        return exists(OrganizationsProps.USERS.unwrap(), block);
    }

    @Override
    public OrganizationsTableEx asTableEx() {
        return new OrganizationsTableEx(this, (String)null);
    }

    @Override
    public OrganizationsTable __disableJoin(String reason) {
        return new OrganizationsTable(this, reason);
    }

    @Override
    public OrganizationsTable __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new OrganizationsTable(this, baseTableOwner);
    }

    @GeneratedBy(
            type = Organizations.class
    )
    public static class Remote extends AbstractTypedTable<Organizations> {
        public Remote(AbstractTypedTable.DelayedOperation delayedOperation) {
            super(Organizations.class, delayedOperation);
        }

        public Remote(TableImplementor<Organizations> table) {
            super(table);
        }

        public Remote(Remote base, BaseTableOwner baseTableOwner) {
            super(base, baseTableOwner);
        }

        public PropExpression.Cmp<UUID> id() {
            return (org.babyfish.jimmer.sql.ast.PropExpression.Cmp<java.util.UUID>)this.<UUID>get(OrganizationsProps.ID.unwrap());
        }

        @Override
        @Deprecated
        public TableEx<Organizations> asTableEx() {
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
