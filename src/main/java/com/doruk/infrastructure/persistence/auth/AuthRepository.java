package com.doruk.infrastructure.persistence.auth;

import com.doruk.application.auth.dto.AuthDto;
import com.doruk.domain.shared.enums.MultiAuthType;
import com.doruk.domain.shared.enums.Permissions;
import com.doruk.infrastructure.config.AppExecutors;
import com.doruk.infrastructure.persistence.entity.Permission;
import com.doruk.infrastructure.persistence.entity.UserTable;
import com.doruk.infrastructure.persistence.entity.UserTableEx;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.JoinType;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.babyfish.jimmer.sql.ast.tuple.Tuple8;
import org.babyfish.jimmer.sql.runtime.LogicalDeletedBehavior;

import java.util.*;

@Singleton
@RequiredArgsConstructor
public class AuthRepository {
    private final JSqlClient sqlClient;

    public Optional<AuthDto> findByUsernameOrEmail(String field) {
        var t = UserTableEx.$;
        var dt = sqlClient.filters(cfg -> cfg.setBehavior(LogicalDeletedBehavior.IGNORED))
                .createQuery(UserTable.$)
                .where(Predicate.or(UserTable.$.username().eq(field), UserTable.$.email().eq(field)))
                .select(
                        t.id(),
                        t.password(),
                        t.phone(),
                        t.email(),
                        t.emailVerified(),
                        t.phoneVerified(),
                        t.multiFactorAuth(),
                        t.roles(JoinType.LEFT).permissions(JoinType.LEFT)
                )
                .execute();

        if (dt.isEmpty())
            return Optional.empty();

        return Optional.of(toAuthDto(dt));
    }

    private static AuthDto toAuthDto(List<Tuple8<UUID, String, String, String, Boolean, Boolean, MultiAuthType, Permission>> dt) {
        var commons = dt.getFirst();
        Set<Permissions> permissions = new HashSet<>();

        dt.forEach(tup ->
                permissions.add(Permissions.valueOf(tup.get_8().name())));

        return AuthDto.builder()
                .id(commons.get_1().toString())
                .password(commons.get_2())
                .phone(commons.get_3())
                .email(commons.get_4())
                .emailVerified(commons.get_5())
                .phoneVerified(commons.get_6())
                .multiFactorAuth(commons.get_7())
                .permissions(permissions)
                .build();
    }
}
