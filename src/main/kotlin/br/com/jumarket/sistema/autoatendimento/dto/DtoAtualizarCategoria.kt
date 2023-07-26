package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import io.swagger.v3.oas.annotations.Hidden
import jakarta.validation.constraints.NotEmpty

@Hidden
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
