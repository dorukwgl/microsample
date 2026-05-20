-- user devices: tracks notification + biometric device IDs per user, persists across sessions
create table user_devices
(
    id                     bigserial primary key,
    user_id                uuid         not null references users (id) on delete cascade,
    notification_device_id varchar(255) not null,
    bio_device_id          varchar(255),
    device_info            varchar(500),
    last_login_at          timestamp with time zone default now(),
    created_at             timestamp with time zone default now(),
    unique (user_id, notification_device_id)
);

create index idx_user_devices_user_id on user_devices (user_id);