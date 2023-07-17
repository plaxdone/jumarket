CREATE TABLE categorias (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome_categoria VARCHAR(255) NOT NULL,
   descricao_categoria VARCHAR(255) NULL,
   CONSTRAINT pk_categorias PRIMARY KEY (id)
);

ALTER TABLE categorias ADD CONSTRAINT uc_categorias_nomecategoria UNIQUE (nome_categoria);