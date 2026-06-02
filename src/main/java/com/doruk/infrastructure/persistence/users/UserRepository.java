package com.doruk.infrastructure.persistence.users;

import com.doruk.application.app.users.dto.*;
import com.doruk.application.dto.StoredObject;
import com.doruk.application.dto.UploadedFile;
import com.doruk.application.enums.ObjectVisibility;
import com.doruk.domain.shared.enums.MultiAuthType;
import com.doruk.domain.shared.enums.UserAccountStatus;
import com.doruk.infrastructure.persistence.entity.*;
import com.doruk.jooq.enums.FileVisibility;
import com.doruk.infrastructure.persistence.users.mapper.ProfileMapper;
import com.doruk.jooq.tables.MediaStore;
import com.doruk.jooq.tables.UserProfiles;
import com.doruk.jooq.tables.UserRoles;
import com.doruk.jooq.tables.Users;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.babyfish.jimmer.sql.ast.mutation.SaveMode;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.impl.DSL;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class UserRepository {
    private final JSqlClient sqlClient;
    private final ProfileMapper profileMapper;
    private final DSLContext dsl;

    public Optional<UserUniqueFields> findByUsernameOrEmail(String username, String email) {
        var t = UserTable.$;
        var userName = username != null ? username.toLowerCase(Locale.ROOT) : null;
        return sqlClient.createQuery(t)
                .where(Predicate.or(t.username().eqIf(userName),
                        t.email().eq(email.toLowerCase(Locale.ROOT))))
                .select(t.username(), t.email())
                .execute()
                .stream()
                .map(u -> new UserUniqueFields(u.get_1(), u.get_2()))
                .findFirst();
    }

    public UserResponseDto createUser(CreateUserCmd dto, String hashedPassword) {
        var u = Users.USERS;
        var r = UserRoles.USER_ROLES;

        return dsl.transactionResult(configuration -> {
            var ctx = DSL.using(configuration);
            var usr = ctx.insertInto(u)
                    .set(u.USERNAME, dto.username())
                    .set(u.EMAIL, dto.email())
                    .set(u.PASSWORD, hashedPassword)
                    .set(u.PHONE, dto.phone())
                    .returning()
                    .fetchOne(rs -> UserResponseDto.builder()
                            .id(rs.getId())
                            .username(rs.getUsername())
                            .email(rs.getEmail())
                            .phone(rs.getPhone())
                            .status(rs.getStatus())
                            .emailVerified(rs.getIsEmailVerified())
                            .phoneVerified(rs.getIsPhoneVerified())
                            .multiFactorAuth(rs.getMultiFactorAuth())
                            .createdAt(rs.getCreatedAt())
                            .updatedAt(rs.getUpdatedAt())
                            .build()
                    );

            // Assign default role
            ctx.insertInto(r)
                    .set(r.USER_ID, Objects.requireNonNull(usr).id())
                    .set(r.NAME, "USER")
                    .execute();

            return usr;
        });
    }

    public ProfileDto updateProfile(String userId, ProfileDto dto) {
        var draft = UserProfileDraft.$.produce(d -> d
                .setFullName(dto.fullName())
                .setAddress(dto.address())
                .setCity(dto.city())
                .setState(dto.state())
                .setCountry(dto.country())
                .setPostalCode(dto.postalCode())
                .setUpdatedAt(OffsetDateTime.now())
                .setUser(UserDraft.$.produce(u ->
                        u.setId(UUID.fromString(userId))))
        );
        var res = sqlClient.saveCommand(draft).setMode(SaveMode.UPSERT).execute();
        return profileMapper.toProfileDto(res.getModifiedEntity());
    }

    public Optional<String> updateProfileIconReturningOld(String userId, UploadedFile icon) {
        return sqlClient.transaction(() -> {
            var t = UserProfileTable.$;
            var oldPic = sqlClient.createQuery(t)
                    .where(t.userId().eq(UUID.fromString(userId)))
                    .select(t.profileIcon())
                    .execute();

            // update with new file id
            sqlClient.createUpdate(t)
                    .where(t.user().id().eq(UUID.fromString(userId)))
                    .set(t.profileIconId(), icon.id())
                    .execute();

            return oldPic.stream()
                    .map(m -> {
                        // delete old file if exists
                        sqlClient.deleteById(MediaStore.class, m.id());
                        return m.objectKey();
                    })
                    .findFirst();
        });
    }

    public CurrentUserDto getCurrentUser(String userId) {
        var u = Users.USERS;
        var r = UserRoles.USER_ROLES;
        var p = UserProfiles.USER_PROFILES;
        var m = MediaStore.MEDIA_STORE;

        var profileField = DSL.row(
                p.USER_ID.cast(String.class),
                p.FULL_NAME,
                p.ADDRESS,
                p.CITY,
                p.STATE,
                p.COUNTRY,
                p.POSTAL_CODE,
                p.CREATED_AT,
                p.UPDATED_AT
        ).mapping(ProfileDto::new);

        var iconField = DSL.row(
                m.OBJECT_KEY,
                m.MIME_TYPE,
                m.SIZE,
                m.VISIBILITY
        ).mapping((String objectKey, String mimeType, Long size, FileVisibility visibility) ->
                objectKey == null ? null : StoredObject.builder()
                        .objectKey(objectKey)
                        .originalName(null)
                        .size(size != null ? size : 0)
                        .visibility(ObjectVisibility.valueOf(visibility.name()))
                        .mimeType(mimeType)
                        .build()
        );

        var rolesField = DSL.multiset(
                DSL.select(r.NAME)
                        .where(r.USER_ID.eq(u.ID))
        ).convertFrom(rs -> rs.stream().map(Record1::value1).toList());

        return dsl.select(
                        u.ID.cast(String.class),
                        u.USERNAME,
                        u.EMAIL,
                        u.PHONE,
                        u.IS_EMAIL_VERIFIED,
                        u.IS_PHONE_VERIFIED,
                        u.MULTI_FACTOR_AUTH,
                        u.STATUS,
                        u.CREATED_AT,
                        u.UPDATED_AT,
                        profileField,
                        iconField,
                        rolesField
                )
                .from(u)
                .leftJoin(p).on(u.ID.eq(p.USER_ID))
                .leftJoin(m).on(p.PROFILE_ICON.eq(m.ID))
                .where(u.ID.eq(UUID.fromString(userId)))
                .fetchOne(rs -> CurrentUserDto.builder()
                        .id(rs.get(0, String.class))
                        .username(rs.get(1, String.class))
                        .email(rs.get(2, String.class))
                        .phone(rs.get(3, String.class))
                        .emailVerified(rs.get(4, Boolean.class))
                        .phoneVerified(rs.get(5, Boolean.class))
                        .multiFactorAuth(rs.get(6, MultiAuthType.class))
                        .status(rs.get(7, UserAccountStatus.class))
                        .createdAt(rs.get(8, OffsetDateTime.class))
                        .updatedAt(rs.get(9, OffsetDateTime.class))
                        .profile(rs.get(10, ProfileDto.class))
                        .profileIcon(rs.get(11, StoredObject.class))
                        .roles(rs.get(12, List.class))
                        .build()
                );
    }
}
