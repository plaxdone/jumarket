package br.com.jumarket.sistema.autoatendimento

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "JuMarket API REST",
        version = "0.1",
        description = "API para o sistema de auto-atendimento",
        contact = Contact(name = "Jorge Luciano", url = "https://github.com/plaxdone", email = "jrglucan@gmail.com"),
        license = License(name = "Apache License Version 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")

    )
)
class configuracaoSwagger3 {
    @Bean
    fun publicApi(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
            .group("springsistemaautoatendimento")
            .pathsToMatch("/api/carrinho/**", "/api/categorias/**", "/api/produtos/**", "/api/produtoscarrinho/**")
            .build()
    }
}