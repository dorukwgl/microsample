package com.doruk.infrastructure.startup;

import com.doruk.application.security.PasswordEncoder;
import com.doruk.domain.shared.enums.MultiAuthType;
import com.doruk.infrastructure.persistence.entity.RoleDraft;
import com.doruk.infrastructure.persistence.entity.UserDraft;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;

import java.util.List;

@Singleton
@Requires(env = "setup")
@RequiredArgsConstructor
public class UserSeeder {
    private final JSqlClient client;
    private final PasswordEncoder hasher;

    public void seedUsers() {
        var ghost = UserDraft.$.produce(u -> u.setUsername("ghost")
                .setEmail("ghost@gmail.com")
                .setEmailVerified(true)
                .setPhoneVerified(true)
                .setPhone("9829293466")
                .setMultiFactorAuth(MultiAuthType.PHONE)
                .setRoles(List.of(RoleDraft.$.produce(r -> r.setName("GHOST"))))
                .setPassword(hasher.encode("ghost@ihbibicdff").block())
        );
        var dictator = UserDraft.$.produce(u -> u.setUsername("dorukwgl")
                .setEmail("dorukwgl@gmail.com")
                .setEmailVerified(true)
                .setPhoneVerified(true)
                .setPhone("9829293466")
                .setRoles(List.of(RoleDraft.$.produce(r -> r.setName("DICTATOR"))))
                .setMultiFactorAuth(MultiAuthType.NONE)
                .setPassword(hasher.encode("dorukwgl@ihbibicdff").block())
        );

        var sysAdmin = UserDraft.$.produce(u -> u.setUsername("doruk")
                .setEmail("doruk@gmail.com")
                .setEmailVerified(true)
                .setPhoneVerified(true)
                .setPhone("9829293466")
                .setRoles(List.of(RoleDraft.$.produce(r -> r.setName("SYS_ADMIN"))))
                .setMultiFactorAuth(MultiAuthType.NONE)
                .setPassword(hasher.encode("doruk@ihbibicdff").block())
        );

        var user = UserDraft.$.produce(u -> u.setUsername("testuser")
                .setEmail("user@gmail.com")
                .setPhoneVerified(true)
                .setPhoneVerified(true)
                .setPhone("9829293466")
                .setMultiFactorAuth(MultiAuthType.NONE)
                .setRoles(List.of(RoleDraft.$.produce(r -> r.setName("USER"))))
                .setPassword(hasher.encode("password").block())
        );

        client.saveEntitiesCommand(List.of(ghost, dictator, sysAdmin, user))
                .execute();

        System.out.println("Users seeded...");
    }
}
