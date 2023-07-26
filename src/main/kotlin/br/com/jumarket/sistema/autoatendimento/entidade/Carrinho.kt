package br.com.jumarket.sistema.autoatendimento.entidade

import br.com.jumarket.sistema.autoatendimento.enumeracao.FormaPagamento
import io.swagger.v3.oas.annotations.Hidden
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
data class Carrinho(
    @Column(nullable = false) @OneToMany(
        fetch = FetchType.LAZY, mappedBy = "carrinho"
    ) val itens: List<ProdutosCarrinho> = mutableListOf(),
    @Enumerated var formaPagamento: FormaPagamento = FormaPagamento.Dinhero,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) {}