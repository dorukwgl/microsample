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
        type = UserProfile.class
)
public class UserProfileTableEx extends UserProfileTable implements TableExProxy<UserProfile, UserProfileTable> {
    public static final UserProfileTableEx $ = new UserProfileTableEx(UserProfileTable.$, (String)null);

    public UserProfileTableEx() {
        super();
    }

    public UserProfileTableEx(AbstractTypedTable.DelayedOperation<UserProfile> delayedOperation) {
        super(delayedOperation);
    }

    public UserProfileTableEx(TableImplementor<UserProfile> table) {
        super(table);
    }

    protected UserProfileTableEx(UserProfileTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected UserProfileTableEx(UserProfileTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    public UserTableEx user() {
        __beforeJoin();
        if (raw != null) {
            return new UserTableEx(raw.joinImplementor(UserProfileProps.USER.unwrap()));
        }
        return new UserTableEx(joinOperation(UserProfileProps.USER.unwrap()));
    }

    public UserTableEx user(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new UserTableEx(raw.joinImplementor(UserProfileProps.USER.unwrap(), joinType));
        }
        return new UserTableEx(joinOperation(UserProfileProps.USER.unwrap(), joinType));
    }

    public MediaStoreTableEx profileIcon() {
        __beforeJoin();
        if (raw != null) {
            return new MediaStoreTableEx(raw.joinImplementor(UserProfileProps.PROFILE_ICON.unwrap()));
        }
        return new MediaStoreTableEx(joinOperation(UserProfileProps.PROFILE_ICON.unwrap()));
    }

    public MediaStoreTableEx profileIcon(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new MediaStoreTableEx(raw.joinImplementor(UserProfileProps.PROFILE_ICON.unwrap(), joinType));
        }
        return new MediaStoreTableEx(joinOperation(UserProfileProps.PROFILE_ICON.unwrap(), joinType));
    }

    @Override
    public UserProfileTableEx asTableEx() {
        return this;
    }

    @Override
    public UserProfileTableEx __disableJoin(String reason) {
        return new UserProfileTableEx(this, reason);
    }

    @Override
    public UserProfileTableEx __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new UserProfileTableEx(this, baseTableOwner);
    }

    public <TT extends Table<?>, WJ extends WeakJoin<UserProfileTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType) {
        return weakJoin(weakJoinType, JoinType.INNER);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>, WJ extends WeakJoin<UserProfileTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType, JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(weakJoinType, joinType));
        }
        return (TT)TableProxies.fluent(joinOperation(weakJoinType, joinType));
    }

    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType,
            WeakJoin<UserProfileTable, TT> weakJoinLambda) {
        return weakJoin(targetTableType, JoinType.INNER, weakJoinLambda);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType, JoinType joinType,
            WeakJoin<UserProfileTable, TT> weakJoinLambda) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(targetTableType, joinType, weakJoinLambda));
        }
        return (TT)TableProxies.fluent(joinOperation(targetTableType, joinType, weakJoinLambda));
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable,
            WeakJoin<UserProfileTable, TT> weakJoinLambda) {
        return weakJoin(targetBaseTable, JoinType.INNER, weakJoinLambda);
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable, JoinType joinType,
            WeakJoin<UserProfileTable, TT> weakJoinLambda) {
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
