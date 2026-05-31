package com.doruk.infrastructure.startup;

import com.doruk.domain.shared.enums.Permissions;
import com.doruk.infrastructure.util.Constants;
import com.doruk.jooq.tables.RolePermissions;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

@Singleton
@Requires(env = "setup")
@RequiredArgsConstructor
public class GrantsSeeder {
    private final DSLContext dsl;

    public void seedGrants() {
        var rp = RolePermissions.ROLE_PERMISSIONS;

        // SYS_ADMIN gets all permissions
        for (Permissions permission : Permissions.values()) {
            dsl.insertInto(rp)
                    .set(rp.ROLE_NAME, Constants.SYS_ADMIN_ROLE)
                    .set(rp.PERMISSION_NAME, permission.name())
                    .onConflictDoNothing()
                    .execute();
        }

        // DICTATOR gets only DICTATOR_PERMISSION
        dsl.insertInto(rp)
                .set(rp.ROLE_NAME, Constants.DICTATOR_ROLE)
                .set(rp.PERMISSION_NAME, "DICTATOR_PERMISSION")
                .onConflictDoNothing()
                .execute();

        System.out.println("Grants seeded...");
    }
}
