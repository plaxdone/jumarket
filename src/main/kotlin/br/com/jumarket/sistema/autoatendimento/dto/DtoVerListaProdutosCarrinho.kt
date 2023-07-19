package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.ProdutosCarrinho
import java.math.BigDecimal

class DtoVerListaProdutosCarrinho(
    val produtoNome: String,
    val produtoPreco: BigDecimal,
    val quantidade: BigDecimal,
    val total: BigDecimal
) {
    constructor(produtosCarrinho: ProdutosCarrinho): this(
        produtoNome = produtosCarrinho.produtoNome,
        produtoPreco = produtosCarrinho.produtoPreco,
        quantidade = produtosCarrinho.quantidade,
        total = produtosCarrinho.total
    )
}
