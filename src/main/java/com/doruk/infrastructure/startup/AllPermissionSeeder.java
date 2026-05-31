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
public class AllPermissionSeeder {
    private final DSLContext dsl;

    public void seedAllPermissions() {
        var p = Permissions.PERMISSIONS;

        for (var permission : com.doruk.domain.shared.enums.Permissions.values()) {
            dsl.insertInto(p)
                    .set(p.NAME, permission.name())
                    .set(p.DELETED_AT, (OffsetDateTime) null)
                    .onConflictDoNothing()
                    .execute();
        }

        System.out.println("All permissions from Permissions enum seeded...");
    }
}
