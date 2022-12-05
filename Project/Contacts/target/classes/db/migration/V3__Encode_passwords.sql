create extension if not exists pgcrypto;

update contacts_users set cuser_password = crypt(cuser_password, gen_salt('bf', 8));