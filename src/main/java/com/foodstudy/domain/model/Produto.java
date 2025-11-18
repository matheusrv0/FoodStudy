// src/main/java/com/foodstudy/model/Produto.java
package com.foodstudy.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    // tipo pode ser "LANCHE", "BEBIDA", etc
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;

    @OneToMany(mappedBy = "produto")
    private List<Pedido> pedidos;

    // getters e setters
}
