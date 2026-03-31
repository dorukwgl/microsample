# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and Development Commands

```bash
# Build the project
./gradlew build

# Run the application (dev environment by default)
./gradlew run

# Run tests
./gradlew test

# Run a specific test class
./gradlew test --tests LedgerTest

# Generate JOOQ code from database schema (runs automatically before compileJava)
./gradlew generateJooq

# Run database migrations
./gradlew flywayMigrate

# Create production JAR with shadow plugin
./gradlew assemble

# Build with Swagger/OpenAPI documentation
./gradlew -Pdocs build
```

## Environment Setup

The application uses environment variables for configuration. Required variables (see `application-dev.yml`):

- `DATASOURCE_URL` - PostgreSQL connection string
- `DATASOURCE_USERNAME` - Database user
- `DATASOURCE_PASSWORD` - Database password
- `APP_ID` - Application identifier for JWT
- `SESSION_EXPIRATION` - Session expiration in days (default: 7)
- `LEDGER_PORT` - Server port
- `NATS_PASSWORD` - NATS password
- `BREVO_API_KEY`, `BREVO_SENDER_EMAIL`, `BREVO_SENDER_NAME` - Email service config
- `R2_ACCESS_KEY`, `R2_SECRET_KEY` - Cloudflare R2/S3 storage

Run with dev environment: `./gradlew run -Dmicronaut.environments=dev,setup`
(`setup` env enables seeders for initial data population)

## Architecture Overview

The project follows **clean/hexagonal architecture** with distinct layers:

### Layer Structure

**`presentation/`** - REST API controllers
- Auth endpoints (`presentation/auth/controller/`)
- User management (`presentation/users/controller/`)
- File handling (`presentation/files/`)
- System admin (`presentation/system/controller/`)

**`application/`** - Use case orchestration
- Application services (`application/app/*/service/`)
- DTOs for external communication (`application/app/*/dto/`)
- Domain events and interfaces (`application/events/`, `application/interfaces/`)
- Security (`application/security/`)

**`domain/`** - Core business logic
- Domain entities and value objects
- Business policies (`domain/policies/`)
- Domain-specific exceptions
- Shared enums and types (`domain/shared/enums/`)

**`infrastructure/`** - External concerns
- Database access (`infrastructure/persistence/`)
- External service clients (Brevo, Sociair, JWKS)
- Messaging (NATS)
- File storage (S3/R2)
- Startup seeders (`infrastructure/startup/`)

### Key Technologies

- **Framework**: Micronaut 4.10+ with Netty
- **Database**: PostgreSQL with Hikari pool, Flyway migrations
- **ORM**: Jimmer for entity mapping + JOOQ for type-safe queries
- **Cache**: Redis for OTP transactions, session data
- **Messaging**: NATS for event-driven architecture
- **Authentication**: JWT with JWKS, RBAC with permissions
- **Storage**: S3-compatible (Cloudflare R2)

### Database

**Schema**: `main` schema in PostgreSQL

**Migrations**: Located in `src/main/resources/db/migration/`
- Run automatically on startup via Flyway
- Manual migration: `./gradlew flywayMigrate`

**Code Generation**:
- JOOQ generates type-safe SQL classes from schema to `build/generated-src/jooq/`
- Run `./gradlew generateJooq` to regenerate

**Key Tables**:
- `users` - User accounts
- `roles` - Role definitions
- `role_permissions` - Permission assignments
- `user_roles` - User-role relationships
- `sessions` - Login sessions with cached permissions
- `biometrics` - Biometric auth data

### Authentication & Authorization

**Authentication Flow**:
1. Login via `/auth/login` → JWT access token + refresh token (cookie)
2. Access tokens in Authorization header, refresh token in cookie
3. MFA supported (email/phone OTP) with transaction-based flow
4. Biometric authentication via public key verification

**Authorization Model**:
- Role-based access control (RBAC)
- Permissions defined in `domain/shared/enums/Permissions.java`
- Use `@Secured` annotation or inject `Authentication` for auth checks
- Custom `PermissionEvaluator` for complex authorization logic

**OTP Transactions**:
- Stored in Redis with namespace prefixes
- Cooldown and attempt limiting enforced
- Magic links supported for password reset and email verification

### Event-Driven Architecture

**NATS Messaging**:
- Events published via `EventPublisher` interface
- Events: `OtpDeliveryEvent`, file upload events, etc.
- Consumers subscribe to NATS topics

**Memory Storage**:
- Redis used via `MemoryStorage` interface
- Namespaced keys via `KeyNamespace` utility
- Used for OTP transactions, cooldown tracking, magic links

### Code Patterns

**Repository Pattern**:
- Repositories in `infrastructure/persistence/*/`
- Use Jimmer for CRUD operations
- Use JOOQ DSL for complex queries with joins
- Example: `AuthRepository.findUserWithPermissions()` using JOOQ multiset for eager loading

**DTO Mappers**:
- Separate mapper classes (e.g., `SessionMapper`, `UserResponseMapper`)
- Converts between domain entities and DTOs
- Keep business logic out of mappers

**Seeders**:
- Run on startup in `setup` environment
- Located in `infrastructure/startup/`
- Populate initial roles, permissions, admin users

**Exceptions**:
- Application exceptions in `application/exception/`
- Domain exceptions in `domain/exception/`
- Infrastructure exception handlers map to HTTP responses

## Common Operations

**Add new API endpoint**:
1. Add method to appropriate service in `application/app/*/service/`
2. Add controller method in `presentation/*/controller/`
3. If needed, add DTO in `application/app/*/dto/`
4. Add repository method in `infrastructure/persistence/*/` if database access needed

**Database schema change**:
1. Create new migration file in `src/main/resources/db/migration/`
2. Run `./gradlew flywayMigrate`
3. Update corresponding entity in `infrastructure/persistence/entity/`
4. Run `./gradlew generateJooq` to regenerate JOOQ code

**Add new permission**:
1. Add enum to `domain/shared/enums/Permissions.java`
2. Create migration to add permission to `role_permissions` table
3. Use `@Secured(Permissions.YOUR_PERMISSION)` or check via `PermissionEvaluator`