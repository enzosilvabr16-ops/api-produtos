package cotiinformatica.api_produtos.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProdutoDeleteResponse(
        Integer status,
        String mensagem,
        LocalDateTime dataHoraExclusao,
        UUID produtoId
) {
}
