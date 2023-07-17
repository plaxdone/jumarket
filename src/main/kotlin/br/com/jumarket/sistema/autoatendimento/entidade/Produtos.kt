package br.com.jumarket.sistema.autoatendimento.entidade

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.math.BigDecimal

@Entity
data class Produtos(
    @Column(nullable = false, unique = true)var nomeProduto: String,
    var descricaoProduto: String,
    @Column(nullable = false) var unMedida: String,
    @Column(nullable = false) var estoque: BigDecimal,
    @Column(nullable = false) var preco: BigDecimal,
    @ManyToOne var categorias: Categorias? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
) {
}