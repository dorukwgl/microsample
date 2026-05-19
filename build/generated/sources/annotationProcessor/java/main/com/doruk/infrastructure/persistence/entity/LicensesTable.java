package com.doruk.infrastructure.persistence.entity;

import com.doruk.domain.shared.enums.LicenseStatus;
import com.doruk.domain.shared.enums.LicenseType;
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.function.Function;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.sql.JoinType;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.babyfish.jimmer.sql.ast.PropExpression;
import org.babyfish.jimmer.sql.ast.impl.base.BaseTableOwner;
import org.babyfish.jimmer.sql.ast.impl.table.TableImplementor;
import org.babyfish.jimmer.sql.ast.table.TableEx;
import org.babyfish.jimmer.sql.ast.table.spi.AbstractTypedTable;

@GeneratedBy(
        type = Licenses.class
)
public class LicensesTable extends AbstractTypedTable<Licenses> implements LicensesProps {
    public static final LicensesTable $ = new LicensesTable();

    public LicensesTable() {
        super(Licenses.class);
    }

    public LicensesTable(AbstractTypedTable.DelayedOperation<Licenses> delayedOperation) {
        super(Licenses.class, delayedOperation);
    }

    public LicensesTable(TableImplementor<Licenses> table) {
        super(table);
    }

    protected LicensesTable(LicensesTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected LicensesTable(LicensesTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    @Override
    public PropExpression.Cmp<UUID> id() {
        return __get(LicensesProps.ID.unwrap());
    }

    @Override
    public PropExpression.Str licenseKey() {
        return __get(LicensesProps.LICENSE_KEY.unwrap());
    }

    @Override
    public PropExpression.Cmp<UUID> organizationId() {
        return __get(LicensesProps.ORGANIZATION_ID.unwrap());
    }

    @Override
    public PropExpression.Cmp<UUID> skuId() {
        return __get(LicensesProps.SKU_ID.unwrap());
    }

    @Override
    public PropExpression.Num<Long> tierId() {
        return __get(LicensesProps.TIER_ID.unwrap());
    }

    @Override
    public PropExpression.Cmp<LicenseType> type() {
        return __get(LicensesProps.TYPE.unwrap());
    }

    @Override
    public PropExpression.Cmp<LicenseStatus> status() {
        return __get(LicensesProps.STATUS.unwrap());
    }

    @Override
    public PropExpression.Str productName() {
        return __get(LicensesProps.PRODUCT_NAME.unwrap());
    }

    @Override
    public PropExpression.Str tierName() {
        return __get(LicensesProps.TIER_NAME.unwrap());
    }

    @Override
    public PropExpression.Num<Integer> totalSeats() {
        return __get(LicensesProps.TOTAL_SEATS.unwrap());
    }

    @Override
    public PropExpression.Num<Integer> assignedSeats() {
        return __get(LicensesProps.ASSIGNED_SEATS.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> validFrom() {
        return __get(LicensesProps.VALID_FROM.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> validUntil() {
        return __get(LicensesProps.VALID_UNTIL.unwrap());
    }

    @Override
    public PropExpression.Str entitlements() {
        return __get(LicensesProps.ENTITLEMENTS.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> createdAt() {
        return __get(LicensesProps.CREATED_AT.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> updatedAt() {
        return __get(LicensesProps.UPDATED_AT.unwrap());
    }

    @Override
    public OrganizationsTable organizations() {
        __beforeJoin();
        if (raw != null) {
            return new OrganizationsTable(raw.joinImplementor(LicensesProps.ORGANIZATIONS.unwrap()));
        }
        return new OrganizationsTable(joinOperation(LicensesProps.ORGANIZATIONS.unwrap()));
    }

    @Override
    public OrganizationsTable organizations(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new OrganizationsTable(raw.joinImplementor(LicensesProps.ORGANIZATIONS.unwrap(), joinType));
        }
        return new OrganizationsTable(joinOperation(LicensesProps.ORGANIZATIONS.unwrap(), joinType));
    }

    @Override
    public PropExpression.Cmp<UUID> organizationsId() {
        return __getAssociatedId(LicensesProps.ORGANIZATIONS.unwrap());
    }

    @Override
    public Predicate addons(Function<LicenseAddonsTableEx, Predicate> block) {
        return exists(LicensesProps.ADDONS.unwrap(), block);
    }

    @Override
    public LicensesTableEx asTableEx() {
        return new LicensesTableEx(this, (String)null);
    }

    @Override
    public LicensesTable __disableJoin(String reason) {
        return new LicensesTable(this, reason);
    }

    @Override
    public LicensesTable __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new LicensesTable(this, baseTableOwner);
    }

    @GeneratedBy(
            type = Licenses.class
    )
    public static class Remote extends AbstractTypedTable<Licenses> {
        public Remote(AbstractTypedTable.DelayedOperation delayedOperation) {
            super(Licenses.class, delayedOperation);
        }

        public Remote(TableImplementor<Licenses> table) {
            super(table);
        }

        public Remote(Remote base, BaseTableOwner baseTableOwner) {
            super(base, baseTableOwner);
        }

        public PropExpression.Cmp<UUID> id() {
            return (org.babyfish.jimmer.sql.ast.PropExpression.Cmp<java.util.UUID>)this.<UUID>get(LicensesProps.ID.unwrap());
        }

        @Override
        @Deprecated
        public TableEx<Licenses> asTableEx() {
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
