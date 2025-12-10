package com.doruk.infrastructure.config;

import io.micronaut.context.annotation.Factory;

import jakarta.inject.Singleton;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.dialect.PostgresDialect;
import org.babyfish.jimmer.sql.runtime.ConnectionManager;

@Factory
public class JimmerConfig {
    @Singleton
    public JSqlClient jSqlClient(BasicR2dbcProperties basicR2dbcProperties) {
        ConnectionManager connectionManager = ConnectionManager.singleConnectionManager(r2dbcOperations);
        return JSqlClient.newBuilder()
                // REQUIRED for Postgres
                .setDialect(new PostgresDialect())

                // Use R2DBC executor
                .setExecutor(new R2dbcExecutor(r2dbcOperations))

                // ---- Recommended Configs ----

                // SQL logging (you want this)
                .setSqlLogger((sql, args, ctx) -> {
                    System.out.println("🔵 SQL: " + sql);
                })

                // Pretty SQL formatting (debugging)
                .setPrettySql(true)

                // Default enum strategy = NAME like your YAML
                .setDefaultEnumStrategy(EnumStrategy.NAME)

                // Enforce immutable entity relationships (recommended)
                .setBlockAssociationMutation(true)

                // Enable automatic identifier generation (UUIDV7 etc)
                .setIdGenerator(IdGenerator.UUID)

                // Optional: register scalar providers (e.g. JSONB)
                //.addScalarProvider(new JsonNodeScalarProvider())

                // Future: Draft interceptors (for auditing)
                //.addDraftInterceptor(new AuditDraftInterceptor())

                .build();
    }
}
