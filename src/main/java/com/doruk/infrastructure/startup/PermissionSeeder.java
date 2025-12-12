package com.doruk.infrastructure.startup;

import com.doruk.infrastructure.persistence.entity.PermissionDraft;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;

import java.time.LocalDateTime;
import java.util.List;

@Singleton
@Requires(env = "setup")
@RequiredArgsConstructor
public class PermissionSeeder {
    private final JSqlClient sqlClient;

    public void seedPermissions() {
        var per1 = PermissionDraft.$.produce(p -> p.setName("GHOST_PERMISSION").setDeletedAt(LocalDateTime.now()));
        var per2 = PermissionDraft.$.produce(p -> p.setName("DICTATOR_PERMISSION").setDeletedAt(LocalDateTime.now()));
        sqlClient.saveEntitiesCommand(List.of(per1, per2)).execute();

        System.out.println("Permissions seeded...");
    }
}
