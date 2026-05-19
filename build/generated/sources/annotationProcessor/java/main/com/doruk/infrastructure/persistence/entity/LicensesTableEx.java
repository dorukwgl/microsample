package com.doruk.infrastructure.persistence.entity;

import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.function.Function;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.sql.JoinType;
import org.babyfish.jimmer.sql.ast.Predicate;
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
        type = Licenses.class
)
public class LicensesTableEx extends LicensesTable implements TableExProxy<Licenses, LicensesTable> {
    public static final LicensesTableEx $ = new LicensesTableEx(LicensesTable.$, (String)null);

    public LicensesTableEx() {
        super();
    }

    public LicensesTableEx(AbstractTypedTable.DelayedOperation<Licenses> delayedOperation) {
        super(delayedOperation);
    }

    public LicensesTableEx(TableImplementor<Licenses> table) {
        super(table);
    }

    protected LicensesTableEx(LicensesTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected LicensesTableEx(LicensesTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    public OrganizationsTableEx organizations() {
        __beforeJoin();
        if (raw != null) {
            return new OrganizationsTableEx(raw.joinImplementor(LicensesProps.ORGANIZATIONS.unwrap()));
        }
        return new OrganizationsTableEx(joinOperation(LicensesProps.ORGANIZATIONS.unwrap()));
    }

    public OrganizationsTableEx organizations(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new OrganizationsTableEx(raw.joinImplementor(LicensesProps.ORGANIZATIONS.unwrap(), joinType));
        }
        return new OrganizationsTableEx(joinOperation(LicensesProps.ORGANIZATIONS.unwrap(), joinType));
    }

    public LicenseAddonsTableEx addons() {
        __beforeJoin();
        if (raw != null) {
            return new LicenseAddonsTableEx(raw.joinImplementor(LicensesProps.ADDONS.unwrap()));
        }
        return new LicenseAddonsTableEx(joinOperation(LicensesProps.ADDONS.unwrap()));
    }

    public LicenseAddonsTableEx addons(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new LicenseAddonsTableEx(raw.joinImplementor(LicensesProps.ADDONS.unwrap(), joinType));
        }
        return new LicenseAddonsTableEx(joinOperation(LicensesProps.ADDONS.unwrap(), joinType));
    }

    @Override
    public Predicate addons(Function<LicenseAddonsTableEx, Predicate> block) {
        return exists(LicensesProps.ADDONS.unwrap(), block);
    }

    @Override
    public LicensesTableEx asTableEx() {
        return this;
    }

    @Override
    public LicensesTableEx __disableJoin(String reason) {
        return new LicensesTableEx(this, reason);
    }

    @Override
    public LicensesTableEx __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new LicensesTableEx(this, baseTableOwner);
    }

    public <TT extends Table<?>, WJ extends WeakJoin<LicensesTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType) {
        return weakJoin(weakJoinType, JoinType.INNER);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>, WJ extends WeakJoin<LicensesTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType, JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(weakJoinType, joinType));
        }
        return (TT)TableProxies.fluent(joinOperation(weakJoinType, joinType));
    }

    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType,
            WeakJoin<LicensesTable, TT> weakJoinLambda) {
        return weakJoin(targetTableType, JoinType.INNER, weakJoinLambda);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType, JoinType joinType,
            WeakJoin<LicensesTable, TT> weakJoinLambda) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(targetTableType, joinType, weakJoinLambda));
        }
        return (TT)TableProxies.fluent(joinOperation(targetTableType, joinType, weakJoinLambda));
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable,
            WeakJoin<LicensesTable, TT> weakJoinLambda) {
        return weakJoin(targetBaseTable, JoinType.INNER, weakJoinLambda);
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable, JoinType joinType,
            WeakJoin<LicensesTable, TT> weakJoinLambda) {
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
