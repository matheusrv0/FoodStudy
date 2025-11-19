// src/main/java/com/foodstudy/model/Pedido.java
package com.foodstudy.domain.model;

import com.foodstudy.model.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"), // Join representa a tabela de Pedido
            inverseJoinColumns = @JoinColumn(name = "produto_id") // Inverse representa a tabela de Produto
    )
    private List<Produto> produtos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estabelecimento_id", nullable = false)
    private Estabelecimento estabelecimento;

    private LocalDateTime horarioRetirada;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private Boolean perecivel;

    private LocalDateTime criadoEm = LocalDateTime.now();

}
