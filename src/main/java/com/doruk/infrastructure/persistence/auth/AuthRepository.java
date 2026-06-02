package com.doruk.infrastructure.persistence.auth;

import com.doruk.application.app.auth.dto.AuthDto;
import com.doruk.application.app.auth.dto.BiometricDto;
import com.doruk.application.app.auth.dto.GoogleLinkDto;
import com.doruk.application.app.auth.dto.SessionDto;
import com.doruk.domain.shared.enums.MultiAuthType;
import com.doruk.domain.shared.enums.Permissions;
import com.doruk.infrastructure.persistence.auth.mapper.BiometricMapper;
import com.doruk.infrastructure.persistence.auth.mapper.SessionMapper;
import com.doruk.infrastructure.persistence.entity.*;
import com.doruk.infrastructure.util.Constants;
import com.doruk.infrastructure.util.Pair;
import com.doruk.jooq.tables.Biometrics;
import com.doruk.jooq.tables.RolePermissions;
import com.doruk.jooq.tables.UserRoles;
import com.doruk.jooq.tables.Users;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.time.OffsetDateTime;
import java.util.*;

@Singleton
@RequiredArgsConstructor
public class AuthRepository {
    private final JSqlClient sqlClient;
    private final SessionMapper sessionMapper;
    private final BiometricMapper biometricMapper;
    private final DSLContext dsl;

    private Optional<String> getDeviceId(String sessionId) {
        return sqlClient.createQuery(SessionTable.$)
                .where(SessionTable.$.sessionId().eq(sessionId))
                .select(SessionTable.$.deviceId())
                .execute()
                .stream()
                .findFirst();
    }

    private Optional<AuthDto> findUserWithPermissions(org.jooq.Condition whereCondition) {
        var u = Users.USERS;
        var r = UserRoles.USER_ROLES;
        var p = RolePermissions.ROLE_PERMISSIONS;
        var rec = dsl.select(
                        u.ID,
                        u.USERNAME,
                        u.PASSWORD,
                        u.PHONE,
                        u.EMAIL,
                        u.IS_EMAIL_VERIFIED,
                        u.IS_PHONE_VERIFIED,
                        u.MULTI_FACTOR_AUTH,
                        // fetch list of permissions
                        DSL.multiset(
                                dsl.selectDistinct(p.PERMISSION_NAME)
                                        .from(r)
                                        .join(p).on(p.ROLE_NAME.eq(r.NAME))
                                        .where(r.USER_ID.eq(u.ID))
                        ).convertFrom(rs -> new HashSet<>(rs.map(perm -> Permissions.valueOf(perm.value1()))))
                )
                .from(u)
                .where(whereCondition)
                .fetchOne(rs -> AuthDto.builder()
                        .id(rs.value1().toString())
                        .username(rs.value2())
                        .password(rs.value3())
                        .phone(rs.value4())
                        .email(rs.value5())
                        .emailVerified(rs.value6())
                        .phoneVerified(rs.value7())
                        .multiFactorAuth(rs.value8())
                        .permissions(rs.value9())
                        .build()
                );

        return Optional.ofNullable(rec);
    }

    public Optional<AuthDto> findByUsernameOrEmail(String field) {
        var u = Users.USERS;
        return this.findUserWithPermissions(u.USERNAME.eq(field).or(u.EMAIL.eq(field)));
    }

    public Optional<AuthDto> findByUserId(String userId) {
        var u = Users.USERS;
        return this.findUserWithPermissions(u.ID.eq(UUID.fromString(userId)));
    }

    public void createSession(String userId,
                              String sessionId,
                              Set<Permissions> permissions,
                              OffsetDateTime expiration,
                              Optional<String> deviceId,
                              Optional<String> deviceInfo) {

        // delete any existing session for the same user+device (one active session per device)
        deviceId.ifPresent(did -> {
            var t = SessionTable.$;
            sqlClient.createDelete(t)
                    .where(Predicate.and(
                            t.userId().eq(UUID.fromString(userId)),
                            t.deviceId().eq(did)))
                    .execute();
        });

        sqlClient.saveCommand(SessionDraft.$.produce(s ->
                s.setUserId(UUID.fromString(userId))
                        .setSessionId(sessionId)
                        .setCachedPermissions(permissions.stream().map(Permissions::id).toList())
                        .setExpiresAt(expiration)
                        .setDeviceId(deviceId.orElse(null))
                        .setDeviceInfo(deviceInfo.orElse(null)))
        ).execute();
    }

    public Optional<SessionDto> getActiveSession(String sessionId) {
        var t = SessionTable.$;
        return sqlClient.createQuery(t)
                .where(Predicate.and(t.sessionId().eq(sessionId),
                        t.expiresAt().gt(OffsetDateTime.now())))
                .select(t)
                .execute()
                .stream()
                .map(sessionMapper::toDto)
                .findFirst();
    }

