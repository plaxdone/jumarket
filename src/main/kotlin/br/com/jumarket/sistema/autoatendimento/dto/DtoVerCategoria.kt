package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias

data class DtoVerCategoria(
    val nomeCategoria: String,
    val descricaoCategoria: String
) {
    constructor(categorias: Categorias): this (
        nomeCategoria = categorias.nomeCategoria,
        descricaoCategoria = categorias.descricaoCategoria
    )
}
