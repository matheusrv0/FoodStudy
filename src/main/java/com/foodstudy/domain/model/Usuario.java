// src/main/java/com/foodstudy/model/Usuario.java
package com.foodstudy.model;

import com.foodstudy.model.enums.TipoUsuario;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    // 1-1 com FoodCash
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private FoodCash foodCash;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "usuario")
    private List<Assinatura> assinaturas;

    @OneToMany(mappedBy = "usuario")
    private List<Notificacao> notificacoes;

    // getters e setters
}
