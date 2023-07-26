package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Carrinho
import br.com.jumarket.sistema.autoatendimento.enumeracao.FormaPagamento
import io.swagger.v3.oas.annotations.Hidden
import org.jetbrains.annotations.NotNull
import java.util.*

@Hidden
data class DtoAtualizarCarrinho(
    @field:NotNull() val formaPagamento: FormaPagamento
) {
    fun paraEntidade(carrinho: Carrinho): Carrinho {
        carrinho.formaPagamento = this.formaPagamento
        return carrinho
    }
}
