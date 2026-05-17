create table product_servers
(
    id           uuid primary key default uuidv7(),
    name         text not null,
    sku_id       text unique not null, -- which product this is
    host_url     text        not null, -- where to push webhooks
    public_key   text        not null, -- product server's pubA (verify inbound)
    created_at   timestamptz default now(),
    updated_at   timestamptz default now()
)
