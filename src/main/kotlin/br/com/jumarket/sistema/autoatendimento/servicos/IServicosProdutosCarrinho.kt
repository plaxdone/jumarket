package br.com.jumarket.sistema.autoatendimento.servicos

import br.com.jumarket.sistema.autoatendimento.entidade.ProdutosCarrinho

interface IServicosProdutosCarrinho {
    fun salvarProdutosCarrinho(produtosCarrinho: ProdutosCarrinho): ProdutosCarrinho

    fun consultarPorCarrinho(id: Long): List<ProdutosCarrinho>

    fun apagarProdutoCarrinho(id: Long)
}