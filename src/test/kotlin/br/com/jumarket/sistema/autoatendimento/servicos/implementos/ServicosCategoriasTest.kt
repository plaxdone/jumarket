package br.com.jumarket.sistema.autoatendimento.servicos.implementos

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import br.com.jumarket.sistema.autoatendimento.repositorio.RepositorioCategoria
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class ServicosCategoriasTest {
    @MockK
    lateinit var repositorioCategoria: RepositorioCategoria

    @InjectMockKs
    lateinit var servicosCategorias: ServicosCategorias

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun salvarCategoria() {
        val testeCategoria: Categorias = criarCategoria()
        every { repositorioCategoria.save(any()) } returns testeCategoria

        val atual: Categorias = servicosCategorias.salvarCategoria(testeCategoria)
        Assertions.assertThat(atual).isNotNull
        Assertions.assertThat(atual).isSameAs(testeCategoria)
        verify(exactly = 1) { repositorioCategoria.save(testeCategoria) }
    }

    @Test
    fun consultarCategoria() {
        val idTeste: Long = Random().nextLong()
        val testeCategoria: Categorias = criarCategoria(id = idTeste)
        every { repositorioCategoria.findById(idTeste) } returns Optional.of(testeCategoria)
        val atual: Categorias = servicosCategorias.consultarCategoria(idTeste)

        Assertions.assertThat(atual).isNotNull
        Assertions.assertThat(atual).isExactlyInstanceOf(Categorias::class.java)
        verify(exactly = 1) { repositorioCategoria.findById(idTeste) }
    }

    @Test
    fun consultarTodasCategorias() {
        val listaCategorias: List<Categorias> =
            listOf(criarCategoria(), criarCategoria(), criarCategoria(), criarCategoria())

        every { repositorioCategoria.consultarTodasCategorias() } returns listaCategorias
        val atual: List<Categorias> = servicosCategorias.consultarTodasCategorias()

        Assertions.assertThat(atual).isNotNull
        Assertions.assertThat(atual).isNotEmpty
        Assertions.assertThat(atual).isSameAs(listaCategorias)
    }

    @Test
    fun apagarCategoria() {
        val idTeste: Long = Random().nextLong()
        val testeCategoria: Categorias = criarCategoria(id = idTeste)
        every { repositorioCategoria.findById(idTeste) } returns Optional.of(testeCategoria)
        every { repositorioCategoria.delete(testeCategoria) } just runs

        servicosCategorias.apagarCategoria(idTeste)

        verify(exactly = 1) { repositorioCategoria.findById(idTeste) }
        verify(exactly = 1) { repositorioCategoria.delete(testeCategoria) }
    }

    companion object {
        fun criarCategoria(
            nomeCategoria: String = "Sorvete",
            descricaoCategoria: String = "Muito gelado",
            id: Long = 1L
        ) = Categorias(
            nomeCategoria = nomeCategoria,
            descricaoCategoria = descricaoCategoria,
            id = id
        )
    }
}