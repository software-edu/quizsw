CREATE TABLE assunto (
 cd_assunto INT NOT NULL,
 nm_assunto VARCHAR(64),
 cd_assunto_superior INT
);

ALTER TABLE assunto ADD CONSTRAINT PK_assunto PRIMARY KEY (cd_assunto);


CREATE TABLE nivel (
 cd_nivel INT NOT NULL,
 nm_nivel VARCHAR(64)
);

ALTER TABLE nivel ADD CONSTRAINT PK_nivel PRIMARY KEY (cd_nivel);


CREATE TABLE quiz (
 cd_quiz INT NOT NULL,
 nm_quiz VARCHAR(64),
 qtd_tempo INT,
 qtd_erro INT
);

ALTER TABLE quiz ADD CONSTRAINT PK_quiz PRIMARY KEY (cd_quiz);


CREATE TABLE pergunta (
 cd_pergunta INT NOT NULL,
 txt_pergunta VARCHAR(256),
 cd_assunto INT,
 cd_nivel INT
);

ALTER TABLE pergunta ADD CONSTRAINT PK_pergunta PRIMARY KEY (cd_pergunta);


CREATE TABLE quiz_pergunta (
 cd_quiz INT NOT NULL,
 cd_pergunta INT NOT NULL
);

ALTER TABLE quiz_pergunta ADD CONSTRAINT PK_quiz_pergunta PRIMARY KEY (cd_quiz,cd_pergunta);


CREATE TABLE resposta (
 cd_resposta INT NOT NULL,
 cd_pergunta INT NOT NULL,
 txt_resposta VARCHAR(256),
 lg_correto SMALLINT,
 blb_imagem BYTEA
);

ALTER TABLE resposta ADD CONSTRAINT PK_resposta PRIMARY KEY (cd_resposta,cd_pergunta);


ALTER TABLE assunto ADD CONSTRAINT FK_assunto_0 FOREIGN KEY (cd_assunto_superior) REFERENCES assunto (cd_assunto);


ALTER TABLE pergunta ADD CONSTRAINT FK_pergunta_0 FOREIGN KEY (cd_assunto) REFERENCES assunto (cd_assunto);
ALTER TABLE pergunta ADD CONSTRAINT FK_pergunta_1 FOREIGN KEY (cd_nivel) REFERENCES nivel (cd_nivel);


ALTER TABLE quiz_pergunta ADD CONSTRAINT FK_quiz_pergunta_0 FOREIGN KEY (cd_quiz) REFERENCES quiz (cd_quiz);
ALTER TABLE quiz_pergunta ADD CONSTRAINT FK_quiz_pergunta_1 FOREIGN KEY (cd_pergunta) REFERENCES pergunta (cd_pergunta);


ALTER TABLE resposta ADD CONSTRAINT FK_resposta_0 FOREIGN KEY (cd_pergunta) REFERENCES pergunta (cd_pergunta);


