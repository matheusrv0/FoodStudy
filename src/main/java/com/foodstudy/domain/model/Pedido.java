// src/main/java/com/foodstudy/model/Pedido.java
package com.foodstudy.model;

import com.foodstudy.model.enums.StatusPedido;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;

    private LocalDateTime horarioRetirada;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    // campo para saber se era perecível quando não retirado
    private Boolean perecivel;

    private LocalDateTime criadoEm = LocalDateTime.now();

    // getters e setters
}
