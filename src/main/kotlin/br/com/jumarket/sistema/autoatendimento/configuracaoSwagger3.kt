package br.com.jumarket.sistema.autoatendimento

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class configuracaoSwagger3 {
    @Bean
    fun publicApi(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
            .group("springsistemaautoatendimento")
            .pathsToMatch("/api/carrinho/**", "/api/categorias/**", "/api/produtos/**", "/api/produtoscarrinho/**")
            .build()
    }
}