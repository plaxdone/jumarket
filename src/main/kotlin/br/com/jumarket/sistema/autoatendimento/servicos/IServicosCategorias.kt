package br.com.jumarket.sistema.autoatendimento.servicos

import br.com.jumarket.sistema.autoatendimento.entidade.Categorias

interface IServicosCategorias {
    fun salvarCategoria(categorias: Categorias): Categorias

    fun consultarCategoria(id: Long): Categorias

    fun apagarCategoria(id: Long)
}