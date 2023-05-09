create table produtos(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    preco decimal(10,2) not null,
    quantidade_estoque integer not null,
    quantidade_minima integer not null,
    quantidade_maxima integer not null,
    tipo_do_produto varchar(100) not null,

    primary key (id)

);