    public List<SessionDto> getActiveDevices(String userId) {
        var t = SessionTable.$;
        return sqlClient.createQuery(t)
                .where(Predicate.and(
                                t.userId().eq(UUID.fromString(userId))),
                        t.expiresAt().gt(OffsetDateTime.now())
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

    /**
     * Deletes the session AND nullifies the notification_device_id in user_devices
     * so push notifications stop, but biometric link is preserved for re-login.
     */
    public void deleteSessionAndDevice(String sessionId) {
        var t = SessionTable.$;
        var row = sqlClient.createQuery(t)
                .where(t.sessionId().eq(sessionId))
                .select(t.userId(), t.deviceId())
                .execute()
                .stream()
                .findFirst()
                .orElse(null);

        sqlClient.createDelete(t)
                .where(t.sessionId().eq(sessionId))
                .execute();

        if (row != null && row.get_2() != null)
            nullifyNotificationDevice(row.get_1().toString(), row.get_2());
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
        var currentNotifId = getDeviceId(sessionId);

        // Look up the biometric hardware ID for the current device to preserve it
        String bioDeviceId = null;
        if (deleteBiometrics) {
            if (currentNotifId.isPresent()) {
                bioDeviceId = dsl.select(DSL.field("bio_device_id", String.class))
                        .from(DSL.table("user_devices"))
                        .where(DSL.field("user_id", UUID.class).eq(UUID.fromString(userId)))
                        .and(DSL.field("notification_device_id", String.class).eq(currentNotifId.get()))
                        .fetchOne(rs -> rs.get(0, String.class));
            }
        }

        var t = SessionTable.$;
        sqlClient.createDelete(t)
                .where(Predicate.and(
                        t.userId().eq(UUID.fromString(userId)),
                        t.sessionId().ne(sessionId)
                ))
                .execute();

        // Nullify notification_device_id for other devices (stop push to logged-out devices).
        // Preserve the current device's notification ID.
        var tbl = DSL.table("user_devices");
        var uidField = DSL.field("user_id", UUID.class);
        var notifField = DSL.field("notification_device_id", String.class);
        if (currentNotifId.isPresent()) {
            dsl.update(tbl)
                    .setNull(notifField)
                    .where(uidField.eq(UUID.fromString(userId)))
                    .and(notifField.ne(currentNotifId.get()))
                    .execute();
        } else {
            dsl.update(tbl)
                    .setNull(notifField)
                    .where(uidField.eq(UUID.fromString(userId)))
                    .execute();
        }

        // Delete biometrics for other devices (or all if current has no biometric)
        if (deleteBiometrics) {
            var bt = BiometricTable.$;
            if (bioDeviceId != null) {
                sqlClient.createDelete(bt)
                        .where(Predicate.and(
                                bt.userId().eq(UUID.fromString(userId)),
                                bt.deviceId().ne(bioDeviceId)
                        ))
                        .execute();
            } else {
                // No biometric on current device → delete all biometrics for user
                sqlClient.createDelete(bt)
                        .where(bt.userId().eq(UUID.fromString(userId)))
                        .execute();
            }
        }
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
     * The first element is a String representing the email address,
     * and the second element is a boolean indicating whether the
     * email has been verified.
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
     *
     * @param userId
     * @return a Pair containing the user's phone and verification status.
     * The first element is a String representing the phone number,
     * and the second element is a boolean indicating whether the
     * phone has been verified.
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

    public void createOrUpdateBiometrics(String deviceId, String userId, byte[] publicKey) {
        var b = Biometrics.BIOMETRICS;
        dsl.insertInto(b)
                .set(b.DEVICE_ID, deviceId)
                .set(b.USER_ID, UUID.fromString(userId))
                .set(b.PUBLIC_KEY, publicKey)
                .onConflict(b.DEVICE_ID)
                .doUpdate()
                .set(b.USER_ID, DSL.excluded(b.USER_ID))
                .set(b.PUBLIC_KEY, DSL.excluded(b.PUBLIC_KEY))
                .execute();
    }

    public Optional<BiometricDto> getActiveBiometric(String deviceId) {
        var t = BiometricTable.$;
        return sqlClient.createQuery(t)
                .where(Predicate.and(
                        t.deviceId().eq(deviceId),
                        t.lastUsedAt().gt(OffsetDateTime.now().minusDays(Constants.BIOMETRIC_MAX_STALE_DAYS))))
                .select(t.fetch(BiometricFetcher.$.allScalarFields()))
                .execute()
                .stream()
                .map(biometricMapper::toDto)
                .findFirst();
    }

    public void updateLastUsedBiometric(String deviceId) {
        var t = BiometricTable.$;
        sqlClient.createUpdate(t)
                .where(t.deviceId().eq(deviceId))
                .set(t.lastUsedAt(), OffsetDateTime.now())
                .execute();
    }

    public void removeBiometric(String deviceId) {
        var t = BiometricTable.$;
        sqlClient.createDelete(t)
                .where(t.deviceId().eq(deviceId))
                .execute();
    }

    // ── user device management ───────────────────────────────────────

    public void upsertUserDevice(String userId, String notificationDeviceId,
                                 String bioDeviceId, String deviceInfo) {
        var tbl = DSL.table("user_devices");
        var uidField = DSL.field("user_id", UUID.class);
        var notifField = DSL.field("notification_device_id", String.class);
        var infoField = DSL.field("device_info", String.class);
        var loginField = DSL.field("last_login_at", OffsetDateTime.class);

        if (bioDeviceId != null) {
            var bioField = DSL.field("bio_device_id", String.class);
            dsl.insertInto(tbl)
                    .set(uidField, UUID.fromString(userId))
                    .set(notifField, notificationDeviceId)
                    .set(bioField, bioDeviceId)
                    .set(infoField, deviceInfo)
                    .set(loginField, OffsetDateTime.now())
                    .onConflict(uidField, notifField)
                    .doUpdate()
                    .set(bioField, bioDeviceId)
                    .set(infoField, deviceInfo)
                    .set(loginField, OffsetDateTime.now())
                    .execute();
        } else {
            // Don't overwrite bio_device_id to null — preserves biometric link
            dsl.insertInto(tbl)
                    .set(uidField, UUID.fromString(userId))
                    .set(notifField, notificationDeviceId)
                    .set(infoField, deviceInfo)
                    .set(loginField, OffsetDateTime.now())
                    .onConflict(uidField, notifField)
                    .doUpdate()
                    .set(infoField, deviceInfo)
                    .set(loginField, OffsetDateTime.now())
                    .execute();
        }
    }

    public void updateDeviceNotificationId(String userId, String oldNotifId, String newNotifId) {
        var tbl = DSL.table("user_devices");
        dsl.update(tbl)
                .set(DSL.field("notification_device_id", String.class), newNotifId)
                .set(DSL.field("last_login_at", OffsetDateTime.class), OffsetDateTime.now())
                .where(DSL.field("user_id", UUID.class).eq(UUID.fromString(userId)))
                .and(DSL.field("notification_device_id", String.class).eq(oldNotifId))
                .execute();
    }

    public void nullifyNotificationDevice(String userId, String notificationDeviceId) {
        var tbl = DSL.table("user_devices");
        dsl.update(tbl)
                .setNull(DSL.field("notification_device_id", String.class))
                .where(DSL.field("user_id", UUID.class).eq(UUID.fromString(userId)))
                .and(DSL.field("notification_device_id", String.class).eq(notificationDeviceId))
                .execute();
    }

    public void nullifyAllNotificationDevices(String userId) {
        var tbl = DSL.table("user_devices");
        dsl.update(tbl)
                .setNull(DSL.field("notification_device_id", String.class))
                .where(DSL.field("user_id", UUID.class).eq(UUID.fromString(userId)))
                .execute();
    }

    public void deleteAllUserDevices(String userId) {
        dsl.deleteFrom(DSL.table("user_devices"))
                .where(DSL.field("user_id", UUID.class).eq(UUID.fromString(userId)))
                .execute();
    }

    public Optional<GoogleLinkDto> findUserByGoogleSubOrEmail(String googleSub, String email) {
        var t = UserTable.$;
        return sqlClient.createQuery(t)
                .where(Predicate.or(
                        t.googleSub().eq(googleSub),
                        t.email().eq(email)
                ))
                .select(t.id(), t.googleSub(), t.email())
                .execute()
                .stream()
                .map(r -> GoogleLinkDto.builder()
                        .userId(r.get_1())
                        .googleSub(r.get_2())
                        .email(r.get_3())
                        .build())
                .findFirst();
    }

    public GoogleLinkDto registerUserViaGoogleAuth(String email, String googleSub, String name) {
        var draft = UserDraft.$.produce(d -> d
                .setEmail(email)
                .setGoogleSub(googleSub)
                .setEmailVerified(true)
        );
        var pDraft = UserProfileDraft.$.produce(d -> d.setFullName(name));

        var saved = sqlClient.transaction(() -> {
            sqlClient.saveCommand(pDraft).execute();
            var usr = sqlClient.saveCommand(draft).execute().getModifiedEntity();

            // assign default role
            sqlClient.saveCommand(UserDraft.$.produce(d ->
                            d.setRoles(List.of(RoleDraft.$.produce(r -> r.setName("USER"))))))
                    .execute();

            return usr;
        });

        return GoogleLinkDto.builder()
                .userId(saved.id())
                .googleSub(googleSub)
                .email(email)
                .build();
    }

    public void linkUserToGoogleAuth(UUID userId, String googleSub) {
        var t = UserTable.$;
        sqlClient.createUpdate(t)
                .set(t.googleSub(), googleSub)
                .set(t.emailVerified(), true)
                .where(t.id().eq(userId))
                .execute();
    }

    public void updateEmailFromGoogleAuth(UUID userId, String email) {
        var t = Users.USERS;
        dsl.update(t)
                .set(t.EMAIL, email)
                .where(t.ID.eq(userId))
                .execute();
    }

    // set for the first time (if done google sign in)
    // WHERE password IS NULL makes this atomic even under concurrent requests
    public boolean setPasswordIfNotSet(UUID userId, String hashedPassword) {
        var t = UserTable.$;
        return sqlClient.createUpdate(t)
                .set(t.password(), hashedPassword)
                .where(t.id().eq(userId), t.password().isNull())
                .execute() > 0;
    }
}
