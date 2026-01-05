package com.doruk.infrastructure.persistence.users.repository;

import com.doruk.application.users.dto.*;
import com.doruk.application.security.PasswordEncoder;
import com.doruk.infrastructure.persistence.entity.*;
import com.doruk.infrastructure.persistence.users.mapper.ProfileMapper;
import com.doruk.infrastructure.persistence.users.mapper.UserMapper;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.JoinType;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.babyfish.jimmer.sql.ast.mutation.SaveMode;
import org.babyfish.jimmer.sql.fetcher.ReferenceFetchType;

import java.util.*;

@Singleton
@RequiredArgsConstructor
public class UserRepository {
    private final JSqlClient sqlClient;
    private final UserMapper userMapper;
    private final ProfileMapper profileMapper;

//    public Mono<UserDto> findByEmailOrUsername(String field) {
//        var table = UserTable.$;
//        return Mono.fromCallable(() -> sqlClient.createQuery(table)
//                    .where(Predicate.or(
//                            table.username().eq(field),
//                            table.email().eq(field)
//                    ))
//                    .select(table)
//                    .fetchFirst())
//                .subscribeOn(executors.BLOCKING);
//    }

    public Optional<UserUniqueFields> findByUsernameOrEmail(String username, String email) {
        var t = UserTable.$;
        var userQuery = sqlClient.createQuery(t)
                .where(Predicate.or(t.username().eq(username.toLowerCase(Locale.ROOT)),
                        t.email().eq(email.toLowerCase(Locale.ROOT))))
                .select(t.username(), t.email())
                .execute();

        if (userQuery.isEmpty())
            return Optional.empty();

        var user = userQuery.getFirst();
        return Optional.of(new UserUniqueFields(user.get_1(), user.get_2()));
    }


    public UserResponseDto createUser(CreateUserCmd dto, String hashedPassword) {
        var draft = UserDraft.$.produce(u ->
                u.setUsername(dto.username().toLowerCase(Locale.ROOT))
                        .setEmail(dto.email().toLowerCase(Locale.ROOT))
                        .setPhone(dto.phone())
                        .setPassword(hashedPassword)
                        .setRoles(List.of(RoleDraft.$.produce(r -> r.setName("USER"))))
        );

        var id = sqlClient.transaction(() -> sqlClient.saveCommand(draft).execute()
                .getModifiedEntity().id());
        var user = sqlClient.findById(User.class, id);

        return userMapper.toResponseDto(Objects.requireNonNull(user));
    }

    public void verifyUserEmail(String userId) {
        var t = UserTable.$;
        sqlClient.createUpdate(t)
                .where(t.id().eq(UUID.fromString(userId)))
                .set(t.emailVerified(), true)
                .execute();
    }

    public void verifyUserPhone(String userId) {
        var t = UserTable.$;
        sqlClient.createUpdate(t)
                .where(t.id().eq(UUID.fromString(userId)))
                .set(t.phoneVerified(), true)
                .execute();
    }

    public String getPhoneNumber(String userId) {
        return sqlClient.createQuery(UserTable.$)
                .where(UserTable.$.id().eq(UUID.fromString(userId)))
                .select(UserTable.$.phone())
                .execute()
                .getFirst();
    }

    public ProfileDto updateProfile(String userId, ProfileDto dto) {
        var draft = UserProfileDraft.$.produce(d -> d
                .setFullName(dto.fullName())
                .setAddress(dto.address())
                .setCity(dto.city())
                .setState(dto.state())
                .setCountry(dto.country())
                .setPostalCode(dto.postalCode())
                .setUser(UserDraft.$.produce(u ->
                        u.setId(UUID.fromString(userId))))
        );
        var res = sqlClient.saveCommand(draft).setMode(SaveMode.UPSERT).execute();
        return profileMapper.toProfileDto(res.getModifiedEntity());
    }

    public void updatePhoneNumber(String userId, String phone) {
        var t = UserTable.$;
        sqlClient.createUpdate(t)
                .where(t.id().eq(UUID.fromString(userId)))
                .set(t.phone(), phone)
                .set(t.phoneVerified(), false)
                .execute();
    }

    public void updateEmailAddress(String userId, String email) {
        var t = UserTable.$;
        sqlClient.createUpdate(t)
                .where(t.id().eq(UUID.fromString(userId)))
                .set(t.email(), email)
                .set(t.emailVerified(), false)
                .execute();
    }

    public String updateProfileIconReturningOld(String userId, String profilePicId) {
        var t = UserProfileTable.$;
        var profilePics = sqlClient.createQuery(t)
                        .where(t.userId().eq(UUID.fromString(userId)))
                        .select(t.profilePicture())
                .execute();

        sqlClient.createUpdate(t)
                .where(t.user().id().eq(UUID.fromString(userId)))
                .set(t.profilePicture(), profilePicId)
                .execute();

        return profilePics.isEmpty() ? null : profilePics.getFirst();
    }

    public CurrentUserDto getCurrentUser(String userId) {
        var t = UserTable.$;
        var userLst = sqlClient.createQuery(t)
                .where(t.id().eq(UUID.fromString(userId)))
                .select(
                        t.fetch(UserFetcher.$.allScalarFields()
                                .profile(ReferenceFetchType.JOIN_ALWAYS, UserProfileFetcher.$.allScalarFields())
                                .roles()
                ))
                .execute();

        return userMapper.toCurrentUserDto(userLst.getFirst());
    }
}
