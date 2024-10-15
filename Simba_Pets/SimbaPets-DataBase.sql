use simba_pets;

CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo BIGINT,
    nome VARCHAR(300),
    marca VARCHAR(300),
    categoria VARCHAR(300),
    tipoPeso VARCHAR(2),
    peso DECIMAL(10,3),
    preco_fornecimento DECIMAL(10,2),
    preco_revenda DECIMAL(10,2),
    estoque INT,						
    venda_granel BOOLEAN,
    porcentagem_lucro DECIMAL(10,2),
    descricao TEXT
);

CREATE TABLE vendas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    valor_total DECIMAL(10,2),
    forma_pagamento VARCHAR(100),
    data_venda DATE
);

CREATE TABLE venda_produto (
    venda_id INT,
    produto_id INT,
    quantidade INT,
    PRIMARY KEY (venda_id, produto_id),
    FOREIGN KEY (venda_id) REFERENCES vendas(id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
);

CREATE TABLE fornecedores (
    id INT AUTO_INCREMENT,
    nomeEmpresa VARCHAR(300),
    cnpj VARCHAR(18),
    rua VARCHAR(300),
    bairro VARCHAR(300),
    numero VARCHAR(5),
    cidade VARCHAR(300),
    estado VARCHAR(300),
    nomeVendedor VARCHAR(300),
    telefone VARCHAR(15),
    email VARCHAR(300),
    constraint pk_fornecedores primary key(id) -- ele cria um objeto que referencia o id na tabela
);

CREATE TABLE fornecedor_produto (
    fornecedor_id INT,
    produto_id INT,
    PRIMARY KEY (fornecedor_id, produto_id),
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
);

CREATE TABLE contas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    beneficiario VARCHAR(300),
    data_vencimento DATE,
    valor DECIMAL(10,2),
    status_conta VARCHAR(50)
);
