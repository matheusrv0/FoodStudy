// src/main/java/com/foodstudy/model/Estabelecimento.java
package com.foodstudy.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "estabelecimentos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "estabelecimento",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Produto> produtos;

    @OneToMany(mappedBy = "estabelecimento",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "estabelecimento",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Relatorio> relatorios;

}
