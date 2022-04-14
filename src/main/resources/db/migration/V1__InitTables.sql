
create table auth_info(

    id   bigserial primary key not null ,
    email varchar not null unique ,
    is_account_non_expired   boolean not null,
    is_account_non_locked     boolean not null,
    is_credentials_non_expired boolean not null,
    is_enabled                 boolean not null,
    password   varchar not null
);
create table auth_info_roles
(
    auth_info_id bigserial primary key not null
         references auth_info,
    roles_id   bigserial primary key not null
        references roles
);
create table roles
(
    id   bigint not null
        constraint roles_pkey
            primary key,
    name varchar(255)
);
create table users
(
    id           bigint not null
        constraint users_pkey
            primary key,
    email        varchar(255),
    password     varchar(255),
    user_name    varchar(255),
    auth_info_id bigint
        constraint fk2blmj1akvmy7gwdifggeajcy8
            references auth_info
);