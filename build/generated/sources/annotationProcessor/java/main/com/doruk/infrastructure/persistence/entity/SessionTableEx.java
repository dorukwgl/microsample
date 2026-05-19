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
        type = Session.class
)
public class SessionTableEx extends SessionTable implements TableExProxy<Session, SessionTable> {
    public static final SessionTableEx $ = new SessionTableEx(SessionTable.$, (String)null);

    public SessionTableEx() {
        super();
    }

    public SessionTableEx(AbstractTypedTable.DelayedOperation<Session> delayedOperation) {
        super(delayedOperation);
    }

    public SessionTableEx(TableImplementor<Session> table) {
        super(table);
    }

    protected SessionTableEx(SessionTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected SessionTableEx(SessionTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    public UserTableEx user() {
        __beforeJoin();
        if (raw != null) {
            return new UserTableEx(raw.joinImplementor(SessionProps.USER.unwrap()));
        }
        return new UserTableEx(joinOperation(SessionProps.USER.unwrap()));
    }

    public UserTableEx user(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new UserTableEx(raw.joinImplementor(SessionProps.USER.unwrap(), joinType));
        }
        return new UserTableEx(joinOperation(SessionProps.USER.unwrap(), joinType));
    }

    @Override
    public SessionTableEx asTableEx() {
        return this;
    }

    @Override
    public SessionTableEx __disableJoin(String reason) {
        return new SessionTableEx(this, reason);
    }

    @Override
    public SessionTableEx __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new SessionTableEx(this, baseTableOwner);
    }

    public <TT extends Table<?>, WJ extends WeakJoin<SessionTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType) {
        return weakJoin(weakJoinType, JoinType.INNER);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>, WJ extends WeakJoin<SessionTable, TT>> TT weakJoin(
            Class<WJ> weakJoinType, JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(weakJoinType, joinType));
        }
        return (TT)TableProxies.fluent(joinOperation(weakJoinType, joinType));
    }

    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType,
            WeakJoin<SessionTable, TT> weakJoinLambda) {
        return weakJoin(targetTableType, JoinType.INNER, weakJoinLambda);
    }

    @SuppressWarnings("all")
    public <TT extends Table<?>> TT weakJoin(Class<TT> targetTableType, JoinType joinType,
            WeakJoin<SessionTable, TT> weakJoinLambda) {
        __beforeJoin();
        if (raw != null) {
            return (TT)TableProxies.wrap(raw.weakJoinImplementor(targetTableType, joinType, weakJoinLambda));
        }
        return (TT)TableProxies.fluent(joinOperation(targetTableType, joinType, weakJoinLambda));
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable,
            WeakJoin<SessionTable, TT> weakJoinLambda) {
        return weakJoin(targetBaseTable, JoinType.INNER, weakJoinLambda);
    }

    public <TT extends BaseTable> TT weakJoin(TT targetBaseTable, JoinType joinType,
            WeakJoin<SessionTable, TT> weakJoinLambda) {
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
