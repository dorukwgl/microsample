# Micronaut Base Project Extraction Plan

> **Goal**: Extract a reusable, production-grade base from the existing Micronaut Java project.
> Future projects copy this base and skip re-implementing auth, permissions, messaging, storage, etc.
>
> **Stack**: Java (Virtual Threads / Project Loom), Micronaut (latest), PostgreSQL, Redis, NATS, Jimmer, jOOQ
> **Philosophy**: No reactive streams, no reflection, pure VT Loom, pure Micronaut idioms.

---

## Pre-flight Checklist (Read Before Starting)

- Use **context7** to resolve all latest versions before touching any dependency.
- Never introduce reflection; use Micronaut AOT / compile-time injection only.
- Virtual threads everywhere — no `Mono`, no `Flux`, no reactive types.
- Every step below should be a separate git commit with a clear message.
- Run the full test suite after each phase before moving on.

---

## Phase 0 — Audit & Dependency Upgrade

**Goal**: Know exactly what we have before changing anything.

### 0.1 Inventory current dependencies
- List all deps in `build.gradle` / `pom.xml` with current versions.
- Flag anything that uses reflection-heavy libraries or reactive patterns.
- Flag any deprecated Micronaut APIs (e.g. old security module, old data APIs).

### 0.2 Resolve latest versions via context7
For each dependency, query context7:
- `micronaut-platform` BOM
- `micronaut-security`
- `micronaut-data` (JPA / JDBC)
- `jimmer-sql` + `jimmer-apt`
- `jooq` + `jooq-codegen`
- `micronaut-redis` (Lettuce)
- `micronaut-nats`
- `micronaut-email`
- `micronaut-object-storage` (local + S3)
- `postgres` JDBC driver
- `flyway` or `liquibase`
- Test: `micronaut-test`, `testcontainers`

### 0.3 Evaluate each dependency
- Is the integration approach still the recommended Micronaut way?
- Does it support virtual threads natively or does it need configuration?
- If the approach is wrong (e.g. using reactive driver for Redis), note the replacement.
- Document findings in `DEPS_AUDIT.md` in the repo root.

### 0.4 Apply upgrades
- Update versions only first; do not change code yet.
- Fix any compilation breaks caused by API changes.
- Commit: `chore: upgrade all dependencies to latest`

---

## Phase 1 — Project Structure & Base Setup

**Goal**: Clean, canonical project layout that will be the template skeleton.

### 1.1 Package structure
```
com.base
  ├── config/          # All @Factory, @Bean, config classes
  ├── common/          # Shared utilities, base classes, constants
  ├── security/        # Auth, JWT, session, permissions
  ├── user/            # User domain (registration, profile)
  ├── notification/    # In-app + email + SMS notifications
  ├── storage/         # File storage abstraction
  ├── messaging/       # NATS wrappers
  ├── cache/           # Redis wrappers
  └── server/          # Server-to-server mutual auth helpers
```

### 1.2 Virtual thread configuration
- Configure Micronaut to use virtual thread executor globally.
- Set `micronaut.executors.io.type: virtual` (or equivalent for current version).
- Verify no blocking calls happen on platform threads.
- Add a dev-mode `BlockingDetector` bean (disabled in prod) to catch accidental blocking.

### 1.3 Database setup
- Flyway/Liquibase migration baseline script for all base tables.
- jOOQ code generation from the schema (run at build time, not runtime).
- Jimmer entity definitions for all base entities.
- PostgreSQL connection pool configured for virtual threads (not async driver).

### 1.4 Environment & configuration
- `application.yml` with all feature flags and defaults.
- `application-dev.yml`, `application-test.yml`, `application-prod.yml`.
- Document every env variable in `ENV_VARS.md`.

---

## Phase 2 — User & Registration

**Goal**: A working user table + registration + login foundation.

### 2.1 Database schema (migration)
```sql
users
  id, username, email, phone, password_hash,
  is_active, is_verified_email, is_verified_phone,
  created_at, updated_at, last_login_at

user_identities          -- for OAuth providers (Google, etc.)
  id, user_id, provider, provider_user_id, provider_data, created_at
```

