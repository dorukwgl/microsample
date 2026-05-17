-- Index for user organization lookups
CREATE INDEX idx_users_organization_id ON users(organization_id);

-- Composite index for member queries
CREATE INDEX idx_users_org_admin_status ON users(organization_id, is_org_admin);
