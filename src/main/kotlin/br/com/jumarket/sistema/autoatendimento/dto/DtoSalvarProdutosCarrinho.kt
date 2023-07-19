package br.com.jumarket.sistema.autoatendimento.dto


import br.com.jumarket.sistema.autoatendimento.entidade.Carrinho
import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import br.com.jumarket.sistema.autoatendimento.entidade.ProdutosCarrinho
import jakarta.persistence.ManyToOne
import java.math.BigDecimal

data class DtoSalvarProdutosCarrinho(
    val produtoNome: String,
    val produtoPreco: BigDecimal,
    val quantidade: BigDecimal,
    val total: BigDecimal,
    val carrinho_id: Long
) {
    fun paraEntidade(): ProdutosCarrinho = ProdutosCarrinho(
        produtoNome = this.produtoNome,
        produtoPreco = this.produtoPreco,
        quantidade = this.quantidade,
        total = this.total,
        carrinho = Carrinho(id = this.carrinho_id)
    )
}
