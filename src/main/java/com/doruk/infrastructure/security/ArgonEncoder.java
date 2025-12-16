package com.doruk.infrastructure.security;

import com.doruk.application.security.PasswordEncoder;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;
import io.micronaut.context.annotation.Type;
import jakarta.inject.Singleton;

@Singleton
@Type(PasswordEncoder.class)
public class ArgonEncoder implements PasswordEncoder {
    private final Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);

    @Override
    public String encode(String password) {
        // iterations, memory: 64MB, parallelism: 2
        return argon2.hash(11, 65536, 2, password.toCharArray());
    }

    @Override
    public boolean matches(String password, String encodedPassword) {
        return argon2.verify(encodedPassword, password.toCharArray());
    }
}