### 2.2 Registration flow
- Email + password registration endpoint `POST /auth/register`.
- Phone number registration variant.
- Input validation (bean validation, no reflection tricks).
- Password hashing via Argon2 (or bcrypt fallback) — use a `PasswordHasher` interface so it's swappable.
- Send verification email on registration (async via NATS → notification worker).
- Duplicate email/phone guard with clear error codes.

### 2.3 Default system accounts (seeded via migration or startup bean)
- **`system`** user: internal service account, has all permissions, cannot be deleted.
- **`dictator`** user: supreme admin, can manage system accounts, cannot be demoted by anyone else.
- Both seeded from env variables (`SYSTEM_PASSWORD`, `DICTATOR_PASSWORD`).
- Startup check: if either account missing, recreate from env.

---

## Phase 3 — Hybrid Auth System (Session + JWT)

**Goal**: Sessions for lifecycle control, JWTs for fast access. Best of both.

### 3.1 Architecture overview
```
[Login] → create Session (stored in Redis) + issue Refresh Token (signed, session-bound)
[Access Token Request] → validate Refresh Token → issue short-lived JWT Access Token
[API Calls] → validate JWT Access Token (stateless, no Redis hit)
[Logout] → delete Session from Redis → Refresh Token invalidated → all Access Tokens expire naturally
[Logout All Devices] → delete all Sessions for user → all Refresh Tokens dead
```

### 3.2 Session management (Redis)
- `Session` entity: `sessionId`, `userId`, `deviceInfo`, `ipAddress`, `createdAt`, `lastUsedAt`, `expiresAt`.
- Store as Redis hash: key `session:{sessionId}`.
- Session index per user: `user_sessions:{userId}` (Redis set of sessionIds).
- TTL on session keys = sliding window (refresh on each use).
- `SessionService`: create, find, touch, invalidate, invalidate-all-for-user.

### 3.3 Refresh token
- Signed JWT (short payload: `sessionId`, `userId`, issued-at).
- Validated against live session in Redis — if session gone, token dead.
- Endpoint: `POST /auth/token/refresh` → returns new Access Token.
- Refresh tokens themselves have a longer TTL (e.g. 30 days), sessions match.

### 3.4 Access token (JWT)
- Short-lived (e.g. 15 minutes).
- Payload: `userId`, `permissions[]`, `sessionId` (for correlation).
- Signed with RS256 (private key on server, public key distributed or JWKS endpoint).
- `TokenService`: issue, verify, extract claims.
- No Redis hit on access token validation — pure signature + expiry check.

### 3.5 Login endpoint
- `POST /auth/login` → email/password or phone/password.
- Returns: `{ accessToken, refreshToken, expiresIn, sessionId }`.
- Record `last_login_at`, `last_login_ip`.
- Failed attempts counter in Redis with lockout after N failures.

### 3.6 Logout endpoints
- `POST /auth/logout` → invalidate current session.
- `POST /auth/logout/all` → invalidate all sessions for current user.
- `POST /auth/logout/session/{sessionId}` → invalidate specific session (for device management).

### 3.7 Active sessions list
- `GET /auth/sessions` → returns all active sessions for the current user (device info, IP, last used).

---

## Phase 4 — Permission & Role System (Pure ABAC-ready)

**Goal**: Fully permission-based system. Roles are just named permission groups. ABAC policies live in the application layer.

### 4.1 Database schema (migration)
```sql
permissions
  id, name (unique slug, e.g. "user:read"), description, category, created_at

roles
  id, name, description, is_system (bool), created_at

role_permissions
  role_id, permission_id

user_roles
  user_id, role_id, granted_at, granted_by

user_permissions           -- direct permission overrides
  user_id, permission_id, is_denied (bool), granted_at, granted_by
```

### 4.2 Special internal permissions (not stored in DB, hardcoded constants)
```java
// Constants class
DICTATOR_PERMISSION = "internal:dictator"   // supreme, manages system accounts
SYSTEM_PERMISSION   = "internal:system"     // all access, auto-granted to system user
```
- Dictator user always has `DICTATOR_PERMISSION`; this grants all others implicitly.
- System user always has `SYSTEM_PERMISSION`; this grants all non-dictator permissions.
- Regular users: computed from `roles → permissions` + direct `user_permissions`.
- `user_permissions.is_denied = true` can explicitly deny a permission even if granted via role.

