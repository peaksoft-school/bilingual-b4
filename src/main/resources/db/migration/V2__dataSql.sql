insert into roles(name)values('admin'),('user');
insert into auth_info(
                       email ,
                       is_account_non_expired,
                       is_account_non_locked,
                       is_credentials_non_expired,
                       is_enabled,
                       password)values
('admin@gmail.com',true,true,true,true,'admin'),
('user@gmail.com',true,true,true,true,'12345');
insert into auth_info_roles(auth_info_id,roles_id)
values(1,1);
insert into users(email,password,user_name,auth_id)
values('admin@gmail.com','admin','Admin',1);