create table boilerplates
(
    id serial not null primary key,
    nome text not null,
    data_ultima_atualizacao timestamp without time zone not null
);
