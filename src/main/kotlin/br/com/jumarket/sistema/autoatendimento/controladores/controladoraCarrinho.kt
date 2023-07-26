package br.com.jumarket.sistema.autoatendimento.controladores

import br.com.jumarket.sistema.autoatendimento.dto.*
import br.com.jumarket.sistema.autoatendimento.entidade.Carrinho
import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import br.com.jumarket.sistema.autoatendimento.servicos.implementos.ServicosCarrinho
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/carrinho")
@Tag(name ="Carrinho", description = "Campo destinado a ações relacionadas aos carrinhos de compras")
class controladoraCarrinho(
    private val servicosCarrinho: ServicosCarrinho
) {

    @Operation(summary = "Salvar novo carrinho", description = "Utilize para criar um novo carrinho")
    @PostMapping
    fun salvarCarrinho(@RequestBody @Valid dtoSalvarCarrinho: DtoSalvarCarrinho): ResponseEntity<String> {
        val carrinhoSalvo: Carrinho = this.servicosCarrinho.salvarCarrinho(dtoSalvarCarrinho.paraEntidade())
        return ResponseEntity.status(HttpStatus.CREATED).body("Carrinho  salvo!")
    }

    @Operation(summary = "Consultar um carrinho", description = "Utilize para consultar um carrinho")
    @GetMapping("/{id}")
    fun consultarCarrinho(@PathVariable id: Long): ResponseEntity<DtoVerCarrinho> {
        val carrinho :Carrinho = this.servicosCarrinho.consultarCarrinho(id)
        return ResponseEntity.status(HttpStatus.OK).body(DtoVerCarrinho(carrinho))
    }

    @Operation(summary = "Apagar um carrinho", description = "Utilize para apagar um carrinho")
    @DeleteMapping("/{id}")
    fun apagarCarrinho(@PathVariable id: Long) = this.servicosCarrinho.apagarCarrinho(id)

    @Operation(summary = "Atualizar um carrinho", description = "Utilize para atualizar um carrinho")
    @PatchMapping
    fun atualizarCarrinho(
        @RequestParam(value = "carrinhoId") id: Long, @RequestBody @Valid dtoAtualizarCarrinho: DtoAtualizarCarrinho
    ): ResponseEntity<DtoVerCarrinho> {
        val carrinho: Carrinho = this.servicosCarrinho.consultarCarrinho(id)
        val carrinhoParaAtualizar: Carrinho = dtoAtualizarCarrinho.paraEntidade(carrinho)
        val carrinhoAtualizado: Carrinho = this.servicosCarrinho.salvarCarrinho(carrinhoParaAtualizar)
        return ResponseEntity.status(HttpStatus.OK).body(DtoVerCarrinho(carrinhoAtualizado))
    }
}