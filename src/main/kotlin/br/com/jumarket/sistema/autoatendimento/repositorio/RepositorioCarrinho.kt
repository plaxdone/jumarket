package br.com.jumarket.sistema.autoatendimento.repositorio

import br.com.jumarket.sistema.autoatendimento.entidade.Carrinho
import br.com.jumarket.sistema.autoatendimento.entidade.Produtos
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface RepositorioCarrinho: JpaRepository<Carrinho, Long> {
}