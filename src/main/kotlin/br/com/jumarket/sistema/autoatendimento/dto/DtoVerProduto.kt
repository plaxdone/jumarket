package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import io.swagger.v3.oas.annotations.Hidden
import java.math.BigDecimal

data class DtoVerProduto(
    val nomeProduto: String,
    val descricaoProduto: String,
    val unMedida: String,
    val estoque: BigDecimal,
    val preco: BigDecimal,
    val categorias_id: Long?
) {
    constructor(produtos: Produtos): this (
        nomeProduto = produtos.nomeProduto,
        descricaoProduto = produtos.descricaoProduto,
        unMedida = produtos.unMedida,
        estoque = produtos.estoque,
        preco = produtos.preco,
        categorias_id = produtos.categorias?.id
    )

}
