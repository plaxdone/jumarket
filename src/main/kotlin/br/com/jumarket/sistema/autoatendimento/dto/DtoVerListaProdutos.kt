package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import java.math.BigDecimal

data class DtoVerListaProdutos(
    val nomeProduto: String,
    val descricaoProduto: String,
    val unMedida: String,
    val estoque: BigDecimal,
    val preco: BigDecimal
) {
    constructor(produtos: Produtos): this(
        nomeProduto = produtos.nomeProduto,
        descricaoProduto = produtos.descricaoProduto,
        unMedida = produtos.unMedida,
        estoque = produtos.estoque,
        preco = produtos.preco
    )
}
