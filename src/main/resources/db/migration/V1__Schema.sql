drop table if exists roles;
drop table if exists auth_info;
drop table if exists auth_info_roles;
drop table if exists users;

create table if not exists roles(
    id   bigserial primary key not null,
    name varchar not null
);
create table if not exists auth_info(
    id                         bigserial primary key not null,
    email                      varchar  not null unique,
    is_account_non_expired     boolean  not null,
    is_account_non_locked      boolean  not null,
    is_credentials_non_expired boolean  not null,
    is_enabled                 boolean  not null,
    password                   varchar  not null
);
create table if not exists auth_info_roles(
    auth_info_id bigint unique not null
        references auth_info (id),
    roles_id bigint unique not null
        references roles (id)
);
create table if not exists users(
    id        bigserial primary key not null,
    email     varchar not null,
    password  varchar not null,
    user_name varchar not null,
    auth_id   bigint unique
        references auth_info (id)
);