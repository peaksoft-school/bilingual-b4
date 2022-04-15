update users set password =(password);
update auth_info set password = MD5(password);