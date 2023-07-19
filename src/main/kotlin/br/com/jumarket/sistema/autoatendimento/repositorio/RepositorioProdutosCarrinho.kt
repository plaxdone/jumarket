package br.com.jumarket.sistema.autoatendimento.repositorio

import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import br.com.jumarket.sistema.autoatendimento.entidade.ProdutosCarrinho
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RepositorioProdutosCarrinho: JpaRepository<ProdutosCarrinho, Long> {
    @Query(value = "SELECT * FROM PRODUTOS_CARRINHO WHERE CARRINHO_ID = ?1", nativeQuery = true)
    fun consultarPorCarrinho(carrinhoId: Long): List<ProdutosCarrinho>
}