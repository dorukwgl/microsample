create type ATTACHMENT_TYPE as enum ('IMAGE', 'AUDIO', 'VIDEO', 'DOCUMENT');

create table notifications
(
    id              bigserial primary key,
    user_id         uuid                     not null references users (id) on delete cascade,
    title           varchar(255)             not null,
    message         text                     not null,
    icon            varchar(500),
    attachment      varchar(500),
    attachment_type ATTACHMENT_TYPE,
    is_read         boolean                  default false,
    created_at      timestamp with time zone default now(),
    updated_at      timestamp with time zone default now()
);

create index idx_notifications_user_id on notifications (user_id);
create index idx_notifications_user_unread on notifications (user_id, is_read) where is_read = false;
