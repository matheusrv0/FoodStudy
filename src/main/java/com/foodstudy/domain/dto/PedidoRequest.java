// domain/dto/PedidoRequest.java
package com.foodstudy.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoRequest {

    private Long usuarioId;
    private Long estabelecimentoId;
    private List<Long> produtosIds;
    private LocalDateTime horarioRetirada;

    // getters/setters
}
