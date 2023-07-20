package br.com.jumarket.sistema.autoatendimento.excecao

import java.time.LocalDateTime

data class DetalheExcecoes (
    val titulo: String,
    val horario: LocalDateTime,
    val status: Int,
    val excecao: String,
    val detalhes: MutableMap<String, String?>
){
}