### 4.3 Permission resolution (no reflection, compile-time safe)
```
resolvePermissions(userId):
  1. If dictator user → return FULL_ACCESS sentinel
  2. If system user → return ALL_PERMISSIONS sentinel
  3. Collect permissions from all user's roles (via role_permissions)
  4. Apply direct user_permissions: add grants, remove denials
  5. Cache result in Redis: key "user_permissions:{userId}", TTL 5 min
  6. On any role/permission change → invalidate cache for affected users
```

### 4.4 `PermissionEvaluator` (replaces old `RbacPermissionEvaluator`)
- Implement `PermissionEvaluator` interface (Micronaut Security).
- `hasPermission(Authentication auth, String permission)`.
- Loads permissions from JWT claims first (fast path), falls back to Redis/DB.
- Handles `DICTATOR_PERMISSION` and `SYSTEM_PERMISSION` sentinel checks.

### 4.5 `@RequiresPermission` annotation
```java
@RequiresPermission("user:write")      // on controller class or method
@RequiresPermission({"report:read", "report:export"})   // all required
@RequiresAnyPermission({"admin:read", "user:read"})     // any one
```
- Backed by a Micronaut `MethodInterceptor` (AOT, no reflection at runtime).
- Works at controller level and service level.
- On failure: `403 Forbidden` with structured error body `{ error: "PERMISSION_DENIED", required: [...] }`.

### 4.6 ABAC policies (application layer pattern)
- `Policy<Context>` interface: `boolean evaluate(Authentication auth, Context ctx)`.
- Example: `PostEditPolicy` checks `auth.userId == post.authorId || hasPermission("post:edit:any")`.
- Policies are plain beans, injected into service/use-case classes.
- Do NOT put ABAC logic in controllers — keep controllers thin.
- Document the pattern with 2-3 example policies in `common/policy/examples/`.

### 4.7 Role management endpoints (admin only)
- `GET /admin/roles` — list roles.
- `POST /admin/roles` — create role.
- `PUT /admin/roles/{id}` — update role name/description.
- `DELETE /admin/roles/{id}` — delete role (not if `is_system = true`).
- `PUT /admin/roles/{id}/permissions` — set permissions on role.
- `POST /admin/users/{id}/roles` — assign roles to user.
- `POST /admin/users/{id}/permissions` — direct permission grants/denials.

---

## Phase 5 — Pagination

**Goal**: One reusable pattern for all paginated routes.

### 5.1 Request model
```java
record PageRequest(
    int page,        // 1-based
    int size,        // default 20, max 100
    String sortBy,   // field name, validated against allowlist per endpoint
    String sortDir   // "asc" | "desc"
) {}
```
- `@QueryValue`-bound, validated via `@Valid`.
- `PageRequestResolver`: a Micronaut `ArgumentBinder` so controllers get it automatically.

### 5.2 Response model
```java
record PageResponse<T>(
    List<T> data,
    long total,
    int page,
    int size,
    int totalPages,
    boolean hasNext,
    boolean hasPrev
) {}
```

### 5.3 jOOQ query helper
```java
PagedQuery.of(dsl, baseQuery)
    .sortable("name", "createdAt", "email")   // allowlist
    .fetch(pageRequest, RecordMapper::toDto);
```
- Returns `PageResponse<T>`.
- COUNT query runs alongside data query (not two separate round trips — use jOOQ windowed count).

### 5.4 Jimmer integration
- `KQuery.paginate(pageRequest)` extension for Jimmer fetchers.
- Consistent output wrapped in `PageResponse<T>`.

---

## Phase 6 — Redis & NATS Setup

**Goal**: Reusable wrappers, not raw client calls scattered everywhere.

### 6.1 Redis setup
- Configure Lettuce (non-reactive, VT-compatible sync commands).
- `RedisCache<T>` generic utility:
  ```java
  cache.get("key", MyType.class)
  cache.set("key", value, Duration.ofMinutes(5))
  cache.delete("key")
  cache.getOrLoad("key", MyType.class, () -> expensiveCall())
  ```
- `RedisPubSub` for simple channel messaging via Redis pub/sub (secondary to NATS).
- `RedisDistributedLock`: try-lock with TTL, used for idempotent operations.
- Key naming convention documented: `{service}:{entity}:{id}:{field}`.

