package br.com.foodstudy.controller;

import br.com.foodstudy.domain.dto.EstabelecimentoRequest;
import br.com.foodstudy.domain.dto.EstabelecimentoResponse;
import br.com.foodstudy.domain.dto.ProdutoRequest;
import br.com.foodstudy.domain.dto.ProdutoResponse;
import br.com.foodstudy.domain.model.Estabelecimento;
import br.com.foodstudy.domain.model.Produto;
import br.com.foodstudy.service.EstabelecimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estabelecimentos")
public class EstabelecimentoController {

    private final EstabelecimentoService estabelecimentoService;

    public EstabelecimentoController(EstabelecimentoService estabelecimentoService) {
        this.estabelecimentoService = estabelecimentoService;
    }

    @PostMapping
    public ResponseEntity<EstabelecimentoResponse> cadastrar(@RequestBody EstabelecimentoRequest dto) {
        Estabelecimento est = new Estabelecimento();
        est.setNome(dto.getNome());

        Estabelecimento salvo = estabelecimentoService.cadastrar(est);

        EstabelecimentoResponse resp = new EstabelecimentoResponse();
        resp.setId(salvo.getId());
        resp.setNome(salvo.getNome());

        return ResponseEntity.ok(resp);
    }

    @PostMapping("/{id}/produtos")
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@PathVariable Long id,
                                                            @RequestBody ProdutoRequest dto) {

        Produto p = new Produto();
        p.setNome(dto.getNome());
        p.setDescricao(dto.getDescricao());
        p.setPreco(dto.getPreco());
        p.setTipo(dto.getTipo());

        Produto salvo = estabelecimentoService.cadastrarProduto(id, p);

        ProdutoResponse resp = new ProdutoResponse();
        resp.setId(salvo.getId());
        resp.setNome(salvo.getNome());
        resp.setDescricao(salvo.getDescricao());
        resp.setPreco(salvo.getPreco());
        resp.setTipo(salvo.getTipo());
        resp.setEstabelecimentoId(salvo.getEstabelecimento().getId());

        return ResponseEntity.ok(resp);
    }
}
