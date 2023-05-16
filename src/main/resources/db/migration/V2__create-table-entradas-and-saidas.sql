create table entradas(
    numero bigint not null auto_increment,
    data_entrada date not null,

    primary key (numero)
);

create table itens_entrada(
    numero_entrada bigint not null,
    id_produto bigint not null,
    quantidade_produto integer not null,

    primary key (numero_entrada, id_produto),
    foreign key (numero_entrada) references entradas (numero),
    foreign key (id_produto) references produtos (id)

);

create table saidas(
    numero bigint not null auto_increment,
    data_saida date not null,

    primary key (numero)
);

create table itens_saida(
    numero_saida bigint not null,
    id_produto bigint not null,
    quantidade_produto integer not null,

    primary key (numero_saida, id_produto),
    foreign key (numero_saida) references saidas (numero),
    foreign key (id_produto) references produtos (id)

);