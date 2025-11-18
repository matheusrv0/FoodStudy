// src/main/java/com/foodstudy/service/AssinaturaService.java
package com.foodstudy.service;

import com.foodstudy.model.Assinatura;
import com.foodstudy.model.Usuario;
import com.foodstudy.model.enums.TipoAssinatura;
import com.foodstudy.repository.AssinaturaRepository;
import com.foodstudy.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final UsuarioRepository usuarioRepository;

    public AssinaturaService(AssinaturaRepository assinaturaRepository,
                             UsuarioRepository usuarioRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Assinatura contratarPlano(Long usuarioId, TipoAssinatura tipo) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Assinatura a = new Assinatura();
        a.setUsuario(usuario);
        a.setTipo(tipo);
        a.setDataInicio(LocalDate.now());
        a.setDataFim(LocalDate.now().plusMonths(1));

        switch (tipo) {
            case BASICO -> {
                a.setValor(BigDecimal.ZERO);
                a.setBeneficios("Agendamento simples.");
            }
            case PREMIUM -> {
                a.setValor(new BigDecimal("9.90"));
                a.setBeneficios("5% de cashback e cupons mensais.");
            }
            case GOLD -> {
                a.setValor(new BigDecimal("19.90"));
                a.setBeneficios("10% de cashback, prioridade em horários e cupons extras.");
            }
        }

        return assinaturaRepository.save(a);
    }
}
