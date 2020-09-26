--banco de dados
CREATE DATABASE if not exists sistemajsp;

--tabela
CREATE TABLE usuario(
	id bigint(11) NOT NULL AUTO INCREMENT PRIMARY KEY,
	nome varchar(255) not null,
	login varchar(255) unique not null,
	senha varchar(255) not null,
	telefone varchar(255) not null,
	data_creacao  TIMESTAMP CURRENT_TIMESTAMP
	
	
);