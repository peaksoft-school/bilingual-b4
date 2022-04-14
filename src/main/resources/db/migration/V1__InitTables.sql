
create table roles
(
    id   bigserial primary key not null ,
    name varchar(255)
);
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
    auth_info_id bigint unique not null
        references auth_info(id),
    roles_id   bigint unique not null
        references roles(id)
);
create table users
(
    id  bigserial primary key not null ,
    email        varchar(255),
    password     varchar(255),
    user_name    varchar(255),
    auth_id bigint unique
        references auth_info(id)
);