package com.doruk.infrastructure.persistence.auth;

import com.doruk.application.auth.dto.AuthDto;
import com.doruk.application.auth.dto.SessionDto;
import com.doruk.domain.shared.enums.MultiAuthType;
import com.doruk.domain.shared.enums.Permissions;
import com.doruk.infrastructure.persistence.auth.mapper.AuthMapper;
import com.doruk.infrastructure.persistence.auth.mapper.SessionMapper;
import com.doruk.infrastructure.persistence.entity.*;
import jakarta.inject.Singleton;
import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.JoinType;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.babyfish.jimmer.sql.ast.tuple.Tuple8;
import org.babyfish.jimmer.sql.ast.tuple.Tuple9;
import org.babyfish.jimmer.sql.runtime.LogicalDeletedBehavior;

import java.time.LocalDateTime;
import java.util.*;

@Singleton
@RequiredArgsConstructor
public class AuthRepository {
    private final JSqlClient sqlClient;
    private final SessionMapper sessionMapper;
    private final AuthMapper authMapper;

    private String getDeviceId(String sessionId) {
        return sqlClient.createQuery(SessionTable.$)
                .where(SessionTable.$.sessionId().eq(sessionId))
                .select(SessionTable.$.deviceId())
                .execute()
                .getFirst();
    }

    public Optional<AuthDto> findByUsernameOrEmail(String field) {
        var t = UserTableEx.$;
        var dt = sqlClient.filters(cfg -> cfg.setBehavior(LogicalDeletedBehavior.IGNORED))
                .createQuery(UserTable.$)
                .where(Predicate.or(UserTable.$.username().eq(field), UserTable.$.email().eq(field)))
                .select(
                        t.id(),
                        t.username(),
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

        return Optional.of(authMapper.toAuthDto(dt));
    }

    public void createSession(String userId,
                              String sessionId,
                              Set<Permissions> permissions,
                              LocalDateTime expiration,
                              Optional<String> deviceId,
                              Optional<String> deviceInfo) {

        sqlClient.saveCommand(SessionDraft.$.produce(s ->
                s.setUserId(UUID.fromString(userId))
                        .setSessionId(sessionId)
                        .setCachedPermissions(permissions.stream().map(Permissions::id).toList())
                        .setExpiresAt(expiration)
                        .setDeviceId(deviceId.orElse(null))
                        .setDeviceInfo(deviceInfo.orElse(null)))
        ).execute();
    }

    public Pair<String, String> getMailAddress(String id) {
        var t = UserTable.$;
        var user = sqlClient.createQuery(t)
                .where(t.id().eq(UUID.fromString(id)))
                .select(t.username(), t.email())
                .execute();

        return user.isEmpty() ? null :
                new Pair<>(user.getFirst().get_1(), user.getFirst().get_2());
    }

    public Optional<SessionDto> getSession(String sessionId) {
        var t = SessionTable.$;
        var dt = sqlClient.createQuery(t)
                .where(t.sessionId().eq(sessionId))
                .select(t)
                .execute();

        if (dt.isEmpty())
            return Optional.empty();

        var session = sessionMapper.toDto(dt.getFirst());
        return Optional.of(session);
    }

    public List<SessionDto> getActiveDevices(String userId) {
        var t = SessionTable.$;
        return sqlClient.createQuery(t)
                .where(Predicate.and(
                        t.userId().eq(UUID.fromString(userId))),
                        t.expiresAt().gt(LocalDateTime.now())
                )
                .select(t.id(), t.deviceId(), t.deviceInfo(), t.createdAt())
                .execute()
                .stream()
                .map(s -> SessionDto.builder()
                        .id(s.get_1().toString())
                        .deviceId(s.get_2())
                        .deviceInfo(s.get_3())
                        .createdAt(s.get_4())
                        .build())
                .toList();
    }

    public void deleteSession(String sessionId) {
        var t = SessionTable.$;
        sqlClient.createDelete(t)
                .where(t.sessionId().eq(sessionId))
                .execute();
    }

    public void deleteAllSessions(String userId, boolean deleteBiometrics) {
        var t = SessionTable.$;
        sqlClient.createDelete(t)
                .where(t.userId().eq(UUID.fromString(userId)))
                .execute();

        if (!deleteBiometrics)
            return;

        var bt = BiometricTable.$;
        sqlClient.createDelete(bt)
                .where(bt.userId().eq(UUID.fromString(userId)))
                .execute();
    }

    public void deleteOtherSessions(String userId, String sessionId, boolean deleteBiometrics) {
        String deviceId = null;
        if (deleteBiometrics)
            deviceId = getDeviceId(sessionId);

        var t = SessionTable.$;
        sqlClient.createDelete(t)
                .where(Predicate.and(
                        t.userId().eq(UUID.fromString(userId)),
                        t.sessionId().ne(sessionId)
                ))
                .execute();

        if (deviceId == null)
            return;

        var bt = BiometricTable.$;
        sqlClient.createDelete(bt)
                .where(Predicate.and(
                        bt.userId().eq(UUID.fromString(userId)),
                        bt.deviceId().ne(deviceId)
                ))
                .execute();
    }
}
