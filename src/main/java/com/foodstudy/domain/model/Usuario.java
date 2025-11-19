// src/main/java/com/foodstudy/model/Usuario.java
package com.foodstudy.domain.model;

import com.foodstudy.model.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"foodCash", "pedidos", "assinaturas", "notificacoes"})
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
    @OneToOne(mappedBy = "usuario",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private FoodCash foodCash;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "usuario")
    private List<Assinatura> assinaturas;

    @OneToMany(mappedBy = "usuario")
    private List<Notificacao> notificacoes;


}
