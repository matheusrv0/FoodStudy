// src/main/java/com/foodstudy/model/Relatorio.java
package com.foodstudy.model;

import com.foodstudy.model.enums.TipoRelatorio;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "relatorios")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoRelatorio tipo;

    private LocalDateTime data;

    @Lob
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;

    // getters e setters
}
