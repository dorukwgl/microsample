package com.doruk.infrastructure.startup;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Singleton
//@Requires(env = "setup")
@Getter
public class RolesSeeder {
    private String test = "this is lombok, is working.";

    @EventListener
    public void seedRoles(StartupEvent event) {
        System.out.println("Roles seeded");
    }
}
