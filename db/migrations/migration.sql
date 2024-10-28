-- Criar um novo esquema
CREATE SCHEMA IF NOT EXISTS ACCESS;
GRANT ALL ON SCHEMA ACCESS TO postgres;

-- Definir o schema padrão para a sessão atual
SET search_path = ACCESS;

-- PROFILE
CREATE TABLE IF NOT EXISTS EN_PROFILE
(
    profile_id serial PRIMARY KEY,
    name varchar(50) NOT NULL UNIQUE,
    description varchar(255)
);

-- PERMISSION
CREATE TABLE IF NOT EXISTS EN_PERMISSION
(
    permission_id serial PRIMARY KEY,
    name varchar(50) NOT NULL UNIQUE,
    description varchar(255)
);

-- ACCOUNT
CREATE TABLE IF NOT EXISTS EN_ACCOUNT
(
    account_id serial PRIMARY KEY,
    profile_id integer,
    email character varying(255) NOT NULL UNIQUE,
    name character varying(255),
    password character varying(255),
    CONSTRAINT account_profile_fk FOREIGN KEY (profile_id)
        REFERENCES EN_PROFILE (profile_id)
);

-- ROLE
CREATE TABLE IF NOT EXISTS EN_ROLE
(
    role_id serial PRIMARY KEY,
    name varchar(50) NOT NULL UNIQUE,
    description varchar(255)
);

-- ACCESS TOKEN
CREATE TABLE IF NOT EXISTS EN_ACCESS_TOKEN
(
    access_token_id serial PRIMARY KEY,
    account_id integer NOT NULL,
    expired boolean NOT NULL DEFAULT FALSE,
    revoked boolean NOT NULL DEFAULT FALSE,
    token varchar(255) NOT NULL UNIQUE,
    CONSTRAINT access_token_fkey_account FOREIGN KEY (account_id)
        REFERENCES EN_ACCOUNT (account_id)
);

-- NxN Profle x Role
CREATE TABLE IF NOT EXISTS RL_PROFILE_ROLE
(
    profile_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT rl_fk_profile_role FOREIGN KEY (profile_id)
        REFERENCES EN_PROFILE (profile_id),
    CONSTRAINT rl_profile_fk_role FOREIGN KEY (role_id)
        REFERENCES EN_ROLE (role_id)
);

-- NxN Role x Permission
CREATE TABLE IF NOT EXISTS RL_ROLE_PERMISSION
(
    role_id integer NOT NULL,
    permission_id integer NOT NULL,
    CONSTRAINT rl_role_permission_pk PRIMARY KEY (role_id, permission_id),
    CONSTRAINT rl_role_fk_permission FOREIGN KEY (permission_id)
        REFERENCES EN_PERMISSION (permission_id),
    CONSTRAINT rl_fk_role_permission FOREIGN KEY (role_id)
        REFERENCES EN_ROLE (role_id)
);

-- EN_CHANGE_PASSWORD
drop table EN_CHANGE_PASSWORD
CREATE TABLE IF NOT EXISTS EN_CHANGE_PASSWORD
(
    change_password_id serial PRIMARY KEY,
    account_id integer NOT NULL,
    url_secret varchar unique,
    status BOOLEAN,
    old_password varchar,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT change_password_fkey_account FOREIGN KEY (account_id)
        REFERENCES EN_ACCOUNT (account_id)
);

-- VIEW ACCOUNT PERMISSION
drop view VW_ACCOUNT_PERMISSION;

create or replace VIEW VW_ACCOUNT_PERMISSION AS
select acc.account_id, acc.email, acc.name, acc.password, acc.profile_id, array_agg(per.name) as permissions
from EN_ACCOUNT acc
    INNER JOIN EN_PROFILE USING(profile_id)
    INNER JOIN RL_PROFILE_ROLE USING (profile_id)
    INNER JOIN EN_ROLE USING(role_id)
    INNER JOIN RL_ROLE_PERMISSION USING (role_id)
    INNER JOIN EN_PERMISSION per USING(permission_id)
GROUP BY acc.account_id, acc.email, acc.name, acc.password;

select * from VW_ACCOUNT_PERMISSION;

-- REVISAR 

-- INSERIR PERMISSOES
insert into EN_PERMISSION (name) values ('feat-one::create');
insert into EN_PERMISSION (name) values ('feat-one::read');
insert into EN_PERMISSION (name) values ('feat-one::update');
insert into EN_PERMISSION (name) values ('feat-one::delete');
insert into EN_PERMISSION (name) values ('feat-two::create');
insert into EN_PERMISSION (name) values ('feat-two::read');
insert into EN_PERMISSION (name) values ('feat-two::update');
insert into EN_PERMISSION (name) values ('feat-two::delete');

-- INSERIR EN_ROLE
insert into EN_ROLE (name, description) values ('feat-one', 'Acessar todos os recursos da feat-one');
insert into EN_ROLE (name, description) values ('feat-two', 'Acessar todos os recursos da feat-two');

-- INSERIR RL_ROLE_PERMISSION
insert into RL_ROLE_PERMISSION (role_id, permission_id)
select 1,permission_id from EN_PERMISSION where name like 'feat-one%';

insert into RL_ROLE_PERMISSION (role_id, permission_id)
select 2,permission_id from EN_PERMISSION where name like 'feat-two%'
commit;

-- INSERIR EN_PROFILE
insert into EN_PROFILE (name, description) values ('admin', 'Acesso administrador no sistema');
insert into EN_PROFILE (name, description) values ('default', 'Usuario padrao do sistema');

-- INSERIR RL_PROFILE_ROLE role_id, permission_id
insert into RL_PROFILE_ROLE (profile_id, role_id)
select 1, role_id from EN_ROLE;

insert into RL_PROFILE_ROLE (profile_id, role_id) values (2,1);
commit;

insert into EN_ACCOUNT (name, password, email, profile_id) values
    ('Admin Teste', '$2a$10$rEsLInD6TNpf61pPC.nrWupZYUq4L2CXyWBumlmUtIgzF9TcfOOU.', 'admin@mail.com', 1);
insert into EN_ACCOUNT (name, password, email, profile_id) values
    ('User Teste', '$2a$10$rEsLInD6TNpf61pPC.nrWupZYUq4L2CXyWBumlmUtIgzF9TcfOOU.', 'user@mail.com', 2);


UPDATE en_account set PASSWORD='$2a$10$rEsLInD6TNpf61pPC.nrWupZYUq4L2CXyWBumlmUtIgzF9TcfOOU.'