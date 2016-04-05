ALTER TABLE quiz_pergunta ADD nr_ordem INTEGER;

ALTER TABLE quiz ADD blb_imagem BYTEA;

CREATE TABLE usuario (
 cd_usuario INT NOT NULL,
 nm_login VARCHAR(32),
 nm_senha VARCHAR(36),
 tp_permissao SMALLINT
);

ALTER TABLE usuario ADD CONSTRAINT PK_usuario PRIMARY KEY (cd_usuario);