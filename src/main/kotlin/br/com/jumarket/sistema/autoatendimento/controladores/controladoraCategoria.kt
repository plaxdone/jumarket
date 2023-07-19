package br.com.jumarket.sistema.autoatendimento.controladores

import br.com.jumarket.sistema.autoatendimento.dto.DtoAtualizarCategoria
import br.com.jumarket.sistema.autoatendimento.dto.DtoSalvarCategoria
import br.com.jumarket.sistema.autoatendimento.dto.DtoVerCategoria
import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import br.com.jumarket.sistema.autoatendimento.servicos.implementos.ServicosCategorias
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
class controladoraCategoria(
    private val servicosCategorias: ServicosCategorias
) {

    @PostMapping
    fun salvarCategoria(@RequestBody dtoSalvarCategoria: DtoSalvarCategoria): ResponseEntity<String> {
        val categoriaSalva = this.servicosCategorias.salvarCategoria(dtoSalvarCategoria.paraEntidade())
        return ResponseEntity.status(HttpStatus.CREATED).body("Categoria '${categoriaSalva.nomeCategoria}' salva!")
    }

    @GetMapping("/{id}")
    fun consultarCategoria(@PathVariable id: Long): ResponseEntity<DtoVerCategoria> {
        val categorias: Categorias = this.servicosCategorias.consultarCategoria(id)
        return ResponseEntity.status(HttpStatus.OK).body(DtoVerCategoria(categorias))
    }

    @GetMapping("/todas")
    fun consultarTodasCategorias(): ResponseEntity<List<DtoVerCategoria>> {
        val dtoVerCategoria: List<DtoVerCategoria> = this.servicosCategorias.consultarTodasCategorias().stream()
            .map { categorias: Categorias -> DtoVerCategoria(categorias) }.collect(
                Collectors.toList()
            )
        return ResponseEntity.status(HttpStatus.OK).body(dtoVerCategoria)
    }

    @DeleteMapping("/{id}")
    fun apagarCategoria(@PathVariable id: Long) = this.servicosCategorias.apagarCategoria(id)

    @PatchMapping
    fun atualizarCategoria(
        @RequestParam(value = "categoriasId") id: Long, @RequestBody dtoAtualizarCategoria: DtoAtualizarCategoria
    ): ResponseEntity<DtoVerCategoria> {
        val categorias: Categorias = this.servicosCategorias.consultarCategoria(id)
        val categoriaParaAtualizar: Categorias = dtoAtualizarCategoria.paraEntidade(categorias)
        val categoriaAtualizada: Categorias = this.servicosCategorias.salvarCategoria(categoriaParaAtualizar)
        return ResponseEntity.status(HttpStatus.OK).body(DtoVerCategoria(categoriaAtualizada))
    }
}