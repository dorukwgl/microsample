package com.doruk.infrastructure.persistence.entity;

import com.doruk.domain.shared.enums.LicenseStatus;
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.Long;
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
        type = LicenseAddons.class
)
public class LicenseAddonsTable extends AbstractTypedTable<LicenseAddons> implements LicenseAddonsProps {
    public static final LicenseAddonsTable $ = new LicenseAddonsTable();

    public LicenseAddonsTable() {
        super(LicenseAddons.class);
    }

    public LicenseAddonsTable(AbstractTypedTable.DelayedOperation<LicenseAddons> delayedOperation) {
        super(LicenseAddons.class, delayedOperation);
    }

    public LicenseAddonsTable(TableImplementor<LicenseAddons> table) {
        super(table);
    }

    protected LicenseAddonsTable(LicenseAddonsTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected LicenseAddonsTable(LicenseAddonsTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    @Override
    public PropExpression.Cmp<UUID> id() {
        return __get(LicenseAddonsProps.ID.unwrap());
    }

    @Override
    public PropExpression.Cmp<UUID> licenseId() {
        return __get(LicenseAddonsProps.LICENSE_ID.unwrap());
    }

    @Override
    public PropExpression.Cmp<UUID> skuId() {
        return __get(LicenseAddonsProps.SKU_ID.unwrap());
    }

    @Override
    public PropExpression.Num<Long> tierId() {
        return __get(LicenseAddonsProps.TIER_ID.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> validUntil() {
        return __get(LicenseAddonsProps.VALID_UNTIL.unwrap());
    }

    @Override
    public PropExpression.Cmp<LicenseStatus> status() {
        return __get(LicenseAddonsProps.STATUS.unwrap());
    }

    @Override
    public PropExpression.Str productName() {
        return __get(LicenseAddonsProps.PRODUCT_NAME.unwrap());
    }

    @Override
    public PropExpression.Str tierName() {
        return __get(LicenseAddonsProps.TIER_NAME.unwrap());
    }

    @Override
    public PropExpression.Str entitlements() {
        return __get(LicenseAddonsProps.ENTITLEMENTS.unwrap());
    }

    @Override
    public PropExpression.Num<Integer> totalSeats() {
        return __get(LicenseAddonsProps.TOTAL_SEATS.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> createdAt() {
        return __get(LicenseAddonsProps.CREATED_AT.unwrap());
    }

    @Override
    public LicensesTable licenses() {
        __beforeJoin();
        if (raw != null) {
            return new LicensesTable(raw.joinImplementor(LicenseAddonsProps.LICENSES.unwrap()));
        }
        return new LicensesTable(joinOperation(LicenseAddonsProps.LICENSES.unwrap()));
    }

    @Override
    public LicensesTable licenses(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new LicensesTable(raw.joinImplementor(LicenseAddonsProps.LICENSES.unwrap(), joinType));
        }
        return new LicensesTable(joinOperation(LicenseAddonsProps.LICENSES.unwrap(), joinType));
    }

    @Override
    public PropExpression.Cmp<UUID> licensesId() {
        return __getAssociatedId(LicenseAddonsProps.LICENSES.unwrap());
    }

    @Override
    public LicenseAddonsTableEx asTableEx() {
        return new LicenseAddonsTableEx(this, (String)null);
    }

    @Override
    public LicenseAddonsTable __disableJoin(String reason) {
        return new LicenseAddonsTable(this, reason);
    }

    @Override
    public LicenseAddonsTable __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new LicenseAddonsTable(this, baseTableOwner);
    }

    @GeneratedBy(
            type = LicenseAddons.class
    )
    public static class Remote extends AbstractTypedTable<LicenseAddons> {
        public Remote(AbstractTypedTable.DelayedOperation delayedOperation) {
            super(LicenseAddons.class, delayedOperation);
        }

        public Remote(TableImplementor<LicenseAddons> table) {
            super(table);
        }

        public Remote(Remote base, BaseTableOwner baseTableOwner) {
            super(base, baseTableOwner);
        }

        public PropExpression.Cmp<UUID> id() {
            return (org.babyfish.jimmer.sql.ast.PropExpression.Cmp<java.util.UUID>)this.<UUID>get(LicenseAddonsProps.ID.unwrap());
        }

        @Override
        @Deprecated
        public TableEx<LicenseAddons> asTableEx() {
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
