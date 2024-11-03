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

-- RULE
CREATE TABLE IF NOT EXISTS EN_RULE
(
    rule_id serial PRIMARY KEY,
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
CREATE TABLE IF NOT EXISTS RL_PROFILE_RULE
(
    profile_id integer NOT NULL,
    rule_id integer NOT NULL,
    CONSTRAINT rl_fk_profile_rule FOREIGN KEY (profile_id)
        REFERENCES EN_PROFILE (profile_id),
    CONSTRAINT rl_profile_fk_rule FOREIGN KEY (rule_id)
        REFERENCES EN_RULE (rule_id)
);

-- NxN Role x Permission
CREATE TABLE IF NOT EXISTS RL_RULE_PERMISSION
(
    rule_id integer NOT NULL,
    permission_id integer NOT NULL,
    CONSTRAINT rl_rule_permission_pk PRIMARY KEY (rule_id, permission_id),
    CONSTRAINT rl_rule_fk_permission FOREIGN KEY (permission_id)
        REFERENCES EN_PERMISSION (permission_id),
    CONSTRAINT rl_fk_rule_permission FOREIGN KEY (rule_id)
        REFERENCES EN_RULE (rule_id)
);

-- EN_CHANGE_PASSWORD
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
create or replace VIEW VW_ACCOUNT_PERMISSION AS
select acc.account_id, acc.email, acc.name, acc.password, acc.profile_id, array_agg(per.name) as permissions
from EN_ACCOUNT acc
    INNER JOIN EN_PROFILE USING(profile_id)
    INNER JOIN RL_PROFILE_RULE USING (profile_id)
    INNER JOIN EN_RULE USING(rule_id)
    INNER JOIN RL_RULE_PERMISSION USING (rule_id)
    INNER JOIN EN_PERMISSION per USING(permission_id)
GROUP BY acc.account_id, acc.email, acc.name, acc.password;
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

insert into EN_PERMISSION (name) values ('feat-three::create');
insert into EN_PERMISSION (name) values ('feat-three::read');
insert into EN_PERMISSION (name) values ('feat-three::update');
insert into EN_PERMISSION (name) values ('feat-three::delete');

insert into EN_PERMISSION (name) values ('feat-four::create');
insert into EN_PERMISSION (name) values ('feat-four::read');
insert into EN_PERMISSION (name) values ('feat-four::update');
insert into EN_PERMISSION (name) values ('feat-four::delete');

insert into EN_PERMISSION (name) values ('feat-five::create');
insert into EN_PERMISSION (name) values ('feat-five::read');
insert into EN_PERMISSION (name) values ('feat-five::update');
insert into EN_PERMISSION (name) values ('feat-five::delete');

insert into EN_PERMISSION (name) values ('feat-six::create');
insert into EN_PERMISSION (name) values ('feat-six::read');
insert into EN_PERMISSION (name) values ('feat-six::update');
insert into EN_PERMISSION (name) values ('feat-six::delete');

insert into EN_PERMISSION (name) values ('feat-seven::create');
insert into EN_PERMISSION (name) values ('feat-seven::read');
insert into EN_PERMISSION (name) values ('feat-seven::update');
insert into EN_PERMISSION (name) values ('feat-seven::delete');

insert into EN_PERMISSION (name) values ('feat-eight::create');
insert into EN_PERMISSION (name) values ('feat-eight::read');
insert into EN_PERMISSION (name) values ('feat-eight::update');
insert into EN_PERMISSION (name) values ('feat-eight::delete');



-- INSERIR EN_RULE
insert into EN_RULE (name, description) values ('feat-one', 'Acessar todos os recursos da feat-one');
insert into EN_RULE (name, description) values ('feat-two', 'Acessar todos os recursos da feat-two');

-- INSERIR RL_RULE_PERMISSION
insert into RL_RULE_PERMISSION (rule_id, permission_id)
select 1,permission_id from EN_PERMISSION where name like 'feat-one%';

insert into RL_RULE_PERMISSION (rule_id, permission_id)
select 2,permission_id from EN_PERMISSION where name like 'feat-two%'
commit;

-- INSERIR EN_PROFILE
insert into EN_PROFILE (name, description) values ('admin', 'Acesso administrador no sistema');
insert into EN_PROFILE (name, description) values ('default', 'Usuario padrao do sistema');

-- INSERIR RL_PROFILE_RULE rule_id, permission_id
insert into RL_PROFILE_RULE (profile_id, rule_id)
select 1, rule_id from EN_RULE;

insert into RL_PROFILE_RULE (profile_id, rule_id) values (2,1);

insert into EN_ACCOUNT (name, password, email, profile_id) values
    ('Admin Teste', '$2a$10$rEsLInD6TNpf61pPC.nrWupZYUq4L2CXyWBumlmUtIgzF9TcfOOU.', 'admin@mail.com', 1);
insert into EN_ACCOUNT (name, password, email, profile_id) values
    ('User Teste', '$2a$10$rEsLInD6TNpf61pPC.nrWupZYUq4L2CXyWBumlmUtIgzF9TcfOOU.', 'user@mail.com', 2);


UPDATE en_account set PASSWORD='$2a$10$rEsLInD6TNpf61pPC.nrWupZYUq4L2CXyWBumlmUtIgzF9TcfOOU.'