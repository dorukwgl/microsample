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
import org.babyfish.jimmer.sql.runtime.LogicalDeletedBehavior;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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

    public Optional<AuthDto> findByUserId(String userId) {
        var t = UserTableEx.$;
        var dt = sqlClient.filters(cfg -> cfg.setBehavior(LogicalDeletedBehavior.IGNORED))
                .createQuery(UserTable.$)
                .where(t.id().eq(UUID.fromString(userId)))
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

    public String getUserPassword(String userId) {
        var t = UserTable.$;
        return sqlClient.createQuery(t)
                .where(t.id().eq(UUID.fromString(userId)))
                .select(t.password())
                .execute()
                .getFirst();
    }

    public void updatePassword(String userId, String password) {
        var t = UserTable.$;
        sqlClient.createUpdate(t)
                .where(t.id().eq(UUID.fromString(userId)))
                .set(t.password(), password)
                .execute();
    }

    public void updateEmail(String userId, String email, boolean verified) {
        var t = UserTable.$;
        sqlClient.createUpdate(t)
                .where(t.id().eq(UUID.fromString(userId)))
                .set(t.email(), email)
                .set(t.emailVerified(), verified)
                .execute();
    }

    public void updatePhone(String userId, String phone, boolean verified) {
        var t = UserTable.$;
        sqlClient.createUpdate(t)
                .where(t.id().eq(UUID.fromString(userId)))
                .set(t.phone(), phone)
                .set(t.phoneVerified(), verified)
                .execute();
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

    /**
     * Retrieves the email and email verification status of a user.
     *
     * @param userId the ID of the user
     * @return a Pair containing the user's email and verification status.
     *         The first element is a String representing the email address,
     *         and the second element is a boolean indicating whether the
     *         email has been verified.
     */
    public Pair<String, Boolean> getUserEmail(String userId) {
        var t = UserTable.$;
        var d = sqlClient.createQuery(t)
                .where(t.id().eq(UUID.fromString(userId)))
                .select(t.email(), t.emailVerified())
                .execute()
                .getFirst();
        return new Pair<>(d.get_1(), d.get_2());
    }

    /**
     * Retrieves the phone and phone verification status of a user
     * @param userId
     * @return a Pair containing the user's phone and verification status.
     *         The first element is a String representing the phone number,
     *         and the second element is a boolean indicating whether the
     *         phone has been verified.
     */
    public Pair<String, Boolean> getUserPhone(String userId) {
        var t = UserTable.$;
        var d = sqlClient.createQuery(t)
                .where(t.id().eq(UUID.fromString(userId)))
                .select(t.phone(), t.phoneVerified())
                .execute()
                .getFirst();
        return new Pair<>(d.get_1(), d.get_2());
    }

    public void enableMfa(String userId, MultiAuthType authType) {
        var t = UserTable.$;
        sqlClient.createUpdate(t)
                .where(t.id().eq(UUID.fromString(userId)))
                .set(t.multiFactorAuth(), authType)
                .execute();
    }

    public void disableMfa(String userId) {
        var t = UserTable.$;
        sqlClient.createUpdate(t)
                .where(t.id().eq(UUID.fromString(userId)))
                .set(t.multiFactorAuth(), MultiAuthType.NONE)
                .execute();
    }
}
