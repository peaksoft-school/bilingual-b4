insert into roles(name)values('admin'),('user');
insert into auth_info(
                       email ,
                       is_account_non_expired,
                       is_account_non_locked,
                       is_credentials_non_expired,
                       is_enabled,
                       password)values
('admin@gmail.com',true,true,true,true,'$2a$12$r8YGcA0EGUIUD3r12aL/Teo9rhcezwEEYmBbh6s59E1274WdPz4Au'),
('user@gmail.com',true,true,true,true,'$2a$12$38kropYhwen3RNAndXFTYe.el28u1tBeJRRLT2F6dq9elCHpEoLmG');
insert into auth_info_roles(auth_info_id,roles_id)
values(1,1),(2,2);
insert into users(email,password,user_name,auth_id)
values('admin@gmail.com','$2a$12$r8YGcA0EGUIUD3r12aL/Teo9rhcezwEEYmBbh6s59E1274WdPz4Au','Admin',1),
      ('user@gmail.com','$2a$12$38kropYhwen3RNAndXFTYe.el28u1tBeJRRLT2F6dq9elCHpEoLmG','Uulkan',2);