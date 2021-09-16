create table cliente (
    id    bigint auto_increment primary key,
    email varchar(255) not null,
    nome  varchar(255) not null
);

create table pet (
    id    bigint auto_increment primary key,
    nome  varchar(255) not null,
    id_cliente bigint  not null,
    constraint FK_PET_CLIENTE foreign key (id_cliente) references cliente (id)
);
