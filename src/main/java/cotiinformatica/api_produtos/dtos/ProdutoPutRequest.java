package cotiinformatica.api_produtos.dtos;

import java.util.UUID;

public record ProdutoPutRequest(
        UUID id,
        String nome,
        Double preco,
        Integer quantidade
) {
}
