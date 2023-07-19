CREATE TABLE produtos_carrinho (
  id BIGINT AUTO_INCREMENT NOT NULL,
   produto_id BIGINT NOT NULL,
   produto_nome BIGINT NOT NULL,
   produto_preco DECIMAL NULL,
   quantidade DECIMAL NULL,
   total DECIMAL NULL,
   carrinho_id BIGINT NULL,
   CONSTRAINT pk_produtoscarrinho PRIMARY KEY (id)
);

ALTER TABLE produtos_carrinho ADD CONSTRAINT FK_PRODUTOSCARRINHO_ON_CARRINHO FOREIGN KEY (carrinho_id) REFERENCES carrinho (id);