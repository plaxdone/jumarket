# JuMarket 
### Sistema de autoatendimento
Sistema desenvolvido com as tecnologias:
- Springboot
- Kotlin
- MySql(via docker)
- Flyway
- JUnit(testes)
- OpenAPI Swagger
- Java 17

### Diagrama Banco de Dados

![](https://raw.githubusercontent.com/plaxdone/jumarket/main/DB.png)

### Usos

Ao iniciar o sistema deve se salvar um carrinho, que pode ser atualizado, apagado ou consultado via requisições POST, PATCH, DELETE e GET.

Com o sistema iniciado e um carrinho já disponivel deve se apresentar as categorias disponiveis para o cliente, após a seleção da categoria apresentar os produtos pertencentes a mesma.
Há tambem a possibilidade de criação, alteração ou mesmo a remoção de qualquer categoria ou produto.

Por fim, após a seleção do produto o sistema adiciona o produto, quantidade e valor ao carrinho possibilitando assim a finalização da compra.
