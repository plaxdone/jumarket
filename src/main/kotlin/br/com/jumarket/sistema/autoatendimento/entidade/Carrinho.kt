package br.com.jumarket.sistema.autoatendimento.entidade

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
data class Carrinho(
    @Column(nullable = false) @OneToMany(
        fetch = FetchType.LAZY, mappedBy = "carrinho"
    ) val itens: List<ProdutosCarrinho> = mutableListOf(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) {}