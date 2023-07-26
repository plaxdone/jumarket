package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import io.swagger.v3.oas.annotations.Hidden
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

@Hidden
data class DtoSalvarProdutos(
    @field:NotEmpty(message = "Digite um nome") val nomeProduto: String,
    val descricaoProduto: String,
    @field:NotEmpty(message = "Digite a unidade de medida") val unMedida: String,
    @field:NotNull() val estoque: BigDecimal,
    @field:NotNull() val preco: BigDecimal,
    @field:NotNull() val categorias_id: Long
) {
    fun paraEntidade() :Produtos = Produtos(
        nomeProduto = this.nomeProduto,
        descricaoProduto = this.descricaoProduto,
        unMedida = this.unMedida,
        estoque = this.estoque,
        preco = this.preco,
        categorias = Categorias(id = this.categorias_id)
    )
}
