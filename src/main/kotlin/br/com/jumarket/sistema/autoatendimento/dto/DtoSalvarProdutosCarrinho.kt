package br.com.jumarket.sistema.autoatendimento.dto


import br.com.jumarket.sistema.autoatendimento.entidade.Carrinho
import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import br.com.jumarket.sistema.autoatendimento.entidade.ProdutosCarrinho
import io.swagger.v3.oas.annotations.Hidden
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

@Hidden
data class DtoSalvarProdutosCarrinho(
    @field:NotEmpty(message = "Digite um nome") val produtoNome: String,
    @field:NotNull() val produtoPreco: BigDecimal,
    @field:NotNull() val quantidade: BigDecimal,
    @field:NotNull() val total: BigDecimal,
    @field:NotNull() val carrinho_id: Long
) {
    fun paraEntidade(): ProdutosCarrinho = ProdutosCarrinho(
        produtoNome = this.produtoNome,
        produtoPreco = this.produtoPreco,
        quantidade = this.quantidade,
        total = this.total,
        carrinho = Carrinho(id = this.carrinho_id)
    )
}
