package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import java.math.BigDecimal

data class DtoAtualizarProduto(
    val nomeProduto: String,
    val descricaoProduto: String,
    val unMedida: String,
    val estoque: BigDecimal,
    val preco: BigDecimal,
    val categorias_id: Long
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
