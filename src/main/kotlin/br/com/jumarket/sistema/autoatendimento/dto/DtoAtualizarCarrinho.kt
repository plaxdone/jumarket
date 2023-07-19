package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Carrinho
import br.com.jumarket.sistema.autoatendimento.enumeracao.FormaPagamento
import java.util.*

data class DtoAtualizarCarrinho(
    val formaPagamento: FormaPagamento
) {
    fun paraEntidade(carrinho: Carrinho): Carrinho {
        carrinho.formaPagamento = this.formaPagamento
        return carrinho
    }
}
