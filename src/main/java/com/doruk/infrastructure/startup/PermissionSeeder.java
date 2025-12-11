package com.doruk.infrastructure.startup;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;

@Singleton
@Requires(env = "setup")
@RequiredArgsConstructor
public class PermissionSeeder {
    private final JSqlClient sqlClient;

    @EventListener
    public void seedPermissions(StartupEvent event) {
        System.out.println("Permissions seeded");
    }
}
