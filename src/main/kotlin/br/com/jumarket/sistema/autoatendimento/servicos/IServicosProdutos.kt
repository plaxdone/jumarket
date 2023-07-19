package br.com.jumarket.sistema.autoatendimento.servicos

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import br.com.jumarket.sistema.autoatendimento.entidade.Produtos

interface IServicosProdutos {
    fun salvarProduto(produtos: Produtos): Produtos

    fun consultarProduto(id: Long): Produtos

    fun consultarPorCategoria(categoriasId: Long): List<Produtos>

    fun consultarTodosProdutos(): List<Produtos>

    fun apagarProduto(id: Long)
}