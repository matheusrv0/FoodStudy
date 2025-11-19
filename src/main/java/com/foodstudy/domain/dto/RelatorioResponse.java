package com.foodstudy.domain.dto;

import br.com.foodstudy.domain.enums.TipoRelatorio;
import java.time.LocalDateTime;

public class RelatorioResponse {

    private Long id;
    private TipoRelatorio tipo;
    private LocalDateTime data;
    private String conteudo;
    private Long estabelecimentoId;

    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TipoRelatorio getTipo() { return tipo; }
    public void setTipo(TipoRelatorio tipo) { this.tipo = tipo; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }

    public Long getEstabelecimentoId() { return estabelecimentoId; }
    public void setEstabelecimentoId(Long estabelecimentoId) { this.estabelecimentoId = estabelecimentoId; }
}
