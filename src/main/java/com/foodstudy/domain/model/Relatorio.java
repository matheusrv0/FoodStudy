// src/main/java/com/foodstudy/model/Relatorio.java
package com.foodstudy.domain.model;

import com.foodstudy.model.enums.TipoRelatorio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "relatorios")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoRelatorio tipo;

    private LocalDateTime data = LocalDateTime.now();

    @Lob
    private String conteudo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estabelecimento_id",nullable = false)
    private Estabelecimento estabelecimento;

}
