-- Make name and org_code nullable to support placeholder organizations for personal users
ALTER TABLE organizations
    ALTER COLUMN name DROP NOT NULL,
    ALTER COLUMN org_code DROP NOT NULL;