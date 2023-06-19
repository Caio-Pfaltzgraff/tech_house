CREATE TABLE MOVIMENTO (
                numero BIGINT AUTO_INCREMENT NOT NULL,
                data DATE NOT NULL,
                valor DECIMAL(10,2) NOT NULL,
                tipo VARCHAR(100) NOT NULL,

                PRIMARY KEY (numero)
);


CREATE TABLE ITEM_MOVIMENTO (
                numero_movimento BIGINT NOT NULL,
                id_produto BIGINT NOT NULL,
                quantidade INT NOT NULL,
                valor DECIMAL(10,2) NOT NULL,

                PRIMARY KEY (numero_movimento, id_produto),
                FOREIGN KEY (numero_movimento) REFERENCES MOVIMENTO (numero),
                FOREIGN KEY (id_produto) REFERENCES PRODUTOS (id)

);