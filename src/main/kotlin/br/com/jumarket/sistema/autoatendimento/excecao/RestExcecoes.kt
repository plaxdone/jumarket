package br.com.jumarket.sistema.autoatendimento.excecao

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExcecoes {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validException(ex: MethodArgumentNotValidException): ResponseEntity<DetalheExcecoes>{
        val erros: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.stream().forEach {
            erro: ObjectError ->
            val nome: String = (erro as FieldError).field
            val mensagem: String? = erro.defaultMessage
            erros[nome] = mensagem
        }
        return ResponseEntity(
            DetalheExcecoes(
                titulo = "Problemas",
                horario= LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                excecao = ex.javaClass.toString(),
                detalhes = erros
              ), HttpStatus.BAD_REQUEST
       )
    }

    @ExceptionHandler(DataAccessException::class)
    fun validException(ex: DataAccessException): ResponseEntity<DetalheExcecoes>{
        return ResponseEntity(
            DetalheExcecoes(
                titulo = "Problemas",
                horario= LocalDateTime.now(),
                status = HttpStatus.CONFLICT.value(),
                excecao = ex.javaClass.toString(),
                detalhes = mutableMapOf(ex.cause.toString() to ex.message)
            ), HttpStatus.CONFLICT
        )
    }

    @ExceptionHandler(ExcecaoNegocio::class)
    fun validException(ex: ExcecaoNegocio): ResponseEntity<DetalheExcecoes>{
        return ResponseEntity(
            DetalheExcecoes(
                titulo = "Problemas",
                horario= LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                excecao = ex.javaClass.toString(),
                detalhes = mutableMapOf(ex.cause.toString() to ex.message)
            ), HttpStatus.BAD_REQUEST
        )
    }
}