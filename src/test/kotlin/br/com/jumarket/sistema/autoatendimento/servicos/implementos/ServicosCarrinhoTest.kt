package br.com.jumarket.sistema.autoatendimento.servicos.implementos

import br.com.jumarket.sistema.autoatendimento.entidade.Carrinho
import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import br.com.jumarket.sistema.autoatendimento.enumeracao.FormaPagamento
import br.com.jumarket.sistema.autoatendimento.repositorio.RepositorioCarrinho
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
class ServicosCarrinhoTest {
    @MockK lateinit var repositorioCarrinho: RepositorioCarrinho
    @InjectMockKs lateinit var servicosCarrinho: ServicosCarrinho


    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun salvarCarrinho() {
        val testeCarrinho: Carrinho = criarCarrinho()
        every { repositorioCarrinho.save(any()) } returns testeCarrinho

        val atual: Carrinho = servicosCarrinho.salvarCarrinho(testeCarrinho)
        Assertions.assertThat(atual).isNotNull
        Assertions.assertThat(atual).isSameAs(testeCarrinho)
        verify(exactly = 1) { repositorioCarrinho.save(testeCarrinho) }
    }

    @Test
    fun consultarCarrinho() {
        val idTeste: Long = Random().nextLong()
        val testeCarrinho: Carrinho = criarCarrinho(id = idTeste)
        every { repositorioCarrinho.findById(idTeste) } returns Optional.of(testeCarrinho)
        val atual: Carrinho = servicosCarrinho.consultarCarrinho(idTeste)

        Assertions.assertThat(atual).isNotNull
        Assertions.assertThat(atual).isExactlyInstanceOf(Carrinho::class.java)
        verify(exactly = 1) { repositorioCarrinho.findById(idTeste) }
    }

    @Test
    fun apagarCarrinho() {
        val idTeste: Long = Random().nextLong()
        val testeCarrinho: Carrinho = criarCarrinho(id = idTeste)
        every { repositorioCarrinho.findById(idTeste) } returns Optional.of(testeCarrinho)
        every { repositorioCarrinho.delete(testeCarrinho) } just runs

        servicosCarrinho.apagarCarrinho(idTeste)

        verify(exactly = 1) { repositorioCarrinho.findById(idTeste) }
        verify(exactly = 1) { repositorioCarrinho.delete(testeCarrinho) }
    }

    companion object {
        fun criarCarrinho(
            id: Long = 1L
        ) = Carrinho(
            id = id
        )
    }
}