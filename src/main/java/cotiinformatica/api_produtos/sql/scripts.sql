CREATE TABLE produtos(
    id                  UUID PRIMARY KEY,
    nome                VARCHAR(150) NOT NULL,
    preco               DECIMAL(10,2) NOT NULL,
    quantidade          INT NOT NULL,
    datahoracadastro    TIMESTAMP NOT NULL,
    ativo               BOOLEAN NOT NULL
);