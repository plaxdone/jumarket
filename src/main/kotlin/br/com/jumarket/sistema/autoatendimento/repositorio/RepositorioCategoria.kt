package br.com.jumarket.sistema.autoatendimento.repositorio

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias
import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RepositorioCategoria: JpaRepository<Categorias, Long> {

    @Query(value = "SELECT * FROM CATEGORIAS", nativeQuery = true)
    fun consultarTodasCategorias(): List<Categorias>
}