### 6.2 NATS setup
- Configure NATS JetStream (persistent, at-least-once delivery).
- `NatsPublisher`: publish to subject with structured envelope `{ eventType, payload, metadata }`.
- `NatsSubscriber` base class: extend to create a worker, handles ack/nack automatically.
- Dead-letter subject for failed messages after N retries.
- Subject naming convention: `{service}.{entity}.{action}` e.g. `base.notification.send`.
- Config: `application.yml` NATS connection, JetStream stream definitions.

### 6.3 Standard event envelope
```java
record NatsEvent<T>(
    String eventId,       // UUID
    String eventType,     // e.g. "notification.send"
    String sourceService, // e.g. "base"
    Instant issuedAt,
    T payload
) {}
```

---

## Phase 7 — Notification System

**Goal**: Fire-and-forget from any service, delivered reliably in background.

### 7.1 Database schema
```sql
notifications
  id, user_id, type, title, body, data (jsonb),
  is_read, read_at, created_at, expires_at
```

### 7.2 Sending a notification (one call)
```java
notificationPublisher.send(
    NotificationRequest.to(userId)
        .type("ORDER_SHIPPED")
        .title("Your order is on the way")
        .body("Estimated delivery: Friday")
        .data(Map.of("orderId", orderId))
        .channels(Channel.IN_APP, Channel.EMAIL)   // optional; defaults to IN_APP only
);
```
- `notificationPublisher.send(...)` publishes to NATS subject `base.notification.send`.
- A `NotificationWorker` (NATS subscriber) picks it up, persists to DB, triggers channel delivery.

### 7.3 Notification worker (background)
- Subscribes to `base.notification.send`.
- Saves to `notifications` table.
- Dispatches to channels: IN_APP (stored), EMAIL (via mail service), SMS (via SMS service).
- Acks only after all dispatches attempted (non-blocking failure — log and continue).

### 7.4 Notification API endpoints
- `GET /notifications` — paginated list for current user (unread first).
- `GET /notifications/unread-count` — fast count from Redis counter.
- `PUT /notifications/{id}/read` — mark one as read.
- `PUT /notifications/read-all` — mark all as read.
- `DELETE /notifications/{id}` — soft delete.
- Redis unread counter: `notification:unread:{userId}`, incremented on send, decremented on read.

---

## Phase 8 — Mail & SMS Setup

### 8.1 Mail
- Use Micronaut Email (JavaMail / SendGrid / SES — switchable via config).
- `MailService` abstraction: `send(MailMessage)` — provider behind config flag `mail.provider`.
- Template engine: Micronaut Views (Thymeleaf or Pebble) for HTML templates.
- Templates to create:
    - `email-verify.html` — email verification link.
    - `password-reset.html` — reset link.
    - `magic-link.html` — magic login link.
    - `notification.html` — generic notification.
    - `welcome.html` — post-registration welcome.
- Template variables passed as `Map<String, Object>`.
- Dev mode: log email to console instead of sending.

### 8.2 SMS
- `SmsService` abstraction: `send(String phone, String message)`.
- Provider: Twilio (default) — switchable via `sms.provider`.
- Dev mode: log to console.
- Shared `SmsTemplates` constants for common messages.

---

## Phase 9 — Password Reset, Verifications & Magic Links

**Goal**: All transactional auth flows using Redis for state (no extra DB tables needed).

### 9.1 Redis token store pattern
```
key:   "txn:{type}:{token}"
value: { userId, metadata, expiresAt }
TTL:   per type (e.g. 15min for reset, 10min for OTP, 1hr for magic link)
```
Types: `email-verify`, `phone-verify`, `password-reset`, `magic-link`.

### 9.2 Email verification
- On registration → generate token → store in Redis → send `email-verify` mail.
- `GET /auth/verify/email?token={token}` → mark `is_verified_email = true`, delete token.
- Resend: `POST /auth/verify/email/resend`.

### 9.3 Phone verification (OTP)
- `POST /auth/verify/phone/request` → generate 6-digit OTP → store in Redis → send SMS.
- `POST /auth/verify/phone/confirm` `{ phone, otp }` → validate → mark `is_verified_phone = true`.
- Rate-limit OTP requests per phone (Redis counter + TTL).

### 9.4 Password reset
- `POST /auth/password/forgot` `{ email }` → generate token → send `password-reset` mail.
- `POST /auth/password/reset` `{ token, newPassword }` → validate token → update hash → invalidate all sessions.
- Always return 200 on forgot (no email enumeration).

