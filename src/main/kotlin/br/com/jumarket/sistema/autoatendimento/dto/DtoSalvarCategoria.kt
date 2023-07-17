package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias

class DtoSalvarCategoria(
    val nomeCategoria: String,
    val descricaoCategoria: String
) {
    fun paraEntidade(): Categorias = Categorias(
        nomeCategoria = this.nomeCategoria,
        descricaoCategoria = this.descricaoCategoria
    )
}
