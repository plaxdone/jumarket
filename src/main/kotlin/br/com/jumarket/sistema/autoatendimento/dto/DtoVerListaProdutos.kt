package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import java.math.BigDecimal

data class DtoVerListaProdutos(
    val id: Long?,
    val nomeProduto: String,
    val descricaoProduto: String,
    val unMedida: String,
    val estoque: BigDecimal,
    val preco: BigDecimal,
    val categoria: Long?
) {
    constructor(produtos: Produtos): this(
        id = produtos.id,
        nomeProduto = produtos.nomeProduto,
        descricaoProduto = produtos.descricaoProduto,
        unMedida = produtos.unMedida,
        estoque = produtos.estoque,
        preco = produtos.preco,
        categoria = produtos.categorias?.id
    )
}
