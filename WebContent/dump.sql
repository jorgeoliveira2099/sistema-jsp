--banco de dados
CREATE DATABASE if not exists sistemajsp;

--tabela
CREATE TABLE usuario(
	id bigint(11) NOT NULL AUTO INCREMENT PRIMARY KEY,
	nome varchar(255) not null,
	login varchar(255) unique not null,
	senha varchar(255) not null,
	telefone varchar(255) not null,
	data_criacao  TIMESTAMP CURRENT_TIMESTAMP
	
	
);


ALTER TABLE usuario  add COLUMN cep VARCHAR(8);

ALTER TABLE usuario  add COLUMN rua VARCHAR(255);

ALTER TABLE usuario  add COLUMN bairro VARCHAR(255);

ALTER TABLE usuario  add COLUMN cidade VARCHAR(255);

ALTER TABLE usuario  add COLUMN estado VARCHAR(3);


CREATE TABLE telefone(
	id bigint(11) NOT NULL AUTO INCREMENT PRIMARY KEY,
	numero varchar(15) not null,
	tipo varchar(255) not null,
	usuario bigint not null,
	
);
ALTER TABLE usuario ADD COLUMN fotobase64 longblob

ALTER TABLE usuario ADD COLUMN contenttype VARCHAR(255)


ALTER TABLE usuario ADD COLUMN curriculobase64 longblob

ALTER TABLE usuario ADD COLUMN contenttypecurriculo VARCHAR(255)


