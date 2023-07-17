package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import java.math.BigDecimal

data class DtoSalvarProdutos(
    val nomeProduto: String,
    val descricaoProduto: String,
    val unMedida: String,
    val estoque: BigDecimal,
    val preco: BigDecimal,
    val categorias_id: Long
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
