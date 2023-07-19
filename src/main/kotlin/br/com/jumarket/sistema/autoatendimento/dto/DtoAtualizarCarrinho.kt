package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Carrinho
import java.util.*

data class DtoAtualizarCarrinho(
    val codigoCarrinho: UUID
) {
    fun paraEntidade(carrinho: Carrinho): Carrinho {

        return carrinho
    }
}
