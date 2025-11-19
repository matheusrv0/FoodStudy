package com.foodstudy.domain.dto;

import br.com.foodstudy.domain.enums.TipoUsuario;

public class UsuarioResponse {

    private Long id;
    private String nome;
    private String cpf;
    private TipoUsuario tipo;
    private Double saldoFoodCash;

    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public TipoUsuario getTipo() { return tipo; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }

    public Double getSaldoFoodCash() { return saldoFoodCash; }
    public void setSaldoFoodCash(Double saldoFoodCash) { this.saldoFoodCash = saldoFoodCash; }
}
