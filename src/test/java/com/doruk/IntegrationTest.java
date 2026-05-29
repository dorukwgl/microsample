package com.doruk;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTest implements TestPropertyProvider {

    @Inject
    EmbeddedApplication<?> application;

    private static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:18-alpine"))
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

    private static final GenericContainer<?> redis =
            new GenericContainer<>(DockerImageName.parse("redis:8.6-alpine"))
                    .withExposedPorts(6379);

    private static final GenericContainer<?> nats =
            new GenericContainer<>(DockerImageName.parse("nats:2.12.6-alpine"))
                    .withExposedPorts(4222);

    static {
        postgres.start();
        redis.start();
        nats.start();
    }

    @Override
    public Map<String, String> getProperties() {
        return Map.of(
                "datasources.default.url", postgres.getJdbcUrl(),
                "datasources.default.username", postgres.getUsername(),
                "datasources.default.password", postgres.getPassword(),
                "redis.uri", "redis://" + redis.getHost() + ":" + redis.getMappedPort(6379),
                "nats.default.addresses", "nats://" + nats.getHost() + ":" + nats.getMappedPort(4222)
        );
    }

    @Test
    void testContextStarts() {
        assertTrue(application.isRunning());
    }
}
