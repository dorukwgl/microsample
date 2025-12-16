package com.doruk.infrastructure.startup;

import com.doruk.infrastructure.persistence.entity.Role;
import com.doruk.infrastructure.persistence.entity.RoleDraft;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;

import java.time.LocalDateTime;
import java.util.List;

@Singleton
@Requires(env = "setup")
@RequiredArgsConstructor
public class RolesSeeder {
    private final JSqlClient client;

    public void seedRoles() {
        Role role1 = RoleDraft.$.produce(r -> r.setName("GHOST").setDeletedAt(LocalDateTime.now()));
        Role role2 = RoleDraft.$.produce(r -> r.setName("DICTATOR").setDeletedAt(LocalDateTime.now()));
        Role role3 = RoleDraft.$.produce(r -> r.setName("SYS_ADMIN").setDeletedAt(null));
        Role role4 = RoleDraft.$.produce(r -> r.setName("USER").setDeletedAt(null));
        client.saveEntitiesCommand(List.of(role1, role2, role3, role4))
                .execute();

        System.out.println("Roles seeded...");
    }
}
