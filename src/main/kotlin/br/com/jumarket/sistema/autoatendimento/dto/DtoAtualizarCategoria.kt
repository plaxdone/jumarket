package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import jakarta.validation.constraints.NotEmpty

data class DtoAtualizarCategoria(
    @field:NotEmpty(message = "Digite um nome") val nomeCategoria: String,
    val descricaoCategoria: String
) {
    fun paraEntidade(categorias: Categorias): Categorias {
        categorias.nomeCategoria = this.nomeCategoria
        categorias.descricaoCategoria = this.descricaoCategoria
        return categorias
    }
}
