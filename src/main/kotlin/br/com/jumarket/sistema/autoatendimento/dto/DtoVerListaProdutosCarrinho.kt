package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.ProdutosCarrinho
import java.math.BigDecimal

class DtoVerListaProdutosCarrinho(
    val id: Long?,
    val produtoNome: String,
    val produtoPreco: BigDecimal,
    val quantidade: BigDecimal,
    val total: BigDecimal,
    val carrinho: Long?
) {
    constructor(produtosCarrinho: ProdutosCarrinho): this(
        id = produtosCarrinho.id,
        produtoNome = produtosCarrinho.produtoNome,
        produtoPreco = produtosCarrinho.produtoPreco,
        quantidade = produtosCarrinho.quantidade,
        total = produtosCarrinho.total,
        carrinho = produtosCarrinho.carrinho?.id
    )
}
