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
        type = Permission.class
)
public class PermissionTableEx extends PermissionTable implements TableExProxy<Permission, PermissionTable> {
    public static final PermissionTableEx $ = new PermissionTableEx(PermissionTable.$, (String)null);

    public PermissionTableEx() {
        super();
    }

    public PermissionTableEx(AbstractTypedTable.DelayedOperation<Permission> delayedOperation) {
        super(delayedOperation);
    }

    public PermissionTableEx(TableImplementor<Permission> table) {
        super(table);
    }

    protected PermissionTableEx(PermissionTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected PermissionTableEx(PermissionTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    public RoleTableEx roles() {
        __beforeJoin();
        if (raw != null) {
            return new RoleTableEx(raw.joinImplementor(PermissionProps.ROLES.unwrap()));
        }
        return new RoleTableEx(joinOperation(PermissionProps.ROLES.unwrap()));
    }

    public RoleTableEx roles(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new RoleTableEx(raw.joinImplementor(PermissionProps.ROLES.unwrap(), joinType));
        }
        return new RoleTableEx(joinOperation(PermissionProps.ROLES.unwrap(), joinType));
    }

    @Override
    public Predicate roles(Function<RoleTableEx, Predicate> block) {
        return exists(PermissionProps.ROLES.unwrap(), block);
    }

    @Override
    public PermissionTableEx asTableEx() {
        return this;
    }

    @Override
    public PermissionTableEx __disableJoin(String reason) {
        return new PermissionTableEx(this, reason);
    }

    @Override
    public PermissionTableEx __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new PermissionTableEx(this, baseTableOwner);
    }

    public <TT extends Table<?>, WJ extends WeakJoin<PermissionTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType) {
        return weakJoin(weakJoinType, JoinType.INNER);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>, WJ extends WeakJoin<PermissionTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType, JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(weakJoinType, joinType));
        }
        return (TT)TableProxies.fluent(joinOperation(weakJoinType, joinType));
    }

    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType,
            WeakJoin<PermissionTable, TT> weakJoinLambda) {
        return weakJoin(targetTableType, JoinType.INNER, weakJoinLambda);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType, JoinType joinType,
            WeakJoin<PermissionTable, TT> weakJoinLambda) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(targetTableType, joinType, weakJoinLambda));
        }
        return (TT)TableProxies.fluent(joinOperation(targetTableType, joinType, weakJoinLambda));
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable,
            WeakJoin<PermissionTable, TT> weakJoinLambda) {
        return weakJoin(targetBaseTable, JoinType.INNER, weakJoinLambda);
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable, JoinType joinType,
            WeakJoin<PermissionTable, TT> weakJoinLambda) {
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
