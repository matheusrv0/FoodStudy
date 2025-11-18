// src/main/java/com/foodstudy/service/UsuarioService.java
package com.foodstudy.service;

import com.foodstudy.model.FoodCash;
import com.foodstudy.model.Usuario;
import com.foodstudy.repository.FoodCashRepository;
import com.foodstudy.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final FoodCashRepository foodCashRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          FoodCashRepository foodCashRepository) {
        this.usuarioRepository = usuarioRepository;
        this.foodCashRepository = foodCashRepository;
    }

    @Transactional
    public Usuario cadastrar(Usuario usuario) {
        // CPF único
        usuarioRepository.findByCpf(usuario.getCpf())
                .ifPresent(u -> { throw new RuntimeException("CPF já cadastrado"); });

        Usuario salvo = usuarioRepository.save(usuario);

        // cria FoodCash zerado
        FoodCash carteira = new FoodCash();
        carteira.setUsuario(salvo);
        foodCashRepository.save(carteira);

        salvo.setFoodCash(carteira);
        return salvo;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @Transactional
    public void adicionarFoodCash(Long usuarioId, BigDecimal valor) {
        Usuario usuario = buscarPorId(usuarioId);
        FoodCash fc = usuario.getFoodCash();
        fc.adicionar(valor);
        foodCashRepository.save(fc);
    }
}
