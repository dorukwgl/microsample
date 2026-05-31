package com.doruk.infrastructure.startup;

import com.doruk.infrastructure.util.Constants;
import com.doruk.jooq.tables.Roles;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.time.OffsetDateTime;

@Singleton
@Requires(env = "setup")
@RequiredArgsConstructor
public class RolesSeeder {
    private final DSLContext dsl;

    public void seedRoles() {
        var r = Roles.ROLES;

        dsl.insertInto(r)
                .set(r.NAME, Constants.DICTATOR_ROLE)
                .set(r.DELETED_AT, OffsetDateTime.now())
                .onConflictDoNothing()
                .execute();

        dsl.insertInto(r)
                .set(r.NAME, Constants.SYS_ADMIN_ROLE)
                .onConflictDoNothing()
                .execute();

        dsl.insertInto(r)
                .set(r.NAME, "USER")
                .onConflictDoNothing()
                .execute();

        System.out.println("Roles seeded...");
    }
}
