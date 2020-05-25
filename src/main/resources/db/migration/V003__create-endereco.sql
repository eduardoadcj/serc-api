create table estado (
    id bigint not null auto_increment primary key,
    nome varchar(200) not null,
    uf char(2) not null
);

create table cidade (
    id bigint not null auto_increment primary key,
    nome varchar(200) not null,
    id_estado bigint not null
);

alter table cidade add constraint fk_cidade_estado
foreign key (id_estado) references estado (id);

create table endereco (
    id bigint not null auto_increment primary key,
    titulo varchar(200) not null,
    rua varchar(255) not null,
    bairro varchar(255) not null,
    numero varchar(20) not null,
    cep char(8) not null,
    id_cidade bigint not null,
    id_cliente bigint not null
);

alter table endereco add constraint fk_endereco_cidade
foreign key (id_cidade) references cidade (id);

alter table endereco add constraint fk_endereco_cliente
foreign key (id_cliente) references cliente (id);