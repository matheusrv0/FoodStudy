// src/main/java/com/foodstudy/model/FoodCash.java
package com.foodstudy.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "foodcash")
public class FoodCash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal saldo = BigDecimal.ZERO;

    @OneToOne
    @JoinColumn(name = "usuario_id")
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

    // getters e setters
}
