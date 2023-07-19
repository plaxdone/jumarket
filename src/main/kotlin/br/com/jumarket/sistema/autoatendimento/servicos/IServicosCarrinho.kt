package br.com.jumarket.sistema.autoatendimento.servicos

import br.com.jumarket.sistema.autoatendimento.entidade.Carrinho
import java.util.UUID

interface IServicosCarrinho {

    fun salvarCarrinho(carrinho: Carrinho): Carrinho

    fun consultarCarrinho(id: Long): Carrinho

    fun apagarCarrinho(id: Long)
}