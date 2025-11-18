// src/main/java/com/foodstudy/model/Estabelecimento.java
package com.foodstudy.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "estabelecimentos")
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL)
    private List<Produto> produtos;

    @OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL)
    private List<Relatorio> relatorios;

    // getters e setters
}
