package com.foodstudy.domain.dto;

import br.com.foodstudy.domain.enums.TipoAssinatura;

public class AssinaturaRequest {

    private Long usuarioId;
    private TipoAssinatura tipo;

    // getters / setters
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public TipoAssinatura getTipo() { return tipo; }
    public void setTipo(TipoAssinatura tipo) { this.tipo = tipo; }
}
