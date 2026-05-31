package com.doruk.infrastructure.startup;

import com.doruk.application.security.PasswordEncoder;
import com.doruk.domain.shared.enums.MultiAuthType;
import com.doruk.infrastructure.util.Constants;
import com.doruk.jooq.tables.UserRoles;
import com.doruk.jooq.tables.Users;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

@Singleton
@Requires(env = "setup")
@RequiredArgsConstructor
public class UserSeeder {
    private final DSLContext dsl;
    private final PasswordEncoder hasher;

    public void seedUsers() {
        var u = Users.USERS;
        var ur = UserRoles.USER_ROLES;

        // dictator
        var dictator = dsl.insertInto(u)
                .set(u.USERNAME, "dorukwgl")
                .set(u.EMAIL, "dorukwagle@gmail.com")
                .set(u.PASSWORD, hasher.encode("dorukwgl@ihbibicdff"))
                .set(u.PHONE, "9829293466")
                .set(u.IS_EMAIL_VERIFIED, true)
                .set(u.IS_PHONE_VERIFIED, true)
                .set(u.MULTI_FACTOR_AUTH, MultiAuthType.EMAIL)
                .onConflict(u.EMAIL)
                .doNothing()
                .returning(u.ID)
                .fetchOne();

        if (dictator != null) {
            dsl.insertInto(ur)
                    .set(ur.USER_ID, dictator.getId())
                    .set(ur.NAME, Constants.DICTATOR_ROLE)
                    .execute();
        }

        // sys admin
        var sysAdmin = dsl.insertInto(u)
                .set(u.USERNAME, "doruk")
                .set(u.EMAIL, "chrishdev.chd@gmail.com")
                .set(u.PASSWORD, hasher.encode("chd@ihbibicdff"))
                .set(u.PHONE, "9829293466")
                .set(u.IS_EMAIL_VERIFIED, true)
                .set(u.IS_PHONE_VERIFIED, true)
                .set(u.MULTI_FACTOR_AUTH, MultiAuthType.NONE)
                .onConflict(u.EMAIL)
                .doNothing()
                .returning(u.ID)
                .fetchOne();

        if (sysAdmin != null) {
            dsl.insertInto(ur)
                    .set(ur.USER_ID, sysAdmin.getId())
                    .set(ur.NAME, Constants.SYS_ADMIN_ROLE)
                    .execute();
        }

        // regular user
        var user = dsl.insertInto(u)
                .set(u.USERNAME, "testuser")
                .set(u.EMAIL, "user@gmail.com")
                .set(u.PASSWORD, hasher.encode("password"))
                .set(u.PHONE, "9829293466")
                .set(u.IS_PHONE_VERIFIED, true)
                .set(u.MULTI_FACTOR_AUTH, MultiAuthType.NONE)
                .onConflict(u.EMAIL)
                .doNothing()
                .returning(u.ID)
                .fetchOne();

        if (user != null) {
            dsl.insertInto(ur)
                    .set(ur.USER_ID, user.getId())
                    .set(ur.NAME, "USER")
                    .execute();
        }

        System.out.println("Users seeded...");
    }
}
