// src/main/java/com/foodstudy/repository/PedidoRepository.java
package com.foodstudy.repository;

import com.foodstudy.model.Pedido;
import com.foodstudy.model.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByUsuarioId(Long usuarioId);

    List<Pedido> findByStatusAndHorarioRetiradaBefore(StatusPedido status, LocalDateTime limite);
}
