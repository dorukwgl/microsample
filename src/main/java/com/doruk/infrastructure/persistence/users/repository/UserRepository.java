package com.doruk.infrastructure.persistence.users.repository;

import com.doruk.application.security.PasswordEncoder;
import com.doruk.application.users.dto.CreateUserCmd;
import com.doruk.application.users.dto.UserResponseDto;
import com.doruk.application.users.dto.UserUniqueFields;
import com.doruk.infrastructure.config.AppExecutors;
import com.doruk.infrastructure.persistence.entity.UserDraft;
import com.doruk.infrastructure.persistence.entity.UserTable;
import com.doruk.infrastructure.persistence.users.mapper.UserMapper;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.Predicate;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class UserRepository {
    private final JSqlClient sqlClient;
    private final AppExecutors executors;
    private final PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

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

    public UserResponseDto createUser(CreateUserCmd dto) {
        var draft = UserDraft.$.produce(u ->
                u.setUsername(dto.username().toLowerCase(Locale.ROOT))
                        .setEmail(dto.email().toLowerCase(Locale.ROOT))
                        .setPhone(dto.phone())
                        .setPassword(passwordEncoder.encode(dto.password()))
        );

        var user = sqlClient.saveCommand(draft).execute();
        return userMapper.toResponseDto(user.getModifiedEntity());
    }

    public void verifyUserEmail(String userId) {
//        var t = UserTable.$;
//        sqlClient.createUpdate(t)
//                .where(t.id().eq(UUID.fromString(userId)))
//                .set(t.emailVerified(), true)
//                .execute();

        var draft = UserDraft.$.produce(u -> u.setId(UUID.fromString(userId))
                .setEmailVerified(true));
        sqlClient.saveCommand(draft).execute();
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
}
