package com.doruk.infrastructure.persistence.entity;

import com.doruk.domain.shared.enums.MultiAuthType;
import com.doruk.domain.shared.enums.UserAccountStatus;
import java.lang.Boolean;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.function.Function;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.meta.ImmutableType;
import org.babyfish.jimmer.meta.TypedProp;
import org.babyfish.jimmer.sql.JoinType;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.babyfish.jimmer.sql.ast.PropExpression;
import org.babyfish.jimmer.sql.ast.Selection;
import org.babyfish.jimmer.sql.ast.table.Props;
import org.babyfish.jimmer.sql.ast.table.PropsFor;

@GeneratedBy(
        type = User.class
)
@PropsFor(User.class)
public interface UserProps extends Props, Selection<User> {
    TypedProp.Scalar<User, UUID> ID = 
        TypedProp.scalar(ImmutableType.get(User.class).getProp("id"));

    TypedProp.Scalar<User, String> USERNAME = 
        TypedProp.scalar(ImmutableType.get(User.class).getProp("username"));

    TypedProp.Scalar<User, String> EMAIL = 
        TypedProp.scalar(ImmutableType.get(User.class).getProp("email"));

    TypedProp.Scalar<User, String> PHONE = 
        TypedProp.scalar(ImmutableType.get(User.class).getProp("phone"));

    TypedProp.Scalar<User, String> PASSWORD = 
        TypedProp.scalar(ImmutableType.get(User.class).getProp("password"));

    TypedProp.Scalar<User, MultiAuthType> MULTI_FACTOR_AUTH = 
        TypedProp.scalar(ImmutableType.get(User.class).getProp("multiFactorAuth"));

    TypedProp.Scalar<User, Boolean> EMAIL_VERIFIED = 
        TypedProp.scalar(ImmutableType.get(User.class).getProp("emailVerified"));

    TypedProp.Scalar<User, Boolean> PHONE_VERIFIED = 
        TypedProp.scalar(ImmutableType.get(User.class).getProp("phoneVerified"));

    TypedProp.Scalar<User, UserAccountStatus> STATUS = 
        TypedProp.scalar(ImmutableType.get(User.class).getProp("status"));

    TypedProp.Scalar<User, OffsetDateTime> UPDATED_AT = 
        TypedProp.scalar(ImmutableType.get(User.class).getProp("updatedAt"));

    TypedProp.Scalar<User, OffsetDateTime> CREATED_AT = 
        TypedProp.scalar(ImmutableType.get(User.class).getProp("createdAt"));

    TypedProp.Scalar<User, UUID> ORGANIZATION_ID = 
        TypedProp.scalar(ImmutableType.get(User.class).getProp("organizationId"));

    TypedProp.Scalar<User, Boolean> ORG_ADMIN = 
        TypedProp.scalar(ImmutableType.get(User.class).getProp("orgAdmin"));

    TypedProp.Reference<User, UserProfile> PROFILE = 
        TypedProp.reference(ImmutableType.get(User.class).getProp("profile"));

    TypedProp.ReferenceList<User, Role> ROLES = 
        TypedProp.referenceList(ImmutableType.get(User.class).getProp("roles"));

    TypedProp.ReferenceList<User, Session> SESSIONS = 
        TypedProp.referenceList(ImmutableType.get(User.class).getProp("sessions"));

    TypedProp.ReferenceList<User, Biometric> BIOMETRICS = 
        TypedProp.referenceList(ImmutableType.get(User.class).getProp("biometrics"));

    TypedProp.Reference<User, Organizations> ORGANIZATION = 
        TypedProp.reference(ImmutableType.get(User.class).getProp("organization"));

    PropExpression.Cmp<UUID> id();

    PropExpression.Str username();

    PropExpression.Str email();

    PropExpression.Str phone();

    PropExpression.Str password();

    PropExpression.Cmp<MultiAuthType> multiFactorAuth();

    PropExpression<Boolean> emailVerified();

    PropExpression<Boolean> phoneVerified();

    PropExpression.Cmp<UserAccountStatus> status();

    PropExpression.Tp<OffsetDateTime> updatedAt();

    PropExpression.Tp<OffsetDateTime> createdAt();

    PropExpression<Boolean> orgAdmin();

    UserProfileTable profile();

    UserProfileTable profile(JoinType joinType);

    Predicate roles(Function<RoleTableEx, Predicate> block);

    Predicate sessions(Function<SessionTableEx, Predicate> block);

    Predicate biometrics(Function<BiometricTableEx, Predicate> block);

    OrganizationsTable organization();

    OrganizationsTable organization(JoinType joinType);

    PropExpression.Cmp<UUID> organizationId();
}
