package br.com.jumarket.sistema.autoatendimento.controladores

import br.com.jumarket.sistema.autoatendimento.dto.DtoAtualizarCategoria
import br.com.jumarket.sistema.autoatendimento.dto.DtoSalvarCategoria
import br.com.jumarket.sistema.autoatendimento.dto.DtoVerCategoria
import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import br.com.jumarket.sistema.autoatendimento.servicos.implementos.ServicosCategorias
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
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
@RequestMapping("/api/categorias")
@Tag(name ="Categorias", description = "Campo destinado a ações relacionadas as categorias")
class controladoraCategoria(
    private val servicosCategorias: ServicosCategorias
) {

    @Operation(summary = "Salvar nova categoria", description = "Utilize para criar uma nova categoria")
    @PostMapping
    fun salvarCategoria(@RequestBody @Valid dtoSalvarCategoria: DtoSalvarCategoria): ResponseEntity<String> {
        val categoriaSalva = this.servicosCategorias.salvarCategoria(dtoSalvarCategoria.paraEntidade())
        return ResponseEntity.status(HttpStatus.CREATED).body("Categoria '${categoriaSalva.nomeCategoria}' salva!")
    }

    @Operation(summary = "Consultar uma categoria", description = "Utilize para consultar uma categoria")
    @GetMapping("/{id}")
    fun consultarCategoria(@PathVariable id: Long): ResponseEntity<DtoVerCategoria> {
        val categorias: Categorias = this.servicosCategorias.consultarCategoria(id)
        return ResponseEntity.status(HttpStatus.OK).body(DtoVerCategoria(categorias))
    }

    @Operation(summary = "Consultar todas as categorias", description = "Utilize para consultar todas as categoria")
    @GetMapping("/todas")
    fun consultarTodasCategorias(): ResponseEntity<List<DtoVerCategoria>> {
        val dtoVerCategoria: List<DtoVerCategoria> = this.servicosCategorias.consultarTodasCategorias().stream()
            .map { categorias: Categorias -> DtoVerCategoria(categorias) }.collect(
                Collectors.toList()
            )
        return ResponseEntity.status(HttpStatus.OK).body(dtoVerCategoria)
    }

    @Operation(summary = "Deletar uma categoria", description = "Utilize para apagar uma categoria")
    @DeleteMapping("/{id}")
    fun apagarCategoria(@PathVariable id: Long) = this.servicosCategorias.apagarCategoria(id)

    @Operation(summary = "Atualizar uma categoria", description = "Utilize para atualizar uma categoria")
    @PatchMapping
    fun atualizarCategoria(
        @RequestParam(value = "categoriasId") id: Long, @RequestBody @Valid dtoAtualizarCategoria: DtoAtualizarCategoria
    ): ResponseEntity<DtoVerCategoria> {
        val categorias: Categorias = this.servicosCategorias.consultarCategoria(id)
        val categoriaParaAtualizar: Categorias = dtoAtualizarCategoria.paraEntidade(categorias)
        val categoriaAtualizada: Categorias = this.servicosCategorias.salvarCategoria(categoriaParaAtualizar)
        return ResponseEntity.status(HttpStatus.OK).body(DtoVerCategoria(categoriaAtualizada))
    }
}