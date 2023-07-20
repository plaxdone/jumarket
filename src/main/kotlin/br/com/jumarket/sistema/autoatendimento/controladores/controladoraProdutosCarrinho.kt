package br.com.jumarket.sistema.autoatendimento.controladores

import br.com.jumarket.sistema.autoatendimento.dto.DtoSalvarProdutos
import br.com.jumarket.sistema.autoatendimento.dto.DtoSalvarProdutosCarrinho
import br.com.jumarket.sistema.autoatendimento.dto.DtoVerListaProdutos
import br.com.jumarket.sistema.autoatendimento.dto.DtoVerListaProdutosCarrinho
import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import br.com.jumarket.sistema.autoatendimento.entidade.ProdutosCarrinho
import br.com.jumarket.sistema.autoatendimento.servicos.implementos.ServicosProdutosCarrinho
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/produtoscarrinho")
class controladoraProdutosCarrinho(
    private val servicosProdutosCarrinho: ServicosProdutosCarrinho
) {
    @PostMapping
    fun salvarProdutoCarrinho(@RequestBody @Valid dtoSalvarProdutosCarrinho: DtoSalvarProdutosCarrinho): ResponseEntity<String> {
        val produtoCarrinhoSalvo = this.servicosProdutosCarrinho.salvarProdutosCarrinho(dtoSalvarProdutosCarrinho.paraEntidade())
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto ${produtoCarrinhoSalvo.produtoNome} inserido no carrinho!")
    }

    @GetMapping
    fun consultarPorCarrinho(@RequestParam(value = "carrinhoId") id: Long): ResponseEntity<List<DtoVerListaProdutosCarrinho>> {
        val dtoVerListaProdutosCarrinho: List<DtoVerListaProdutosCarrinho> = this.servicosProdutosCarrinho.consultarPorCarrinho(id).stream()
            .map { produtosCarrinho: ProdutosCarrinho -> DtoVerListaProdutosCarrinho(produtosCarrinho) }.collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(dtoVerListaProdutosCarrinho)
    }

    @DeleteMapping("/{id}")
    fun apagarProdutoCarrinho(@PathVariable id: Long) = this.servicosProdutosCarrinho.apagarProdutoCarrinho(id)
}