package br.com.jumarket.sistema.autoatendimento.repositorio

import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RepositorioProdutos:JpaRepository<Produtos, Long> {
    @Query(value = "SELECT * FROM PRODUTOS WHERE CATEGORIAS_ID = ?1", nativeQuery = true)
    fun consultarPorCategoria(categoriasId: Long): List<Produtos>
}