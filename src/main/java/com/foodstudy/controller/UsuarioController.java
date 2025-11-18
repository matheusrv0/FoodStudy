package br.com.foodstudy.controller;

import br.com.foodstudy.domain.dto.UsuarioRequest;
import br.com.foodstudy.domain.dto.UsuarioResponse;
import br.com.foodstudy.domain.model.Usuario;
import br.com.foodstudy.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody UsuarioRequest dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setTipo(dto.getTipo());

        Usuario salvo = usuarioService.cadastrar(usuario);

        UsuarioResponse response = new UsuarioResponse();
        response.setId(salvo.getId());
        response.setNome(salvo.getNome());
        response.setCpf(salvo.getCpf());
        response.setTipo(salvo.getTipo());
        response.setSaldoFoodCash(salvo.getFoodCash().getSaldo().doubleValue());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscar(@PathVariable Long id) {
        Usuario u = usuarioService.buscarPorId(id);

        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setCpf(u.getCpf());
        dto.setTipo(u.getTipo());
        dto.setSaldoFoodCash(u.getFoodCash().getSaldo().doubleValue());

        return ResponseEntity.ok(dto);
    }
}
