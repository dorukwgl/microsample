package com.doruk.infrastructure.startup;

import com.doruk.jooq.tables.Permissions;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.time.OffsetDateTime;

@Singleton
@Requires(env = "setup")
@RequiredArgsConstructor
public class PermissionSeeder {
    private final DSLContext dsl;

    public void seedPermissions() {
        var p = Permissions.PERMISSIONS;

        dsl.update(p)
                .set(p.DELETED_AT, OffsetDateTime.now())
                .where(p.NAME.eq("DICTATOR_PERMISSION"))
                .execute();

        System.out.println("Permissions seeded...");
    }
}
