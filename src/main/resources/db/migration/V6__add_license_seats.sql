CREATE TABLE license_seats
(
    id          UUID PRIMARY KEY     DEFAULT uuidv7(),
    license_id  UUID        NOT NULL REFERENCES licenses (id) ON DELETE CASCADE,
    user_id     UUID        NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    assigned_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    assigned_by UUID        NOT NULL REFERENCES users (id),
    UNIQUE (license_id, user_id)
);

CREATE INDEX idx_license_seats_license ON license_seats (license_id);
CREATE INDEX idx_license_seats_user ON license_seats (user_id);