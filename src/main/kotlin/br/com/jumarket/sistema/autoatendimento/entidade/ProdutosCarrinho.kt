package br.com.jumarket.sistema.autoatendimento.entidade

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
data class ProdutosCarrinho(
    val produtoNome: String,
    val produtoPreco: BigDecimal,
    val quantidade: BigDecimal,
    val total: BigDecimal,
    @ManyToOne var carrinho: Carrinho? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null

) {

}
