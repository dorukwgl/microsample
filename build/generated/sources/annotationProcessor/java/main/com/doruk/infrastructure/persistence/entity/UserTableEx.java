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
        type = User.class
)
public class UserTableEx extends UserTable implements TableExProxy<User, UserTable> {
    public static final UserTableEx $ = new UserTableEx(UserTable.$, (String)null);

    public UserTableEx() {
        super();
    }

    public UserTableEx(AbstractTypedTable.DelayedOperation<User> delayedOperation) {
        super(delayedOperation);
    }

    public UserTableEx(TableImplementor<User> table) {
        super(table);
    }

    protected UserTableEx(UserTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected UserTableEx(UserTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    public UserProfileTableEx profile() {
        __beforeJoin();
        if (raw != null) {
            return new UserProfileTableEx(raw.joinImplementor(UserProps.PROFILE.unwrap()));
        }
        return new UserProfileTableEx(joinOperation(UserProps.PROFILE.unwrap()));
    }

    public UserProfileTableEx profile(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new UserProfileTableEx(raw.joinImplementor(UserProps.PROFILE.unwrap(), joinType));
        }
        return new UserProfileTableEx(joinOperation(UserProps.PROFILE.unwrap(), joinType));
    }

    public RoleTableEx roles() {
        __beforeJoin();
        if (raw != null) {
            return new RoleTableEx(raw.joinImplementor(UserProps.ROLES.unwrap()));
        }
        return new RoleTableEx(joinOperation(UserProps.ROLES.unwrap()));
    }

    public RoleTableEx roles(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new RoleTableEx(raw.joinImplementor(UserProps.ROLES.unwrap(), joinType));
        }
        return new RoleTableEx(joinOperation(UserProps.ROLES.unwrap(), joinType));
    }

    @Override
    public Predicate roles(Function<RoleTableEx, Predicate> block) {
        return exists(UserProps.ROLES.unwrap(), block);
    }

    public SessionTableEx sessions() {
        __beforeJoin();
        if (raw != null) {
            return new SessionTableEx(raw.joinImplementor(UserProps.SESSIONS.unwrap()));
        }
        return new SessionTableEx(joinOperation(UserProps.SESSIONS.unwrap()));
    }

    public SessionTableEx sessions(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new SessionTableEx(raw.joinImplementor(UserProps.SESSIONS.unwrap(), joinType));
        }
        return new SessionTableEx(joinOperation(UserProps.SESSIONS.unwrap(), joinType));
    }

    @Override
    public Predicate sessions(Function<SessionTableEx, Predicate> block) {
        return exists(UserProps.SESSIONS.unwrap(), block);
    }

    public BiometricTableEx biometrics() {
        __beforeJoin();
        if (raw != null) {
            return new BiometricTableEx(raw.joinImplementor(UserProps.BIOMETRICS.unwrap()));
        }
        return new BiometricTableEx(joinOperation(UserProps.BIOMETRICS.unwrap()));
    }

    public BiometricTableEx biometrics(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new BiometricTableEx(raw.joinImplementor(UserProps.BIOMETRICS.unwrap(), joinType));
        }
        return new BiometricTableEx(joinOperation(UserProps.BIOMETRICS.unwrap(), joinType));
    }

    @Override
    public Predicate biometrics(Function<BiometricTableEx, Predicate> block) {
        return exists(UserProps.BIOMETRICS.unwrap(), block);
    }

    public OrganizationsTableEx organization() {
        __beforeJoin();
        if (raw != null) {
            return new OrganizationsTableEx(raw.joinImplementor(UserProps.ORGANIZATION.unwrap()));
        }
        return new OrganizationsTableEx(joinOperation(UserProps.ORGANIZATION.unwrap()));
    }

    public OrganizationsTableEx organization(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new OrganizationsTableEx(raw.joinImplementor(UserProps.ORGANIZATION.unwrap(), joinType));
        }
        return new OrganizationsTableEx(joinOperation(UserProps.ORGANIZATION.unwrap(), joinType));
    }

    @Override
    public UserTableEx asTableEx() {
        return this;
    }

    @Override
    public UserTableEx __disableJoin(String reason) {
        return new UserTableEx(this, reason);
    }

    @Override
    public UserTableEx __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new UserTableEx(this, baseTableOwner);
    }

    public <TT extends Table<?>, WJ extends WeakJoin<UserTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType) {
        return weakJoin(weakJoinType, JoinType.INNER);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>, WJ extends WeakJoin<UserTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType, JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(weakJoinType, joinType));
        }
        return (TT)TableProxies.fluent(joinOperation(weakJoinType, joinType));
    }

    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType,
            WeakJoin<UserTable, TT> weakJoinLambda) {
        return weakJoin(targetTableType, JoinType.INNER, weakJoinLambda);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType, JoinType joinType,
            WeakJoin<UserTable, TT> weakJoinLambda) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(targetTableType, joinType, weakJoinLambda));
        }
        return (TT)TableProxies.fluent(joinOperation(targetTableType, joinType, weakJoinLambda));
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable,
            WeakJoin<UserTable, TT> weakJoinLambda) {
        return weakJoin(targetBaseTable, JoinType.INNER, weakJoinLambda);
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable, JoinType joinType,
            WeakJoin<UserTable, TT> weakJoinLambda) {
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
