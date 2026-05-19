package com.doruk.infrastructure.persistence.entity;

import java.lang.Long;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.meta.ImmutableType;
import org.babyfish.jimmer.meta.TypedProp;
import org.babyfish.jimmer.sql.JoinType;
import org.babyfish.jimmer.sql.ast.PropExpression;
import org.babyfish.jimmer.sql.ast.Selection;
import org.babyfish.jimmer.sql.ast.table.Props;
import org.babyfish.jimmer.sql.ast.table.PropsFor;

@GeneratedBy(
        type = UserProfile.class
)
@PropsFor(UserProfile.class)
public interface UserProfileProps extends Props, Selection<UserProfile> {
    TypedProp.Scalar<UserProfile, UUID> ID = 
        TypedProp.scalar(ImmutableType.get(UserProfile.class).getProp("id"));

    TypedProp.Reference<UserProfile, User> USER = 
        TypedProp.reference(ImmutableType.get(UserProfile.class).getProp("user"));

    TypedProp.Scalar<UserProfile, String> FULL_NAME = 
        TypedProp.scalar(ImmutableType.get(UserProfile.class).getProp("fullName"));

    TypedProp.Reference<UserProfile, MediaStore> PROFILE_ICON = 
        TypedProp.reference(ImmutableType.get(UserProfile.class).getProp("profileIcon"));

    TypedProp.Scalar<UserProfile, String> ADDRESS = 
        TypedProp.scalar(ImmutableType.get(UserProfile.class).getProp("address"));

    TypedProp.Scalar<UserProfile, String> CITY = 
        TypedProp.scalar(ImmutableType.get(UserProfile.class).getProp("city"));

    TypedProp.Scalar<UserProfile, String> STATE = 
        TypedProp.scalar(ImmutableType.get(UserProfile.class).getProp("state"));

    TypedProp.Scalar<UserProfile, String> COUNTRY = 
        TypedProp.scalar(ImmutableType.get(UserProfile.class).getProp("country"));

    TypedProp.Scalar<UserProfile, String> POSTAL_CODE = 
        TypedProp.scalar(ImmutableType.get(UserProfile.class).getProp("postalCode"));

    TypedProp.Scalar<UserProfile, OffsetDateTime> CREATED_AT = 
        TypedProp.scalar(ImmutableType.get(UserProfile.class).getProp("createdAt"));

    TypedProp.Scalar<UserProfile, OffsetDateTime> UPDATED_AT = 
        TypedProp.scalar(ImmutableType.get(UserProfile.class).getProp("updatedAt"));

    PropExpression.Cmp<UUID> id();

    UserTable user();

    UserTable user(JoinType joinType);

    PropExpression.Cmp<UUID> userId();

    PropExpression.Str fullName();

    MediaStoreTable profileIcon();

    MediaStoreTable profileIcon(JoinType joinType);

    PropExpression.Num<Long> profileIconId();

    PropExpression.Str address();

    PropExpression.Str city();

    PropExpression.Str state();

    PropExpression.Str country();

    PropExpression.Str postalCode();

    PropExpression.Tp<OffsetDateTime> createdAt();

    PropExpression.Tp<OffsetDateTime> updatedAt();
}
