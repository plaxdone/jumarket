package br.com.jumarket.sistema.autoatendimento.servicos.implementos

import br.com.jumarket.sistema.autoatendimento.entidade.Carrinho
import br.com.jumarket.sistema.autoatendimento.excecao.ExcecaoNegocio
import br.com.jumarket.sistema.autoatendimento.repositorio.RepositorioCarrinho
import br.com.jumarket.sistema.autoatendimento.servicos.IServicosCarrinho
import org.springframework.stereotype.Service
import java.util.*

@Service
class ServicosCarrinho(
    private val repositorioCarrinho: RepositorioCarrinho
) : IServicosCarrinho {
    override fun salvarCarrinho(carrinho: Carrinho): Carrinho = this.repositorioCarrinho.save(carrinho)

    override fun consultarCarrinho(id: Long): Carrinho = this.repositorioCarrinho.findById(id).orElseThrow {
        throw ExcecaoNegocio("Carrinho $id não encontrado")
    }

    override fun apagarCarrinho(id: Long) = this.repositorioCarrinho.deleteById(id)
}