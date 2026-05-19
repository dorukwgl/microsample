package com.doruk.infrastructure.persistence.entity;

import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.sql.JoinType;
import org.babyfish.jimmer.sql.ast.impl.base.BaseTableOwner;
import org.babyfish.jimmer.sql.ast.impl.base.BaseTableSymbol;
import org.babyfish.jimmer.sql.ast.impl.base.BaseTableSymbols;
import org.babyfish.jimmer.sql.ast.impl.table.JWeakJoinLambdaFactory;
import org.babyfish.jimmer.sql.ast.impl.table.TableImplementor;
import org.babyfish.jimmer.sql.ast.impl.table.TableProxies;
import org.babyfish.jimmer.sql.ast.impl.table.WeakJoinHandle;
import org.babyfish.jimmer.sql.ast.impl.table.WeakJoinLambda;
import org.babyfish.jimmer.sql.ast.table.BaseTable;
import org.babyfish.jimmer.sql.ast.table.Table;
import org.babyfish.jimmer.sql.ast.table.WeakJoin;
import org.babyfish.jimmer.sql.ast.table.spi.AbstractTypedTable;
import org.babyfish.jimmer.sql.ast.table.spi.TableExProxy;
import org.babyfish.jimmer.sql.ast.table.spi.TableLike;

@GeneratedBy(
        type = LicenseAddons.class
)
public class LicenseAddonsTableEx extends LicenseAddonsTable implements TableExProxy<LicenseAddons, LicenseAddonsTable> {
    public static final LicenseAddonsTableEx $ = new LicenseAddonsTableEx(LicenseAddonsTable.$, (String)null);

    public LicenseAddonsTableEx() {
        super();
    }

    public LicenseAddonsTableEx(
            AbstractTypedTable.DelayedOperation<LicenseAddons> delayedOperation) {
        super(delayedOperation);
    }

    public LicenseAddonsTableEx(TableImplementor<LicenseAddons> table) {
        super(table);
    }

    protected LicenseAddonsTableEx(LicenseAddonsTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected LicenseAddonsTableEx(LicenseAddonsTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    public LicensesTableEx licenses() {
        __beforeJoin();
        if (raw != null) {
            return new LicensesTableEx(raw.joinImplementor(LicenseAddonsProps.LICENSES.unwrap()));
        }
        return new LicensesTableEx(joinOperation(LicenseAddonsProps.LICENSES.unwrap()));
    }

    public LicensesTableEx licenses(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new LicensesTableEx(raw.joinImplementor(LicenseAddonsProps.LICENSES.unwrap(), joinType));
        }
        return new LicensesTableEx(joinOperation(LicenseAddonsProps.LICENSES.unwrap(), joinType));
    }

    @Override
    public LicenseAddonsTableEx asTableEx() {
        return this;
    }

    @Override
    public LicenseAddonsTableEx __disableJoin(String reason) {
        return new LicenseAddonsTableEx(this, reason);
    }

    @Override
    public LicenseAddonsTableEx __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new LicenseAddonsTableEx(this, baseTableOwner);
    }

    public <TT extends Table<?>, WJ extends WeakJoin<LicenseAddonsTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType) {
        return weakJoin(weakJoinType, JoinType.INNER);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>, WJ extends WeakJoin<LicenseAddonsTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType, JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(weakJoinType, joinType));
        }
        return (TT)TableProxies.fluent(joinOperation(weakJoinType, joinType));
    }

    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType,
            WeakJoin<LicenseAddonsTable, TT> weakJoinLambda) {
        return weakJoin(targetTableType, JoinType.INNER, weakJoinLambda);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType, JoinType joinType,
            WeakJoin<LicenseAddonsTable, TT> weakJoinLambda) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(targetTableType, joinType, weakJoinLambda));
        }
        return (TT)TableProxies.fluent(joinOperation(targetTableType, joinType, weakJoinLambda));
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable,
            WeakJoin<LicenseAddonsTable, TT> weakJoinLambda) {
        return weakJoin(targetBaseTable, JoinType.INNER, weakJoinLambda);
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable, JoinType joinType,
            WeakJoin<LicenseAddonsTable, TT> weakJoinLambda) {
        WeakJoinLambda lambda = JWeakJoinLambdaFactory.get(weakJoinLambda);
        WeakJoinHandle handle = WeakJoinHandle.of(
            lambda,
            true,
            true,
            (WeakJoin<TableLike<?>, TableLike<?>>)(WeakJoin<?, ?>) weakJoinLambda
        );
        return (TT) BaseTableSymbols.of((BaseTableSymbol) targetBaseTable, this, handle, joinType);
    }
}
