// src/main/java/com/foodstudy/service/EstabelecimentoService.java
package com.foodstudy.service;

import com.foodstudy.model.Estabelecimento;
import com.foodstudy.model.Produto;
import com.foodstudy.repository.EstabelecimentoRepository;
import com.foodstudy.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstabelecimentoService {

    private final EstabelecimentoRepository estabelecimentoRepository;
    private final ProdutoRepository produtoRepository;

    public EstabelecimentoService(EstabelecimentoRepository estabelecimentoRepository,
                                  ProdutoRepository produtoRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.produtoRepository = produtoRepository;
    }

    public Estabelecimento cadastrar(Estabelecimento estabelecimento) {
        return estabelecimentoRepository.save(estabelecimento);
    }

    public List<Estabelecimento> listar() {
        return estabelecimentoRepository.findAll();
    }

    @Transactional
    public Produto cadastrarProduto(Long estabelecimentoId, Produto produto) {
        Estabelecimento est = estabelecimentoRepository.findById(estabelecimentoId)
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));
        produto.setEstabelecimento(est);
        return produtoRepository.save(produto);
    }
}
