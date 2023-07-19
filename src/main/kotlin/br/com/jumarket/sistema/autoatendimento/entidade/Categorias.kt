package br.com.jumarket.sistema.autoatendimento.entidade

import jakarta.persistence.*

@Entity
data class Categorias(
    @Column(nullable = false, unique = true) var nomeCategoria: String = "",
    var descricaoCategoria: String = "",
    @Column(nullable = false) @OneToMany(
        fetch = FetchType.LAZY, mappedBy = "categorias"
    ) var produtos: List<Produtos> = mutableListOf(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) {}