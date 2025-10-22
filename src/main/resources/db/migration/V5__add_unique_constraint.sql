ALTER TABLE roles_tb
    ADD CONSTRAINT uc_roles_tb_role_name UNIQUE (role_name);

ALTER TABLE token_tb
    ADD CONSTRAINT uc_token_tb_token UNIQUE (token);

ALTER TABLE users_tb
    ADD CONSTRAINT uc_users_tb_username UNIQUE (username);