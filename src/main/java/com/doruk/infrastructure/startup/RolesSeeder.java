package com.doruk.infrastructure.startup;

import com.doruk.infrastructure.persistence.entity.*;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.mutation.SaveMode;

import java.time.LocalDateTime;

@Singleton
@Requires(env = "setup")
@RequiredArgsConstructor
@Getter
public class RolesSeeder {
    private final JSqlClient client;

    @EventListener
    public void seedRoles(StartupEvent event) {

        Role role1 = RoleDraft.$.produce(r -> r.setName("GHOST").setDeletedAt(LocalDateTime.now()));
        Role role2 = RoleDraft.$.produce(r -> r.setName("SYSTEM_ADMIN").setDeletedAt(LocalDateTime.now()));

        client.saveCommand(role1).execute();
        client.saveCommand(role2).execute();


        System.out.println("Roles seeded");
    }
}
