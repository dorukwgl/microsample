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
        type = Role.class
)
public class RoleTableEx extends RoleTable implements TableExProxy<Role, RoleTable> {
    public static final RoleTableEx $ = new RoleTableEx(RoleTable.$, (String)null);

    public RoleTableEx() {
        super();
    }

    public RoleTableEx(AbstractTypedTable.DelayedOperation<Role> delayedOperation) {
        super(delayedOperation);
    }

    public RoleTableEx(TableImplementor<Role> table) {
        super(table);
    }

    protected RoleTableEx(RoleTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected RoleTableEx(RoleTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    public UserTableEx users() {
        __beforeJoin();
        if (raw != null) {
            return new UserTableEx(raw.joinImplementor(RoleProps.USERS.unwrap()));
        }
        return new UserTableEx(joinOperation(RoleProps.USERS.unwrap()));
    }

    public UserTableEx users(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new UserTableEx(raw.joinImplementor(RoleProps.USERS.unwrap(), joinType));
        }
        return new UserTableEx(joinOperation(RoleProps.USERS.unwrap(), joinType));
    }

    @Override
    public Predicate users(Function<UserTableEx, Predicate> block) {
        return exists(RoleProps.USERS.unwrap(), block);
    }

    public PermissionTableEx permissions() {
        __beforeJoin();
        if (raw != null) {
            return new PermissionTableEx(raw.joinImplementor(RoleProps.PERMISSIONS.unwrap()));
        }
        return new PermissionTableEx(joinOperation(RoleProps.PERMISSIONS.unwrap()));
    }

    public PermissionTableEx permissions(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new PermissionTableEx(raw.joinImplementor(RoleProps.PERMISSIONS.unwrap(), joinType));
        }
        return new PermissionTableEx(joinOperation(RoleProps.PERMISSIONS.unwrap(), joinType));
    }

    @Override
    public Predicate permissions(Function<PermissionTableEx, Predicate> block) {
        return exists(RoleProps.PERMISSIONS.unwrap(), block);
    }

    @Override
    public RoleTableEx asTableEx() {
        return this;
    }

    @Override
    public RoleTableEx __disableJoin(String reason) {
        return new RoleTableEx(this, reason);
    }

    @Override
    public RoleTableEx __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new RoleTableEx(this, baseTableOwner);
    }

    public <TT extends Table<?>, WJ extends WeakJoin<RoleTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType) {
        return weakJoin(weakJoinType, JoinType.INNER);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>, WJ extends WeakJoin<RoleTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType, JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(weakJoinType, joinType));
        }
        return (TT)TableProxies.fluent(joinOperation(weakJoinType, joinType));
    }

    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType,
            WeakJoin<RoleTable, TT> weakJoinLambda) {
        return weakJoin(targetTableType, JoinType.INNER, weakJoinLambda);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType, JoinType joinType,
            WeakJoin<RoleTable, TT> weakJoinLambda) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(targetTableType, joinType, weakJoinLambda));
        }
        return (TT)TableProxies.fluent(joinOperation(targetTableType, joinType, weakJoinLambda));
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable,
            WeakJoin<RoleTable, TT> weakJoinLambda) {
        return weakJoin(targetBaseTable, JoinType.INNER, weakJoinLambda);
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable, JoinType joinType,
            WeakJoin<RoleTable, TT> weakJoinLambda) {
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
