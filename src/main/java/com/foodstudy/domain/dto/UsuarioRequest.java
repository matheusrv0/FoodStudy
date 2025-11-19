package com.foodstudy.domain.dto;

import br.com.foodstudy.domain.enums.TipoUsuario;

public class UsuarioRequest {

    private String nome;
    private String cpf;
    private TipoUsuario tipo;

    // getters / setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public TipoUsuario getTipo() { return tipo; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }
}
