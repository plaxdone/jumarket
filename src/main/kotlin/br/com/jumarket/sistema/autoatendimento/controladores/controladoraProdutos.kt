package br.com.jumarket.sistema.autoatendimento.controladores

import br.com.jumarket.sistema.autoatendimento.dto.DtoSalvarProdutos
import br.com.jumarket.sistema.autoatendimento.dto.DtoVerListaProdutos
import br.com.jumarket.sistema.autoatendimento.dto.DtoVerProduto
import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import br.com.jumarket.sistema.autoatendimento.servicos.implementos.ServicosProdutos
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/produtos")
class controladoraProdutos(
    private val servicosProdutos: ServicosProdutos
) {

    @PostMapping
    fun salvarProduto(@RequestBody dtoSalvarProdutos: DtoSalvarProdutos): ResponseEntity<String> {
        val produtoSalvo = this.servicosProdutos.salvarProduto(dtoSalvarProdutos.paraEntidade())
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto ${produtoSalvo.nomeProduto} salvo!")
    }

    @GetMapping("/{id}")
    fun consultarProduto(@PathVariable id: Long): ResponseEntity<DtoVerProduto> {
        val produtos = this.servicosProdutos.consultarProduto(id)
        return ResponseEntity.status(HttpStatus.OK).body(DtoVerProduto(produtos))
    }

    @GetMapping
    fun consultarPorCategoria(@RequestParam(value = "categoriasId") id: Long): ResponseEntity<List<DtoVerListaProdutos>> {
        val dtoVerListaProdutos: List<DtoVerListaProdutos> = this.servicosProdutos.consultarPorCategoria(id).stream()
            .map { produtos: Produtos -> DtoVerListaProdutos(produtos) }.collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(dtoVerListaProdutos)
    }
}