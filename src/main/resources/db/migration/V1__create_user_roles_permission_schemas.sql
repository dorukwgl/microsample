-- enums
create type USER_STATUS as enum ('ACTIVE', 'INACTIVE');
create type MULTI_AUTH_TYPE as enum ('NONE', 'PHONE', 'EMAIL');

-- users
CREATE TABLE users
(
    id            UUID PRIMARY KEY default uuidv7(),
    username      VARCHAR(255)  NOT NULL UNIQUE,
    email varchar(255) not null unique,
    phone varchar(255),
    password VARCHAR(1024) NOT NULL,
    multi_factor_auth MULTI_AUTH_TYPE DEFAULT 'NONE',
    is_email_verified boolean DEFAULT false,
    is_phone_verified boolean DEFAULT false,
    status       USER_STATUS DEFAULT 'ACTIVE',
    created_at timestamp with time zone default now(),
    updated_at    TIMESTAMP with time zone default now(),
    deleted_at    TIMESTAMP
);

-- profile
create table user_profiles
(
    id bigserial primary key,
    user_id uuid unique references users (id) on delete cascade,
    full_name varchar(255),
    icon_url varchar(255),
    address varchar(255),
    city varchar(255),
    state varchar(255),
    country varchar(255),
    postal_code varchar(255),
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now(),
    deleted_at timestamp with time zone
);

-- roles
CREATE TABLE roles
(
    name VARCHAR(100) primary key,
    deleted_at timestamp with time zone
);

-- permissions: store enum name as string
CREATE TABLE permissions
(
    name varchar(200) primary key,
    deleted_at timestamp with time zone
);

-- role -> permission mapping
CREATE TABLE role_permissions
(
    role_name VARCHAR(100) NOT NULL REFERENCES roles (name) ON DELETE CASCADE,
    permission_name VARCHAR(200) NOT NULL REFERENCES permissions (name) ON DELETE CASCADE,
    PRIMARY KEY (role_name, permission_name)
);

-- user -> role mapping
CREATE TABLE user_roles
(
    user_id uuid NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    name varchar(100) NOT NULL REFERENCES roles (name) ON DELETE CASCADE,
    PRIMARY KEY (user_id, name)
);

-- SESSIONS AND BIOMETRICS --
create table sessions
(
    id bigserial primary key,
    user_id uuid not null references users (id) on delete cascade,
    session_id varchar(255) not null unique,
    device_info varchar(255),
    device_id varchar(255) unique,
    expires_at timestamp with time zone not null,
    created_at timestamp with time zone default now(),
    permissions integer[] not null
);

create table biometrics
(
    id uuid primary key default uuidv7(),
    user_id uuid not null references users (id) on delete cascade,
    public_key varchar(255) not null unique,
    device_id varchar(255) not null unique,
    created_at timestamp with time zone default now()
);


-- index all foreign keys
create index idx_sessions_user_id on sessions (user_id);
create index idx_biometrics_user_id on biometrics (user_id);