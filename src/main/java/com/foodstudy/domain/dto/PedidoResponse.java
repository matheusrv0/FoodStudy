// domain/dto/PedidoResponse.java
package com.foodstudy.domain.dto;

import br.com.foodstudy.domain.enums.StatusPedido;
import java.time.LocalDateTime;
import java.util.List;


public class PedidoResponse {

    private Long id;
    private Long usuarioId;
    private Long estabelecimentoId;
    private List<Long> produtosIds;
    private LocalDateTime horarioRetirada;
    private StatusPedido status;

}
