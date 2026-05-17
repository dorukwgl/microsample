package com.doruk.infrastructure.persistence.users;

import com.doruk.application.app.users.dto.*;
import com.doruk.application.dto.OrganizationDto;
import com.doruk.application.dto.UploadedFile;
import com.doruk.domain.shared.enums.OrganizationType;
import com.doruk.infrastructure.persistence.entity.*;
import com.doruk.infrastructure.persistence.users.mapper.ProfileMapper;
import com.doruk.infrastructure.persistence.users.mapper.UserMapper;
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
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class UserRepository {
    private final JSqlClient sqlClient;
    private final UserMapper userMapper;
    private final ProfileMapper profileMapper;
    private final DSLContext dsl;

    public Optional<UserUniqueFields> findByUsernameOrEmail(String username, String email) {
        var t = UserTable.$;
        return sqlClient.createQuery(t)
                .where(Predicate.or(t.username().eq(username.toLowerCase(Locale.ROOT)),
                        t.email().eq(email.toLowerCase(Locale.ROOT))))
                .select(t.username(), t.email())
                .execute()
                .stream()
                .map(u -> new UserUniqueFields(u.get_1(), u.get_2()))
                .findFirst();
    }

    public boolean orgCodeExists(String orgCode) {
        return sqlClient.createQuery(OrganizationsTable.$)
                .where(OrganizationsTable.$.orgCode().eq(orgCode))
                .exists();
    }

    private OrganizationDto createOrganization(String orgName, OrganizationType type, String orgCode) {
        var o = com.doruk.jooq.tables.Organizations.ORGANIZATIONS;
        return dsl.insertInto(o)
                .set(o.NAME, orgName)
                .set(o.TYPE, type)
                .set(o.ORG_CODE, orgCode)
                .returning()
                .fetchOne(rs -> OrganizationDto.builder()
                        .name(rs.getName())
                        .orgCode(rs.getOrgCode())
                        .type(rs.getType())
                        .id(rs.getId())
                        .build());

    }

    public UserResponseDto createUser(CreateUserCmd dto, String hashedPassword) {
        var u = Users.USERS;
        var r = UserRoles.USER_ROLES;

        return dsl.transactionResult(() -> {
            // Create organization based on account type
            boolean isOrgAdmin = dto.type() == OrganizationType.ENTERPRISE;
            OrganizationDto org = switch(dto.type()) {
                case PERSONAL -> createOrganization(null, OrganizationType.PERSONAL, null);
                case ENTERPRISE -> createOrganization(dto.orgName(), OrganizationType.ENTERPRISE, dto.orgCode());
            };


            UUID orgId = org.id();

            // Create user linked to organization
            var usr = dsl.insertInto(u)
                    .set(u.USERNAME, dto.username())
                    .set(u.EMAIL, dto.email())
                    .set(u.PASSWORD, hashedPassword)
                    .set(u.PHONE, dto.phone())
                    .set(u.ORGANIZATION_ID, orgId)
                    .set(u.IS_ORG_ADMIN, isOrgAdmin)
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
                            .isOrgAdmin(rs.getIsOrgAdmin())
                            .updatedAt(rs.getUpdatedAt())
                            .organization(org)
                            .build()
                    );

            // Assign default role
            dsl.insertInto(r)
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
        var o = com.doruk.jooq.tables.Organizations.ORGANIZATIONS;

        return dsl.select(
                        u.ID.cast(String.class),
                        u.USERNAME,
                        u.EMAIL,
                        u.PHONE,
                        u.IS_EMAIL_VERIFIED,
                        u.IS_PHONE_VERIFIED,
                        u.MULTI_FACTOR_AUTH,
                        u.STATUS,
                        u.IS_ORG_ADMIN,
                        u.CREATED_AT,
                        u.UPDATED_AT,

                        DSL.row(
                                p.USER_ID.cast(String.class),
                                p.FULL_NAME,
                                p.ADDRESS,
                                p.CITY,
                                p.STATE,
                                p.COUNTRY,
                                p.POSTAL_CODE,
                                p.CREATED_AT,
                                p.UPDATED_AT
                        ).mapping(ProfileDto::new),

                        // fetch organization
                        DSL.row(
                                o.ID,
                                o.NAME,
                                o.ORG_CODE,
                                o.TYPE
                        ).mapping(OrganizationDto::new),

                        // fetch roles
                        DSL.multiset(
                                DSL.select(r.NAME)
                                        .where(r.USER_ID.eq(u.ID))
                        ).convertFrom(rs -> rs.stream().map(Record1::value1).toList())
                )
                .from(u)
                .leftJoin(p).on(u.ID.eq(p.USER_ID))
                .leftJoin(o).on(o.ID.eq(u.ORGANIZATION_ID))
                .where(u.ID.eq(UUID.fromString(userId)))
                .fetchOne(userMapper::toCurrentUserDto);
    }
}
