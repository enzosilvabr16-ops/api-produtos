package cotiinformatica.api_produtos.dtos;

/*
    DTO para representação dos dados que a API
    irá receber (REQUEST) no endpoint de
    cadastro de produto (POST)
 */
public record ProdutoPostRequest(
        String nome,
        Double preco,
        Integer quantidade
) {
}
