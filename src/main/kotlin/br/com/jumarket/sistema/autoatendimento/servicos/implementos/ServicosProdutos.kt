package br.com.jumarket.sistema.autoatendimento.servicos.implementos

import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import br.com.jumarket.sistema.autoatendimento.repositorio.RepositorioProdutos
import br.com.jumarket.sistema.autoatendimento.servicos.IServicosProdutos
import org.springframework.stereotype.Service

@Service
class ServicosProdutos(
    private val repositorioProdutos: RepositorioProdutos, private val servicosCategorias: ServicosCategorias
) : IServicosProdutos {
    override fun salvarProduto(produtos: Produtos): Produtos {
        produtos.apply {
            categorias = servicosCategorias.consultarCategoria(produtos.categorias?.id!!)
        }
        return this.repositorioProdutos.save(produtos)
    }

    override fun consultarProduto(id: Long): Produtos = this.repositorioProdutos.findById(id).orElseThrow {
        throw RuntimeException("Id $id não encontrada!")
    }

    override fun consultarPorCategoria(categoriasId: Long): List<Produtos> =
        this.repositorioProdutos.consultarPorCategoria(categoriasId)

    override fun consultarTodosProdutos(): List<Produtos> = this.repositorioProdutos.consultarTodosProdutos()

    override fun apagarProduto(id: Long) = this.repositorioProdutos.deleteById(id)
}