// src/main/java/com/foodstudy/model/Assinatura.java
package com.foodstudy.domain.model;

import com.foodstudy.model.enums.TipoAssinatura;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "assinaturas")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
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

}
