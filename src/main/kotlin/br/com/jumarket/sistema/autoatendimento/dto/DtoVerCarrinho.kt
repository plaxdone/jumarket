package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Carrinho
import br.com.jumarket.sistema.autoatendimento.enumeracao.FormaPagamento
import java.util.*

data class DtoVerCarrinho(
    val id: Long?,
    val formaPagamento: FormaPagamento
) {
    constructor(carrinho: Carrinho): this (
        formaPagamento = carrinho.formaPagamento,
        id = carrinho.id
    )
}
