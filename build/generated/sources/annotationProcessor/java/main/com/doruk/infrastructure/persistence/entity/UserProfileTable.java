package com.doruk.infrastructure.persistence.entity;

import java.lang.Deprecated;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.sql.JoinType;
import org.babyfish.jimmer.sql.ast.PropExpression;
import org.babyfish.jimmer.sql.ast.impl.base.BaseTableOwner;
import org.babyfish.jimmer.sql.ast.impl.table.TableImplementor;
import org.babyfish.jimmer.sql.ast.table.TableEx;
import org.babyfish.jimmer.sql.ast.table.spi.AbstractTypedTable;

@GeneratedBy(
        type = UserProfile.class
)
public class UserProfileTable extends AbstractTypedTable<UserProfile> implements UserProfileProps {
    public static final UserProfileTable $ = new UserProfileTable();

    public UserProfileTable() {
        super(UserProfile.class);
    }

    public UserProfileTable(AbstractTypedTable.DelayedOperation<UserProfile> delayedOperation) {
        super(UserProfile.class, delayedOperation);
    }

    public UserProfileTable(TableImplementor<UserProfile> table) {
        super(table);
    }

    protected UserProfileTable(UserProfileTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected UserProfileTable(UserProfileTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    @Override
    public PropExpression.Cmp<UUID> id() {
        return __get(UserProfileProps.ID.unwrap());
    }

    @Override
    public UserTable user() {
        __beforeJoin();
        if (raw != null) {
            return new UserTable(raw.joinImplementor(UserProfileProps.USER.unwrap()));
        }
        return new UserTable(joinOperation(UserProfileProps.USER.unwrap()));
    }

    @Override
    public UserTable user(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new UserTable(raw.joinImplementor(UserProfileProps.USER.unwrap(), joinType));
        }
        return new UserTable(joinOperation(UserProfileProps.USER.unwrap(), joinType));
    }

    @Override
    public PropExpression.Cmp<UUID> userId() {
        return __getAssociatedId(UserProfileProps.USER.unwrap());
    }

    @Override
    public PropExpression.Str fullName() {
        return __get(UserProfileProps.FULL_NAME.unwrap());
    }

    @Override
    public MediaStoreTable profileIcon() {
        __beforeJoin();
        if (raw != null) {
            return new MediaStoreTable(raw.joinImplementor(UserProfileProps.PROFILE_ICON.unwrap()));
        }
        return new MediaStoreTable(joinOperation(UserProfileProps.PROFILE_ICON.unwrap()));
    }

    @Override
    public MediaStoreTable profileIcon(JoinType joinType) {
        __beforeJoin();
        if (raw != null) {
            return new MediaStoreTable(raw.joinImplementor(UserProfileProps.PROFILE_ICON.unwrap(), joinType));
        }
        return new MediaStoreTable(joinOperation(UserProfileProps.PROFILE_ICON.unwrap(), joinType));
    }

    @Override
    public PropExpression.Num<Long> profileIconId() {
        return __getAssociatedId(UserProfileProps.PROFILE_ICON.unwrap());
    }

    @Override
    public PropExpression.Str address() {
        return __get(UserProfileProps.ADDRESS.unwrap());
    }

    @Override
    public PropExpression.Str city() {
        return __get(UserProfileProps.CITY.unwrap());
    }

    @Override
    public PropExpression.Str state() {
        return __get(UserProfileProps.STATE.unwrap());
    }

    @Override
    public PropExpression.Str country() {
        return __get(UserProfileProps.COUNTRY.unwrap());
    }

    @Override
    public PropExpression.Str postalCode() {
        return __get(UserProfileProps.POSTAL_CODE.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> createdAt() {
        return __get(UserProfileProps.CREATED_AT.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> updatedAt() {
        return __get(UserProfileProps.UPDATED_AT.unwrap());
    }

    @Override
    public UserProfileTableEx asTableEx() {
        return new UserProfileTableEx(this, (String)null);
    }

    @Override
    public UserProfileTable __disableJoin(String reason) {
        return new UserProfileTable(this, reason);
    }

    @Override
    public UserProfileTable __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new UserProfileTable(this, baseTableOwner);
    }

    @GeneratedBy(
            type = UserProfile.class
    )
    public static class Remote extends AbstractTypedTable<UserProfile> {
        public Remote(AbstractTypedTable.DelayedOperation delayedOperation) {
            super(UserProfile.class, delayedOperation);
        }

        public Remote(TableImplementor<UserProfile> table) {
            super(table);
        }

        public Remote(Remote base, BaseTableOwner baseTableOwner) {
            super(base, baseTableOwner);
        }

        public PropExpression.Cmp<UUID> id() {
            return (org.babyfish.jimmer.sql.ast.PropExpression.Cmp<java.util.UUID>)this.<UUID>get(UserProfileProps.ID.unwrap());
        }

        @Override
        @Deprecated
        public TableEx<UserProfile> asTableEx() {
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
