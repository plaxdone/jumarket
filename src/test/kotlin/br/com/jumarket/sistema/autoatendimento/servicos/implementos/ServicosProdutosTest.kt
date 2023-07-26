package br.com.jumarket.sistema.autoatendimento.servicos.implementos

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import br.com.jumarket.sistema.autoatendimento.repositorio.RepositorioProdutos
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
import java.math.BigDecimal
import java.util.*

@ExtendWith(MockKExtension::class)
class ServicosProdutosTest {
    @MockK lateinit var repositorioProdutos: RepositorioProdutos
    @MockK lateinit var servicosCategorias: ServicosCategorias
    @InjectMockKs lateinit var servicosProdutos: ServicosProdutos

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun salvarProduto() {
        val testeProdutos: Produtos = criarProdutosAbacate()
        val idCategorias: Long = 1L

        every { servicosCategorias.consultarCategoria(idCategorias) } returns testeProdutos.categorias!!
        every { repositorioProdutos.save(testeProdutos) } returns testeProdutos

        val atual: Produtos = this.servicosProdutos.salvarProduto(testeProdutos)
        verify(exactly = 1) { servicosCategorias.consultarCategoria(idCategorias) }
        verify(exactly = 1) { repositorioProdutos.save(testeProdutos) }

        Assertions.assertThat(atual).isNotNull
        Assertions.assertThat(atual).isSameAs(testeProdutos)

    }

    @Test
    fun consultarProduto() {
        val idTeste: Long = Random().nextLong()
        val testeProduto: Produtos = criarProdutosAbacate(id = idTeste)
        every { repositorioProdutos.findById(idTeste) } returns Optional.of(testeProduto)
        val atual: Produtos = servicosProdutos.consultarProduto(idTeste)

        Assertions.assertThat(atual).isNotNull
        Assertions.assertThat(atual).isExactlyInstanceOf(Produtos::class.java)
        Assertions.assertThat(atual).isSameAs(testeProduto)
        verify(exactly = 1) { repositorioProdutos.findById(idTeste) }
    }

    @Test
    fun consultarPorCategoria() {
        val idTeste: Long = Random().nextLong()
        val listaProdutos: List<Produtos> = listOf(criarProdutosAbacate(), criarProdutosMelancia())

        every { repositorioProdutos.consultarPorCategoria(idTeste) } returns listaProdutos

        val atual: List<Produtos> = servicosProdutos.consultarPorCategoria(idTeste)

        Assertions.assertThat(atual).isNotNull
        Assertions.assertThat(atual).isNotEmpty
        Assertions.assertThat(atual).isSameAs(listaProdutos)
    }

    @Test
    fun consultarTodosProdutos() {
        val listaProdutos: List<Produtos> = listOf(criarProdutosAbacate(), criarProdutosMelancia())

        every { repositorioProdutos.consultarTodosProdutos() } returns listaProdutos

        val atual: List<Produtos> = servicosProdutos.consultarTodosProdutos()

        Assertions.assertThat(atual).isNotNull
        Assertions.assertThat(atual).isNotEmpty
        Assertions.assertThat(atual).isSameAs(listaProdutos)
    }

    @Test
    fun apagarProduto() {
        val idTeste: Long = Random().nextLong()
        val testeProduto: Produtos = criarProdutosAbacate(id = idTeste)
        every { repositorioProdutos.findById(idTeste) } returns Optional.of(testeProduto)
        every { repositorioProdutos.delete(testeProduto) } just runs

        servicosProdutos.apagarProduto(idTeste)

        verify(exactly = 1) { repositorioProdutos.findById(idTeste) }
        verify(exactly = 1) { repositorioProdutos.delete(testeProduto) }
    }

    companion object{
        private fun criarProdutosAbacate(
            nomeProduto: String = "Abacate",
            descricaoProduto: String = "Verde",
            unMedida: String = "unidade",
            estoque: BigDecimal = BigDecimal.valueOf(100.0),
            preco: BigDecimal = BigDecimal.valueOf(10.90),
            categorias: Categorias = ServicosCategoriasTest.criarCategoria(),
            id: Long = 1L
        ): Produtos = Produtos(
            nomeProduto = nomeProduto,
            descricaoProduto = descricaoProduto,
            unMedida = unMedida,
            estoque = estoque,
            preco = preco,
            categorias = categorias,id = id
        )

        private fun criarProdutosMelancia(
            nomeProduto: String = "Melancia",
            descricaoProduto: String = "Vermelha",
            unMedida: String = "kilo",
            estoque: BigDecimal = BigDecimal.valueOf(97.0),
            preco: BigDecimal = BigDecimal.valueOf(1.90),
            categorias: Categorias = ServicosCategoriasTest.criarCategoria(),
            id: Long = 1L
        ): Produtos = Produtos(
            nomeProduto = nomeProduto,
            descricaoProduto = descricaoProduto,
            unMedida = unMedida,
            estoque = estoque,
            preco = preco,
            categorias = categorias,
            id = id
        )
    }
}