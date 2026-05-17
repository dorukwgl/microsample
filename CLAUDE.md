Follow `/home/doruk/.claude/CLAUDE.md` for all global Java/Micronaut/DDD rules.

# Project: Kendra

- **Main class**: `com.doruk.Application`
- **Java 26**, Micronaut 4.6.1, Gradle Kotlin DSL
- **Stack**: PostgreSQL, Flyway, Jimmer 0.10.6, JOOQ 3.21.1, NATS, Redis, S3
- **Package**: `com.doruk` (presentation/application/domain/infrastructure)

## Key Annotations

```
-Amicronaut.processing.group=com.doruk
-Amicronaut.processing.module=kendra
```

## Build Commands

| Task | Command |
|---|---|
| Run | `./gradlew run` |
| Build | `./gradlew assemble` |
| Test | `./gradlew test` |
| JOOQ gen | `./gradlew generateJooq` |
| Flyway migrate | `./gradlew flywayMigrate` |
