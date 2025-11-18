// controller/ProdutoController.java
package br.com.foodstudy.controller;

import br.com.foodstudy.domain.model.Produto;
import br.com.foodstudy.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto p) {
        return ResponseEntity.ok(repository.save(p));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/estabelecimento/{id}")
    public ResponseEntity<List<Produto>> porEstabelecimento(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findByEstabelecimentoId(id));
    }
}