### 9.5 Magic link login
- `POST /auth/magic-link/request` `{ email }` → generate token → store in Redis → send `magic-link` mail.
- `GET /auth/magic-link/verify?token={token}` → validate → create session → return tokens.
- Single use (delete Redis key on first use).

---

## Phase 10 — Sign in with Google

### 10.1 Flow
- `GET /auth/google` → redirect to Google OAuth consent.
- `GET /auth/google/callback?code=...` → exchange code → fetch profile.
- If `user_identities` record exists → login that user.
- If email matches existing user → link identity, login.
- If new → auto-register, create user + identity record.
- Return same token response as regular login.

### 10.2 Implementation
- Use Micronaut Security OAuth2 (`micronaut-security-oauth2`).
- Config: `micronaut.security.oauth2.clients.google.*` from env vars.
- `GoogleUserDetailsMapper` bean: maps Google profile to internal `UserDetails`.
- No reflection — use record-based DTOs for Google profile response.

---

## Phase 11 — Fingerprint & Device Tracking

**Goal**: Track client fingerprints for security signals (not strict enforcement).

### 11.1 Database schema
```sql
user_fingerprints
  id, user_id, fingerprint_hash, user_agent, ip_address,
  is_trusted, first_seen_at, last_seen_at, session_id
```

### 11.2 Fingerprint flow
- Client sends `X-Device-Fingerprint` header (hash of browser signals).
- `FingerprintFilter` (Micronaut `HttpServerFilter`): extract header, log/update on each login.
- On login: if fingerprint unknown → flag as new device → optionally notify user.
- `GET /auth/devices` — list known fingerprints/devices for user.
- `DELETE /auth/devices/{id}` — untrust a device + invalidate that session.

---

## Phase 12 — Server-to-Server Mutual Auth

**Goal**: Validate inbound request signatures, sign outbound requests.

### 12.1 Review existing helper class
- Locate the existing helper (check `server/` or `security/` package).
- Evaluate: does it cover HMAC-SHA256 request signing? Timestamp replay protection? Key rotation?
- Document gaps.

### 12.2 Complete the implementation
- `RequestSigner`: signs outbound requests with `X-Signature`, `X-Timestamp`, `X-Service-ID` headers.
    - Signature = HMAC-SHA256(secret, `{method}:{path}:{timestamp}:{bodyHash}`).
- `SignatureValidator`: validates inbound requests, rejects if timestamp > 5 min old (replay protection).
- `@RequiresServiceAuth` annotation for controller methods that require server-to-server auth.
- Service key pairs stored in config (env vars), with support for key rotation (accept old key for grace period).
- `ServiceAuthFilter`: Micronaut server filter that calls `SignatureValidator` for annotated routes.

---

## Phase 13 — Storage (Local ↔ S3 Toggle)

### 13.1 Review existing toggle
- Locate the existing storage toggle implementation.
- Check that the abstraction works: `StorageService` interface with `LocalStorageService` and `S3StorageService` implementations.
- Verify the env variable switch is clean (`STORAGE_PROVIDER=local|s3`).

### 13.2 Ensure complete implementation
- `StorageService` interface:
  ```java
  String store(String path, InputStream data, long size, String contentType);
  InputStream retrieve(String path);
  void delete(String path);
  String getPublicUrl(String path);
  ```
- `LocalStorageService`: writes to `STORAGE_LOCAL_ROOT` env var path, serves via static route.
- `S3StorageService`: uses AWS SDK v2 (sync client, VT-compatible), bucket from env.
- Presigned URL support for S3 (time-limited direct access).
- File type validation and size limits in a shared `UploadValidator`.

---

## Phase 14 — Security Audit

**Goal**: Find and fix security issues before calling this a template.

> **Do this phase last** — all features must be in place first.

### 14.1 Authentication & session security
- [ ] Session fixation: regenerate session ID on login.
- [ ] Refresh token rotation: issue a new refresh token on each use, invalidate old.
- [ ] Access token revocation: confirm short TTL is acceptable; add optional token blacklist in Redis if needed.
- [ ] Brute force: rate-limit login, OTP, and magic-link endpoints (Redis counter per IP + per user).
- [ ] Timing-safe comparison for all token/OTP validation (use `MessageDigest.isEqual`, not `equals`).
- [ ] HTTPS-only cookies for any cookie-based values; `SameSite=Strict`.

