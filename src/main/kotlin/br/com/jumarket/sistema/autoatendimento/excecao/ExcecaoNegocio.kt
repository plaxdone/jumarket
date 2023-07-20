package br.com.jumarket.sistema.autoatendimento.excecao

import java.lang.RuntimeException

data class ExcecaoNegocio(override val message: String?): RuntimeException(message) {
}