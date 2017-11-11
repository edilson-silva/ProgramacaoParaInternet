-- create database Acervo;

create table filme(
	id bigserial primary key,
	titulo varchar(150),
	genero varchar(100),
	data_lancamento date,
	duracao varchar(7),
	nota_imdb integer
);