package br.com.jumarket.sistema.autoatendimento.dto

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import io.swagger.v3.oas.annotations.Hidden
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull

@Hidden
class DtoSalvarCategoria(
    @field:NotEmpty(message = "Digite um nome") val nomeCategoria: String,
    val descricaoCategoria: String
) {
    fun paraEntidade(): Categorias = Categorias(
        nomeCategoria = this.nomeCategoria,
        descricaoCategoria = this.descricaoCategoria
    )
}
