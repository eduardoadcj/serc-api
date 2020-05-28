create table usuario (
    id bigint not null auto_increment primary key,
    uid varchar(20) not null,
    nome varchar(200) not null
);

create table cliente (
    id bigint not null auto_increment primary key,
    nome varchar(200) not null,
    whatsapp varchar(11),
    email varchar(255),
    cpf char(11) not null,
    numero_calcado varchar(3),
    numero_jeans varchar(3),
    nascimento datetime,
    data_registro datetime not null,
    id_usuario bigint not null
);

alter table cliente add constraint fk_cliente_usuario
foreign key (id_usuario) references usuario(id);

create table endereco (
    id bigint not null auto_increment primary key,
    titulo varchar(200) not null,
    rua varchar(255) not null,
    bairro varchar(255) not null,
    numero varchar(20) not null,
    cep char(8) not null,
    cidade varchar(255) not null,
    estado varchar(255) not null,
    estado_uf char(2),
    id_cliente bigint not null
);

alter table endereco add constraint fk_endereco_cliente
foreign key (id_cliente) references cliente (id);