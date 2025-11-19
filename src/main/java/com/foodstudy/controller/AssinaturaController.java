package com.foodstudy.controller;

import br.com.foodstudy.domain.dto.AssinaturaRequest;
import br.com.foodstudy.domain.dto.AssinaturaResponse;
import br.com.foodstudy.domain.model.Assinatura;
import br.com.foodstudy.service.AssinaturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assinaturas")
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    public AssinaturaController(AssinaturaService assinaturaService) {
        this.assinaturaService = assinaturaService;
    }

    @PostMapping
    public ResponseEntity<AssinaturaResponse> contratar(@RequestBody AssinaturaRequest dto) {

        Assinatura a = assinaturaService.contratarPlano(dto.getUsuarioId(), dto.getTipo());

        AssinaturaResponse resp = new AssinaturaResponse();
        resp.setId(a.getId());
        resp.setTipo(a.getTipo());
        resp.setValor(a.getValor());
        resp.setBeneficios(a.getBeneficios());
        resp.setDataInicio(a.getDataInicio());
        resp.setDataFim(a.getDataFim());

        return ResponseEntity.ok(resp);
    }
}
