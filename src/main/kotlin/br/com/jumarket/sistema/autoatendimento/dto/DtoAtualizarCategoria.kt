package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias

data class DtoAtualizarCategoria(
    val nomeCategoria: String,
    val descricaoCategoria: String
) {
    fun paraEntidade(categorias: Categorias): Categorias {
        categorias.nomeCategoria = this.nomeCategoria
        categorias.descricaoCategoria = this.descricaoCategoria
        return categorias
    }
}
