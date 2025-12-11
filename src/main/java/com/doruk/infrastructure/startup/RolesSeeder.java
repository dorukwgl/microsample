package com.doruk.infrastructure.startup;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;

@Singleton
@Requires(env = "setup")
@RequiredArgsConstructor
@Getter
public class RolesSeeder {
    private final JSqlClient client;

    @EventListener
    public void seedRoles(StartupEvent event) {
        System.out.println("Roles seeded");
    }
}
