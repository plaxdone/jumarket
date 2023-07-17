CREATE TABLE produtos (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome_produto VARCHAR(255) NOT NULL,
   descricao_produto VARCHAR(255) NULL,
   un_medida VARCHAR(255) NOT NULL,
   estoque DECIMAL NOT NULL,
   preco DECIMAL NOT NULL,
   categorias_id BIGINT NULL,
   CONSTRAINT pk_produtos PRIMARY KEY (id)
);

ALTER TABLE produtos ADD CONSTRAINT uc_produtos_nomeproduto UNIQUE (nome_produto);

ALTER TABLE produtos ADD CONSTRAINT FK_PRODUTOS_ON_CATEGORIAS FOREIGN KEY (categorias_id) REFERENCES categorias (id);