package br.com.jumarket.sistema.autoatendimento.controladores

import br.com.jumarket.sistema.autoatendimento.dto.*
import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import br.com.jumarket.sistema.autoatendimento.servicos.implementos.ServicosProdutos
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/produtos")
@Tag(name ="Produtos", description = "Campo destinado a ações relacionadas aos produtos")
class controladoraProdutos(
    private val servicosProdutos: ServicosProdutos
) {

    @Operation(summary = "Salvar novo produto", description = "Utilize para criar um novo produto")
    @PostMapping
    fun salvarProduto(@RequestBody @Valid dtoSalvarProdutos: DtoSalvarProdutos): ResponseEntity<String> {
        val produtoSalvo = this.servicosProdutos.salvarProduto(dtoSalvarProdutos.paraEntidade())
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto '${produtoSalvo.nomeProduto}' salvo!")
    }

    @Operation(summary = "Consultar um produto", description = "Utilize para consultar um produto")
    @GetMapping("/{id}")
    fun consultarProduto(@PathVariable id: Long): ResponseEntity<DtoVerProduto> {
        val produtos = this.servicosProdutos.consultarProduto(id)
        return ResponseEntity.status(HttpStatus.OK).body(DtoVerProduto(produtos))
    }

    @Operation(summary = "Consultar um produto por categoria", description = "Utilize para consultar um produto por categoria")
    @GetMapping
    fun consultarPorCategoria(@RequestParam(value = "categoriasId") id: Long): ResponseEntity<List<DtoVerListaProdutos>> {
        val dtoVerListaProdutos: List<DtoVerListaProdutos> = this.servicosProdutos.consultarPorCategoria(id).stream()
            .map { produtos: Produtos -> DtoVerListaProdutos(produtos) }.collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(dtoVerListaProdutos)
    }

    @Operation(summary = "Consultar todos os produtos", description = "Utilize para consultar todos os produtos")
    @GetMapping("/todos")
    fun consultarTodosProdutos(): ResponseEntity<List<DtoVerListaProdutos>> {
        val dtoVerListaProdutos: List<DtoVerListaProdutos> = this.servicosProdutos.consultarTodosProdutos().stream()
            .map { produtos: Produtos -> DtoVerListaProdutos(produtos) }.collect(
                Collectors.toList()
            )
        return ResponseEntity.status(HttpStatus.OK).body(dtoVerListaProdutos)
    }

    @Operation(summary = "Apagar um produto", description = "Utilize para apagar um produto")
    @DeleteMapping("/{id}")
    fun apagarProduto(@PathVariable id: Long) = this.servicosProdutos.apagarProduto(id)

    @Operation(summary = "Atualizar um produto", description = "Utilize para atualizar um produto")
    @PatchMapping
    fun atualizarProduto(
        @RequestParam(value = "produtosId") id: Long, @RequestBody @Valid dtoAtualizarProduto: DtoAtualizarProduto
    ): ResponseEntity<DtoVerProduto> {
        val produtos: Produtos = this.servicosProdutos.consultarProduto(id)
        val produtoParaAtualizar: Produtos = dtoAtualizarProduto.paraEntidade(produtos)
        val produtoAtualizado: Produtos = this.servicosProdutos.salvarProduto(produtoParaAtualizar)
        return ResponseEntity.status(HttpStatus.OK).body(DtoVerProduto(produtoAtualizado))
    }
}