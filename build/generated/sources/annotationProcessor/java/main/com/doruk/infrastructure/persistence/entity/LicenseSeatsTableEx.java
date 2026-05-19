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
        type = LicenseSeats.class
)
public class LicenseSeatsTableEx extends LicenseSeatsTable implements TableExProxy<LicenseSeats, LicenseSeatsTable> {
    public static final LicenseSeatsTableEx $ = new LicenseSeatsTableEx(LicenseSeatsTable.$, (String)null);

    public LicenseSeatsTableEx() {
        super();
    }

    public LicenseSeatsTableEx(AbstractTypedTable.DelayedOperation<LicenseSeats> delayedOperation) {
        super(delayedOperation);
    }

    public LicenseSeatsTableEx(TableImplementor<LicenseSeats> table) {
        super(table);
    }

    protected LicenseSeatsTableEx(LicenseSeatsTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected LicenseSeatsTableEx(LicenseSeatsTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    public LicensesTableEx licenses() {
        __beforeJoin();
        if (raw != null) {
            return new LicensesTableEx(raw.joinImplementor(LicenseSeatsProps.LICENSES.unwrap()));
        }
        return new LicensesTableEx(joinOperation(LicenseSeatsProps.LICENSES.unwrap()));
    }

    public LicensesTableEx licenses(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new LicensesTableEx(raw.joinImplementor(LicenseSeatsProps.LICENSES.unwrap(), joinType));
        }
        return new LicensesTableEx(joinOperation(LicenseSeatsProps.LICENSES.unwrap(), joinType));
    }

    public UserTableEx user() {
        __beforeJoin();
        if (raw != null) {
            return new UserTableEx(raw.joinImplementor(LicenseSeatsProps.USER.unwrap()));
        }
        return new UserTableEx(joinOperation(LicenseSeatsProps.USER.unwrap()));
    }

    public UserTableEx user(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new UserTableEx(raw.joinImplementor(LicenseSeatsProps.USER.unwrap(), joinType));
        }
        return new UserTableEx(joinOperation(LicenseSeatsProps.USER.unwrap(), joinType));
    }

    public UserTableEx assignedBy() {
        __beforeJoin();
        if (raw != null) {
            return new UserTableEx(raw.joinImplementor(LicenseSeatsProps.ASSIGNED_BY.unwrap()));
        }
        return new UserTableEx(joinOperation(LicenseSeatsProps.ASSIGNED_BY.unwrap()));
    }

    public UserTableEx assignedBy(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new UserTableEx(raw.joinImplementor(LicenseSeatsProps.ASSIGNED_BY.unwrap(), joinType));
        }
        return new UserTableEx(joinOperation(LicenseSeatsProps.ASSIGNED_BY.unwrap(), joinType));
    }

    @Override
    public LicenseSeatsTableEx asTableEx() {
        return this;
    }

    @Override
    public LicenseSeatsTableEx __disableJoin(String reason) {
        return new LicenseSeatsTableEx(this, reason);
    }

    @Override
    public LicenseSeatsTableEx __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new LicenseSeatsTableEx(this, baseTableOwner);
    }

    public <TT extends Table<?>, WJ extends WeakJoin<LicenseSeatsTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType) {
        return weakJoin(weakJoinType, JoinType.INNER);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>, WJ extends WeakJoin<LicenseSeatsTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType, JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(weakJoinType, joinType));
        }
        return (TT)TableProxies.fluent(joinOperation(weakJoinType, joinType));
    }

    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType,
            WeakJoin<LicenseSeatsTable, TT> weakJoinLambda) {
        return weakJoin(targetTableType, JoinType.INNER, weakJoinLambda);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType, JoinType joinType,
            WeakJoin<LicenseSeatsTable, TT> weakJoinLambda) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(targetTableType, joinType, weakJoinLambda));
        }
        return (TT)TableProxies.fluent(joinOperation(targetTableType, joinType, weakJoinLambda));
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable,
            WeakJoin<LicenseSeatsTable, TT> weakJoinLambda) {
        return weakJoin(targetBaseTable, JoinType.INNER, weakJoinLambda);
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable, JoinType joinType,
            WeakJoin<LicenseSeatsTable, TT> weakJoinLambda) {
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
