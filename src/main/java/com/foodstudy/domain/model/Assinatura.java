// src/main/java/com/foodstudy/model/Assinatura.java
package com.foodstudy.model;

import com.foodstudy.model.enums.TipoAssinatura;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "assinaturas")
public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoAssinatura tipo;

    private BigDecimal valor;

    private String beneficios; // texto descritivo

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    // getters e setters
}
