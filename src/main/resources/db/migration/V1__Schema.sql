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

                                auth_info_id  bigint,
                                roles_id bigint,
                                primary key (auth_info_id,roles_id),
                                foreign key (auth_info_id) references auth_info(id),
                                foreign key (roles_id) references roles(id)




);

create table if not exists users(
                                    id        bigserial primary key not null,
                                    email     varchar not null,
                                    password  varchar not null,
                                    user_name varchar not null,
                                    auth_info_id bigint references auth_info(id)
);
create table words(
    id bigserial primary key,
    word_name varchar,
    correct_answer boolean
);
create table tests(
                        id bigserial primary key ,
                        title varchar,
                        short_description varchar
  );
create table users_test(
                               users_id bigint,
                               test_id bigint,
                               primary key (users_id,test_id),
                               foreign key (users_id)references users(id),
                               foreign key (test_id)references tests(id)

    );
create table if not exists questions(
                                        id bigserial primary key not null,
                                        name varchar,
                                        single_and_multi_type varchar,
                                        audio varchar,
                                        number_of_replace integer,
                                        upload varchar,
                                        play varchar,
                                        correct_answer boolean not null,
                                        record varchar,
                                        upload_image varchar,
                                        question_statement varchar,
                                        word_counter integer,
                                        question_to_the_passage varchar,
                                        passage varchar,
                                        high_light_correct_answer varchar,
                                        question_type varchar,
                                        word_id bigint references words(id),
                                        test_id bigint references tests(id)

);



