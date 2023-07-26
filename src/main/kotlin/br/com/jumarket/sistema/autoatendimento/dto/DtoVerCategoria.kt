package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import io.swagger.v3.oas.annotations.Hidden

data class DtoVerCategoria(
    val id: Long?,
    val nomeCategoria: String,
    val descricaoCategoria: String
) {
    constructor(categorias: Categorias): this (
        id = categorias.id,
        nomeCategoria = categorias.nomeCategoria,
        descricaoCategoria = categorias.descricaoCategoria
    )
}