### 14.2 Input & injection
- [ ] All external inputs go through `@Valid` bean validation.
- [ ] jOOQ: all queries use parameterized binding — no string concatenation.
- [ ] Jimmer: confirm no raw SQL paths without parameter binding.
- [ ] Sort field allow-listing in pagination (no free-form ORDER BY injection).
- [ ] File upload: validate MIME type from content (not filename), scan for double extensions.

### 14.3 Authorization
- [ ] Every endpoint has an explicit permission requirement or is explicitly public.
- [ ] No endpoint falls through to `authenticated()` only — must check a permission.
- [ ] Admin endpoints require both auth + specific admin permission.
- [ ] IDOR check: all resource fetches filter by `userId` unless caller has broad permission.
- [ ] Dictator/system accounts cannot be deleted or demoted via API.

### 14.4 Data & secrets
- [ ] No secrets in logs (passwords, tokens, keys).
- [ ] Passwords: Argon2id with tuned parameters (time cost, memory cost, parallelism).
- [ ] JWT secrets / RSA keys: loaded from env, never from code or config files in VCS.
- [ ] Sensitive fields excluded from API responses (`passwordHash`, internal flags).
- [ ] JSONB fields in DB: sanitize before storing user-controlled data.

### 14.5 Infrastructure
- [ ] CORS policy: explicit allow-list, not wildcard in production.
- [ ] Security headers: `Content-Security-Policy`, `X-Frame-Options`, `Referrer-Policy` — add via Micronaut filter.
- [ ] Dependency vulnerabilities: run `./gradlew dependencyCheckAnalyze` (OWASP plugin) and fix high/critical.
- [ ] Confirm no DEBUG-level logging in production profile (tokens, SQL with params, etc.).
- [ ] Replay protection on server-to-server signed requests (timestamp window check).

### 14.6 Final checklist
- [ ] All TODO/FIXME comments resolved or ticketed.
- [ ] No hardcoded credentials anywhere in the codebase (run `git grep -i "password\|secret\|key" -- "*.java" "*.yml"`).
- [ ] Integration tests cover the full auth lifecycle (register → verify → login → refresh → logout).
- [ ] Security audit findings documented in `SECURITY_AUDIT.md`.

---

## Delivery: Base Project Checklist

Before declaring this the reusable base template, verify:

- [ ] `README.md` explains every env variable and how to bootstrap.
- [ ] `ENV_VARS.md` lists all env vars with type, default, and purpose.
- [ ] `DEPS_AUDIT.md` from Phase 0 is up to date.
- [ ] `SECURITY_AUDIT.md` from Phase 14 is complete.
- [ ] Database migrations in `src/main/resources/db/migrations/` are numbered and complete.
- [ ] jOOQ generated sources checked in or reliably generated at build time.
- [ ] Jimmer APT sources generated at build time (no manual steps).
- [ ] Docker Compose file for local dev (Postgres, Redis, NATS).
- [ ] Seed script for `system` and `dictator` users + base permissions + base roles.
- [ ] All public endpoints documented (at minimum a Postman collection or OpenAPI spec).
- [ ] Smoke test: fresh clone → `docker compose up` → `./gradlew run` → health check passes.

---

## Phased Summary Table

| Phase | Focus | Depends On |
|-------|-------|------------|
| 0 | Audit & upgrade deps | — |
| 1 | Project structure, VT config, DB setup | 0 |
| 2 | User tables, registration, system accounts | 1 |
| 3 | Hybrid auth: sessions + JWT | 2 |
| 4 | Permissions, roles, ABAC pattern | 2, 3 |
| 5 | Pagination | 1 |
| 6 | Redis & NATS wrappers | 1 |
| 7 | Notification system | 4, 6 |
| 8 | Mail & SMS | 6, 7 |
| 9 | Password reset, verify, magic links | 3, 6, 8 |
| 10 | Sign in with Google | 3 |
| 11 | Fingerprint & device tracking | 3 |
| 12 | Server-to-server mutual auth | 1 |
| 13 | Storage toggle (local / S3) | 1 |
| 14 | Security audit | All |