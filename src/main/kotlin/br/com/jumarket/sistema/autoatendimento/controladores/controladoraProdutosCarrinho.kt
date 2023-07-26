package br.com.jumarket.sistema.autoatendimento.controladores

import br.com.jumarket.sistema.autoatendimento.dto.DtoSalvarProdutosCarrinho
import br.com.jumarket.sistema.autoatendimento.dto.DtoVerListaProdutosCarrinho
import br.com.jumarket.sistema.autoatendimento.entidade.ProdutosCarrinho
import br.com.jumarket.sistema.autoatendimento.servicos.implementos.ServicosProdutosCarrinho
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/produtoscarrinho")
@Tag(name ="Produtos do carrinho", description = "Campo destinado a ações relacionadas aos produtos do carrinho de compras")
class controladoraProdutosCarrinho(
    private val servicosProdutosCarrinho: ServicosProdutosCarrinho
) {
    @Operation(summary = "Salvar um produto no carrinho", description = "Utilize para salvar um produto no carrinho")
    @PostMapping
    fun salvarProdutoCarrinho(@RequestBody @Valid dtoSalvarProdutosCarrinho: DtoSalvarProdutosCarrinho): ResponseEntity<String> {
        val produtoCarrinhoSalvo =
            this.servicosProdutosCarrinho.salvarProdutosCarrinho(dtoSalvarProdutosCarrinho.paraEntidade())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Produto ${produtoCarrinhoSalvo.produtoNome} inserido no carrinho!")
    }

    @Operation(summary = "Consultar produtos no carrinho", description = "Utilize para consultar os produtos no carrinho")
    @GetMapping
    fun consultarPorCarrinho(@RequestParam(value = "carrinhoId") id: Long): ResponseEntity<List<DtoVerListaProdutosCarrinho>> {
        val dtoVerListaProdutosCarrinho: List<DtoVerListaProdutosCarrinho> =
            this.servicosProdutosCarrinho.consultarPorCarrinho(id).stream()
                .map { produtosCarrinho: ProdutosCarrinho -> DtoVerListaProdutosCarrinho(produtosCarrinho) }
                .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(dtoVerListaProdutosCarrinho)
    }

    @Operation(summary = "apagar um produto no carrinho", description = "Utilize para apagar um produto no carrinho")
    @DeleteMapping("/{id}")
    fun apagarProdutoCarrinho(@PathVariable id: Long) = this.servicosProdutosCarrinho.apagarProdutoCarrinho(id)
}