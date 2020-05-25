create table cliente (
    id bigint not null auto_increment primary key,
    nome varchar(200) not null,
    whatsapp varchar(11),
    cpf char(11) not null,
    numero_calcado varchar(3),
    numero_jeans varchar(3),
    nascimento datetime,
    data_registro datetime not null,
    id_endereco bigint not null
);

alter table cliente add constraint fk_cliente_endereco
foreign key (id_endereco) references endereco (id);