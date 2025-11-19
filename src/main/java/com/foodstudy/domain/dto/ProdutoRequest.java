package com.foodstudy.domain.dto;

import java.math.BigDecimal;

public class ProdutoRequest {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String tipo;

    // getters / setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
