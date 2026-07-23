package cotiinformatica.api_produtos.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProdutoPutResponse(
        Integer status,
        String mensagem,
        LocalDateTime dataHoraAtualizacao,
        UUID produtoId
) {
}
