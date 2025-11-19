package com.foodstudy.domain.dto;

import br.com.foodstudy.domain.enums.TipoAssinatura;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AssinaturaResponse {

    private Long id;
    private TipoAssinatura tipo;
    private BigDecimal valor;
    private String beneficios;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TipoAssinatura getTipo() { return tipo; }
    public void setTipo(TipoAssinatura tipo) { this.tipo = tipo; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getBeneficios() { return beneficios; }
    public void setBeneficios(String beneficios) { this.beneficios = beneficios; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
}
