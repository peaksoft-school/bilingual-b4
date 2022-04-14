update users set password = MD5(password);
update auth_info set password = MD5(password);