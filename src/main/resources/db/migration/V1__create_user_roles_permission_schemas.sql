-- enums
create type USER_STATUS as enum ('ACTIVE', 'INACTIVE');
create type MULTI_AUTH_TYPE as enum ('NONE', 'PHONE', 'EMAIL');
create type FILE_VISIBILITY as enum ('PRIVATE', 'PUBLIC');

create table media_store
(
    id         bigserial PRIMARY KEY,
    object_key VARCHAR                   NOT NULL UNIQUE,
    visibility FILE_VISIBILITY default 'PUBLIC',
    mime_type  VARCHAR                   NOT NULL,
    size       BIGINT                    NOT NULL,
    created_at timestamp with time zone  default now(),
    deleted_at timestamp with time zone
);

-- users
CREATE TABLE users
(
    id                UUID PRIMARY KEY         default uuidv7(),
    username          VARCHAR(255)  NOT NULL UNIQUE,
    email             varchar(255)  not null unique,
    phone             varchar(255),
    password          VARCHAR(1024) NOT NULL,
    multi_factor_auth MULTI_AUTH_TYPE          DEFAULT 'NONE',
    is_email_verified boolean                  DEFAULT false,
    is_phone_verified boolean                  DEFAULT false,
    status            USER_STATUS              DEFAULT 'ACTIVE',
    created_at        timestamp with time zone default now(),
    updated_at        TIMESTAMP with time zone default now()
);

-- profile
create table user_profiles
(
    id              bigserial primary key,
    user_id         uuid unique references users (id) on delete cascade,
    full_name       varchar(255),
    profile_icon bigint,
    address         varchar(255),
    city            varchar(255),
    state           varchar(255),
    country         varchar(255),
    postal_code     varchar(255),
    created_at      timestamp with time zone default now(),
    updated_at      timestamp with time zone default now(),
    foreign key (profile_icon) references media_store(id)
);

-- roles
CREATE TABLE roles
(
    name       VARCHAR(100) primary key,
    deleted_at timestamp with time zone
);

-- permissions: store enum name as string
CREATE TABLE permissions
(
    name       varchar(200) primary key,
    deleted_at timestamp with time zone
);

-- role -> permission mapping
CREATE TABLE role_permissions
(
    role_name       VARCHAR(100) NOT NULL REFERENCES roles (name) ON DELETE CASCADE,
    permission_name VARCHAR(200) NOT NULL REFERENCES permissions (name) ON DELETE CASCADE,
    PRIMARY KEY (role_name, permission_name)
);

-- user -> role mapping
CREATE TABLE user_roles
(
    user_id uuid         NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    name    varchar(100) NOT NULL REFERENCES roles (name) ON DELETE CASCADE,
    PRIMARY KEY (user_id, name)
);

-- SESSIONS --
-- device_info: parsed User-Agent string (e.g. "Chrome 125 on Windows")
-- device_id:   Firebase FCM token or browser UUID for push notifications (notification device ID)
--              nullable — sessions without a notification channel (e.g. biometric-only) have no device_id.
--              unique per (user, device_id) when not null — one active session per notification channel.
create table sessions
(
    id          bigserial primary key,
    user_id     uuid                     not null references users (id) on delete cascade,
    session_id  varchar(255)             not null unique,
    device_info varchar(500),
    device_id   varchar(255),
    expires_at  timestamp with time zone not null,
    created_at  timestamp with time zone default now(),
    permissions integer[]                not null
);
create index idx_sessions_user_id on sessions (user_id);
create index idx_sessions_active_devices on sessions (user_id, expires_at);
create unique index idx_sessions_user_device on sessions (user_id, device_id) where device_id is not null;

-- BIOMETRICS --
-- device_id: biometric hardware identifier (e.g. Android Keystore alias, Secure Enclave key ID).
--            persistent across sessions and normal logouts — survives app reinstalls.
--            NOT the same as sessions.device_id (notification device ID).
create table biometrics
(
    id           uuid primary key default uuidv7(),
    user_id      uuid         not null references users (id) on delete cascade,
    public_key   bytea        not null unique,
    device_id    varchar(255) not null unique,
    last_used_at timestamp with time zone
);
create index idx_biometrics_user_id on biometrics (user_id);

-- USER DEVICES --
-- Maps notification_device_id ↔ bio_device_id for each user.
-- notification_device_id: Firebase FCM token or browser UUID (session-scoped).
--                         Nullified on logout to stop push; preserved on re-login.
-- bio_device_id:          Biometric hardware ID from enrollment (persistent).
--                         Survives logout; only deleted on logout-all?biometric=true.
-- Multiple rows per user: one per active device.
create table user_devices
(
    id                     bigserial primary key,
    user_id                uuid         not null references users (id) on delete cascade,
    notification_device_id varchar(255),
    bio_device_id          varchar(255),
    device_info            varchar(500),
    last_login_at          timestamp with time zone default now(),
    created_at             timestamp with time zone default now()
);
create unique index idx_user_devices_notif_unique on user_devices (user_id, notification_device_id) where notification_device_id is not null;
create index idx_user_devices_user_id on user_devices (user_id);

-- casts for enum columns used via jOOQ raw queries
CREATE CAST (character varying AS multi_auth_type) WITH INOUT AS IMPLICIT;
CREATE CAST (character varying AS USER_STATUS) WITH INOUT AS IMPLICIT;
