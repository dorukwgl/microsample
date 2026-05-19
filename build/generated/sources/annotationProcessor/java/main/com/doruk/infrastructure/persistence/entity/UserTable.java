package com.doruk.infrastructure.persistence.entity;

import com.doruk.domain.shared.enums.MultiAuthType;
import com.doruk.domain.shared.enums.UserAccountStatus;
import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Override;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.function.Function;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.sql.JoinType;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.babyfish.jimmer.sql.ast.PropExpression;
import org.babyfish.jimmer.sql.ast.impl.base.BaseTableOwner;
import org.babyfish.jimmer.sql.ast.impl.table.TableImplementor;
import org.babyfish.jimmer.sql.ast.table.TableEx;
import org.babyfish.jimmer.sql.ast.table.spi.AbstractTypedTable;

@GeneratedBy(
        type = User.class
)
public class UserTable extends AbstractTypedTable<User> implements UserProps {
    public static final UserTable $ = new UserTable();

    public UserTable() {
        super(User.class);
    }

    public UserTable(AbstractTypedTable.DelayedOperation<User> delayedOperation) {
        super(User.class, delayedOperation);
    }

    public UserTable(TableImplementor<User> table) {
        super(table);
    }

    protected UserTable(UserTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected UserTable(UserTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    @Override
    public PropExpression.Cmp<UUID> id() {
        return __get(UserProps.ID.unwrap());
    }

    @Override
    public PropExpression.Str username() {
        return __get(UserProps.USERNAME.unwrap());
    }

    @Override
    public PropExpression.Str email() {
        return __get(UserProps.EMAIL.unwrap());
    }

    @Override
    public PropExpression.Str phone() {
        return __get(UserProps.PHONE.unwrap());
    }

    @Override
    public PropExpression.Str password() {
        return __get(UserProps.PASSWORD.unwrap());
    }

    @Override
    public PropExpression.Cmp<MultiAuthType> multiFactorAuth() {
        return __get(UserProps.MULTI_FACTOR_AUTH.unwrap());
    }

    @Override
    public PropExpression<Boolean> emailVerified() {
        return __get(UserProps.EMAIL_VERIFIED.unwrap());
    }

    @Override
    public PropExpression<Boolean> phoneVerified() {
        return __get(UserProps.PHONE_VERIFIED.unwrap());
    }

    @Override
    public PropExpression.Cmp<UserAccountStatus> status() {
        return __get(UserProps.STATUS.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> updatedAt() {
        return __get(UserProps.UPDATED_AT.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> createdAt() {
        return __get(UserProps.CREATED_AT.unwrap());
    }

    @Override
    public PropExpression<Boolean> orgAdmin() {
        return __get(UserProps.ORG_ADMIN.unwrap());
    }

    @Override
    public UserProfileTable profile() {
        __beforeJoin();
        if (raw != null) {
            return new UserProfileTable(raw.joinImplementor(UserProps.PROFILE.unwrap()));
        }
        return new UserProfileTable(joinOperation(UserProps.PROFILE.unwrap()));
    }

    @Override
    public UserProfileTable profile(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new UserProfileTable(raw.joinImplementor(UserProps.PROFILE.unwrap(), joinType));
        }
        return new UserProfileTable(joinOperation(UserProps.PROFILE.unwrap(), joinType));
    }

    @Override
    public Predicate roles(Function<RoleTableEx, Predicate> block) {
        return exists(UserProps.ROLES.unwrap(), block);
    }

    @Override
    public Predicate sessions(Function<SessionTableEx, Predicate> block) {
        return exists(UserProps.SESSIONS.unwrap(), block);
    }

    @Override
    public Predicate biometrics(Function<BiometricTableEx, Predicate> block) {
        return exists(UserProps.BIOMETRICS.unwrap(), block);
    }

    @Override
    public OrganizationsTable organization() {
        __beforeJoin();
        if (raw != null) {
            return new OrganizationsTable(raw.joinImplementor(UserProps.ORGANIZATION.unwrap()));
        }
        return new OrganizationsTable(joinOperation(UserProps.ORGANIZATION.unwrap()));
    }

    @Override
    public OrganizationsTable organization(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new OrganizationsTable(raw.joinImplementor(UserProps.ORGANIZATION.unwrap(), joinType));
        }
        return new OrganizationsTable(joinOperation(UserProps.ORGANIZATION.unwrap(), joinType));
    }

    @Override
    public PropExpression.Cmp<UUID> organizationId() {
        return __getAssociatedId(UserProps.ORGANIZATION.unwrap());
    }

    @Override
    public UserTableEx asTableEx() {
        return new UserTableEx(this, (String)null);
    }

    @Override
    public UserTable __disableJoin(String reason) {
        return new UserTable(this, reason);
    }

    @Override
    public UserTable __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new UserTable(this, baseTableOwner);
    }

    @GeneratedBy(
            type = User.class
    )
    public static class Remote extends AbstractTypedTable<User> {
        public Remote(AbstractTypedTable.DelayedOperation delayedOperation) {
            super(User.class, delayedOperation);
        }

        public Remote(TableImplementor<User> table) {
            super(table);
        }

        public Remote(Remote base, BaseTableOwner baseTableOwner) {
            super(base, baseTableOwner);
        }

        public PropExpression.Cmp<UUID> id() {
            return (org.babyfish.jimmer.sql.ast.PropExpression.Cmp<java.util.UUID>)this.<UUID>get(UserProps.ID.unwrap());
        }

        @Override
        @Deprecated
        public TableEx<User> asTableEx() {
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
