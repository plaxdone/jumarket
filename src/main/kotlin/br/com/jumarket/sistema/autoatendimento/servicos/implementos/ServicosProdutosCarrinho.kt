package br.com.jumarket.sistema.autoatendimento.servicos.implementos

import br.com.jumarket.sistema.autoatendimento.entidade.ProdutosCarrinho
import br.com.jumarket.sistema.autoatendimento.excecao.ExcecaoNegocio
import br.com.jumarket.sistema.autoatendimento.repositorio.RepositorioProdutosCarrinho
import br.com.jumarket.sistema.autoatendimento.servicos.IServicosProdutosCarrinho
import org.springframework.stereotype.Service

@Service
class ServicosProdutosCarrinho(
    private val repositorioProdutosCarrinho: RepositorioProdutosCarrinho, private val servicosCarrinho: ServicosCarrinho
) : IServicosProdutosCarrinho {
    override fun salvarProdutosCarrinho(produtosCarrinho: ProdutosCarrinho): ProdutosCarrinho {
        produtosCarrinho.apply {
            carrinho = servicosCarrinho.consultarCarrinho(produtosCarrinho.carrinho?.id!!)
        }
        return this.repositorioProdutosCarrinho.save(produtosCarrinho)
    }

    override fun consultarProdutoCarrinho(id: Long): ProdutosCarrinho = this.repositorioProdutosCarrinho.findById(id).orElseThrow {
        throw ExcecaoNegocio("Id $id não encontrada!")
    }

    override fun consultarPorCarrinho(carrinhoId: Long): List<ProdutosCarrinho> =
        this.repositorioProdutosCarrinho.consultarPorCarrinho(carrinhoId)

    override fun apagarProdutoCarrinho(id: Long){
        val produtosCarrinho: ProdutosCarrinho = this.consultarProdutoCarrinho(id)
        this.repositorioProdutosCarrinho.delete(produtosCarrinho)
    }
    override fun finalizarCarrinho(id: Long) {
    }
}