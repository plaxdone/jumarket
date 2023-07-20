package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

data class DtoAtualizarProduto(
    @field:NotEmpty(message = "Digite um nome") val nomeProduto: String,
    val descricaoProduto: String,
    @field:NotEmpty(message = "Digite a unidade de medida") val unMedida: String,
    @field:NotNull() val estoque: BigDecimal,
    @field:NotNull() val preco: BigDecimal,
    @field:NotNull() val categorias_id: Long
) {
    fun paraEntidade(produtos: Produtos): Produtos {
        produtos.nomeProduto = this.nomeProduto
        produtos.descricaoProduto = this.descricaoProduto
        produtos.unMedida = this.unMedida
        produtos.estoque = this.estoque
        produtos.preco = this.preco
        produtos.categorias = Categorias(id = this.categorias_id)
        return produtos
    }

}
