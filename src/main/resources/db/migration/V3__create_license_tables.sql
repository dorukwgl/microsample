CREATE TYPE organization_type_enum AS ENUM ('PERSONAL', 'ENTERPRISE');
CREATE TYPE license_type_enum AS ENUM ('SUBSCRIPTION', 'PERPETUAL', 'TRIAL');
CREATE TYPE license_status_enum AS ENUM (
    'ACTIVE',
    'EXPIRED',
    'SUSPENDED',
    'REVOKED'
    );

CREATE TABLE organizations
(
    id         UUID PRIMARY KEY,
    name       TEXT                     NOT NULL,
    type       organization_type_enum   NOT NULL,

    org_code   varchar(255) unique, -- maps to Lago customer_id or external system

    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

ALTER TABLE users
    ADD COLUMN organization_id UUID,
    ADD COLUMN is_org_admin    BOOLEAN NOT NULL DEFAULT FALSE;

ALTER TABLE users
    ADD CONSTRAINT fk_users_organization
        FOREIGN KEY (organization_id)
            REFERENCES organizations (id)
            ON DELETE CASCADE;

CREATE TABLE licenses
(
    id              UUID PRIMARY KEY                  default uuidv7(),

    license_key     TEXT                     NOT NULL UNIQUE,

    organization_id UUID                     NOT NULL REFERENCES organizations (id),

    sku_id          UUID                     NOT NULL, -- from catalog
    tier_id         BIGINT                   NOT NULL, -- from catalog

    type            license_type_enum        NOT NULL,
    status          license_status_enum      NOT NULL,

    name            TEXT                     NOT NULL,
    tier_name       TEXT                     NOT NULL,

    entitlements    jsonb                             default '{}'::jsonb,
    total_seats     INTEGER                  NOT NULL DEFAULT 1,
    assigned_seats  INTEGER                  NOT NULL DEFAULT 0,

    valid_from      TIMESTAMP WITH TIME ZONE NOT NULL,
    valid_until     TIMESTAMP WITH TIME ZONE,

    created_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE license_addons
(
    id           UUID PRIMARY KEY                  default uuidv7(),

    license_id   UUID                     NOT NULL REFERENCES licenses (id) ON DELETE CASCADE,

    sku_id       UUID                     NOT NULL,
    tier_id      BIGINT,

    status       license_status_enum      NOT NULL default 'ACTIVE',

    name         TEXT                     NOT NULL,
    tier_name    TEXT                     NOT NULL,

    valid_until  TIMESTAMP WITH TIME ZONE,
    entitlements jsonb                             default '{}'::jsonb,

    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_license_addon_license ON license_addons (license_id);
CREATE INDEX idx_license_org ON licenses (organization_id);
CREATE INDEX idx_license_sku ON licenses (sku_id);
CREATE INDEX idx_license_validity ON licenses (status, valid_until);