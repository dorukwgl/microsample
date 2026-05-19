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
        type = Organizations.class
)
public class OrganizationsTableEx extends OrganizationsTable implements TableExProxy<Organizations, OrganizationsTable> {
    public static final OrganizationsTableEx $ = new OrganizationsTableEx(OrganizationsTable.$, (String)null);

    public OrganizationsTableEx() {
        super();
    }

    public OrganizationsTableEx(
            AbstractTypedTable.DelayedOperation<Organizations> delayedOperation) {
        super(delayedOperation);
    }

    public OrganizationsTableEx(TableImplementor<Organizations> table) {
        super(table);
    }

    protected OrganizationsTableEx(OrganizationsTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected OrganizationsTableEx(OrganizationsTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    public LicensesTableEx licenses() {
        __beforeJoin();
        if (raw != null) {
            return new LicensesTableEx(raw.joinImplementor(OrganizationsProps.LICENSES.unwrap()));
        }
        return new LicensesTableEx(joinOperation(OrganizationsProps.LICENSES.unwrap()));
    }

    public LicensesTableEx licenses(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new LicensesTableEx(raw.joinImplementor(OrganizationsProps.LICENSES.unwrap(), joinType));
        }
        return new LicensesTableEx(joinOperation(OrganizationsProps.LICENSES.unwrap(), joinType));
    }

    @Override
    public Predicate licenses(Function<LicensesTableEx, Predicate> block) {
        return exists(OrganizationsProps.LICENSES.unwrap(), block);
    }

    public UserTableEx users() {
        __beforeJoin();
        if (raw != null) {
            return new UserTableEx(raw.joinImplementor(OrganizationsProps.USERS.unwrap()));
        }
        return new UserTableEx(joinOperation(OrganizationsProps.USERS.unwrap()));
    }

    public UserTableEx users(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new UserTableEx(raw.joinImplementor(OrganizationsProps.USERS.unwrap(), joinType));
        }
        return new UserTableEx(joinOperation(OrganizationsProps.USERS.unwrap(), joinType));
    }

    @Override
    public Predicate users(Function<UserTableEx, Predicate> block) {
        return exists(OrganizationsProps.USERS.unwrap(), block);
    }

    @Override
    public OrganizationsTableEx asTableEx() {
        return this;
    }

    @Override
    public OrganizationsTableEx __disableJoin(String reason) {
        return new OrganizationsTableEx(this, reason);
    }

    @Override
    public OrganizationsTableEx __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new OrganizationsTableEx(this, baseTableOwner);
    }

    public <TT extends Table<?>, WJ extends WeakJoin<OrganizationsTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType) {
        return weakJoin(weakJoinType, JoinType.INNER);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>, WJ extends WeakJoin<OrganizationsTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType, JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(weakJoinType, joinType));
        }
        return (TT)TableProxies.fluent(joinOperation(weakJoinType, joinType));
    }

    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType,
            WeakJoin<OrganizationsTable, TT> weakJoinLambda) {
        return weakJoin(targetTableType, JoinType.INNER, weakJoinLambda);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType, JoinType joinType,
            WeakJoin<OrganizationsTable, TT> weakJoinLambda) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(targetTableType, joinType, weakJoinLambda));
        }
        return (TT)TableProxies.fluent(joinOperation(targetTableType, joinType, weakJoinLambda));
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable,
            WeakJoin<OrganizationsTable, TT> weakJoinLambda) {
        return weakJoin(targetBaseTable, JoinType.INNER, weakJoinLambda);
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable, JoinType joinType,
            WeakJoin<OrganizationsTable, TT> weakJoinLambda) {
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
