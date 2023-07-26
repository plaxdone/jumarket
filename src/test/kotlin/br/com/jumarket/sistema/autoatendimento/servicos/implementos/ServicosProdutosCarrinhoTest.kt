package br.com.jumarket.sistema.autoatendimento.servicos.implementos

import br.com.jumarket.sistema.autoatendimento.entidade.Carrinho
import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import br.com.jumarket.sistema.autoatendimento.entidade.ProdutosCarrinho
import br.com.jumarket.sistema.autoatendimento.repositorio.RepositorioProdutosCarrinho
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
class ServicosProdutosCarrinhoTest {
    @MockK lateinit var repositorioProdutosCarrinho: RepositorioProdutosCarrinho
    @MockK lateinit var servicosCarrinho: ServicosCarrinho
    @InjectMockKs lateinit var servicosProdutosCarrinho: ServicosProdutosCarrinho

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun salvarProdutosCarrinho() {
        val testeProdutosCarrinho: ProdutosCarrinho = criarProdutosCarrinhoUm()
        val idCarrinho: Long = 1L

        every { servicosCarrinho.consultarCarrinho(idCarrinho) } returns testeProdutosCarrinho.carrinho!!
        every { repositorioProdutosCarrinho.save(testeProdutosCarrinho) } returns testeProdutosCarrinho

        val atual: ProdutosCarrinho = this.servicosProdutosCarrinho.salvarProdutosCarrinho(testeProdutosCarrinho)
        verify(exactly = 1) { servicosCarrinho.consultarCarrinho(idCarrinho) }
        verify(exactly = 1) { repositorioProdutosCarrinho.save(testeProdutosCarrinho) }

        Assertions.assertThat(atual).isNotNull
        Assertions.assertThat(atual).isSameAs(testeProdutosCarrinho)

    }

    @Test
    fun consultarPorCarrinho() {
        val idTeste: Long = Random().nextLong()
        val listaProdutosCarrinho: List<ProdutosCarrinho> = listOf(
            criarProdutosCarrinhoUm(),
            criarProdutosCarrinhoDois()
        )

        every { repositorioProdutosCarrinho.consultarPorCarrinho(idTeste) } returns listaProdutosCarrinho

        val atual: List<ProdutosCarrinho> = servicosProdutosCarrinho.consultarPorCarrinho(idTeste)

        Assertions.assertThat(atual).isNotNull
        Assertions.assertThat(atual).isNotEmpty
        Assertions.assertThat(atual).isSameAs(listaProdutosCarrinho)
    }

    @Test
    fun apagarProdutoCarrinho() {
        val idTeste: Long = Random().nextLong()
        val testeProdutoCarrinho: ProdutosCarrinho = criarProdutosCarrinhoDois(id = idTeste)
        every { repositorioProdutosCarrinho.findById(idTeste) } returns Optional.of(testeProdutoCarrinho)
        every { repositorioProdutosCarrinho.delete(testeProdutoCarrinho) } just runs

        servicosProdutosCarrinho.apagarProdutoCarrinho(idTeste)

        verify(exactly = 1) { repositorioProdutosCarrinho.findById(idTeste) }
        verify(exactly = 1) { repositorioProdutosCarrinho.delete(testeProdutoCarrinho) }
    }

    @Test
    fun finalizarCarrinho() {
    }

    companion object{
        private fun criarProdutosCarrinhoUm(
            produtoNome: String = "Sorvete",
            produtoPreco: BigDecimal = BigDecimal.valueOf(100.0),
            quantidade: BigDecimal = BigDecimal.valueOf(1.0),
            total: BigDecimal = BigDecimal.valueOf(100.0),
            carrinho: Carrinho = ServicosCarrinhoTest.criarCarrinho(),
            id: Long = 1L
        ): ProdutosCarrinho = ProdutosCarrinho(
            produtoNome = produtoNome,
            produtoPreco = produtoPreco,
            quantidade = quantidade,
            total = total,
            carrinho = carrinho,
            id = id
        )

        private fun criarProdutosCarrinhoDois(
            produtoNome: String = "Sorvete",
            produtoPreco: BigDecimal = BigDecimal.valueOf(8.59),
            quantidade: BigDecimal = BigDecimal.valueOf(3.0),
            total: BigDecimal = BigDecimal.valueOf(25.77),
            carrinho: Carrinho = ServicosCarrinhoTest.criarCarrinho(),
            id: Long = 1L
        ): ProdutosCarrinho = ProdutosCarrinho(
            produtoNome = produtoNome,
            produtoPreco = produtoPreco,
            quantidade = quantidade,
            total = total,
            carrinho = carrinho,
            id = id
        )
    }
}