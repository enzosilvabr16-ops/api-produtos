package cotiinformatica.api_produtos.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

/*
    DTO para representação dos dados que a API
    irá retornar (RESPONSE) no endpoint de
    cadastro de produto (POST)
 */
public record ProdutoPostResponse(
        Integer status,
        String mensagem,
        LocalDateTime dataHoraCadastro,
        UUID produtoId
) {
}
