// src/main/java/com/foodstudy/model/FoodCash.java
package com.foodstudy.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "foodcash")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class FoodCash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal saldo = BigDecimal.ZERO;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id",
                nullable = false,
                unique = true)
    private Usuario usuario;

    
    public void adicionar(BigDecimal valor) {
        if (valor != null && valor.compareTo(BigDecimal.ZERO) > 0) {
            this.saldo = this.saldo.add(valor);
        }
    }

    public void descontar(BigDecimal valor) {
        if (valor != null && valor.compareTo(BigDecimal.ZERO) > 0) {
            this.saldo = this.saldo.subtract(valor);
        }
    }

    public BigDecimal consultarSaldo() {
        return this.saldo;
    }

}
