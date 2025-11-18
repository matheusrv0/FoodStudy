package br.com.foodstudy.controller;

import br.com.foodstudy.domain.dto.PedidoRequest;
import br.com.foodstudy.domain.dto.PedidoResponse;
import br.com.foodstudy.domain.model.Pedido;
import br.com.foodstudy.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> criar(@RequestBody PedidoRequest dto) {

        Pedido pedido = pedidoService.agendarPedido(
                dto.getUsuarioId(),
                dto.getEstabelecimentoId(),
                dto.getProdutosIds(),
                dto.getHorarioRetirada()
        );

        PedidoResponse resp = new PedidoResponse();
        resp.setId(pedido.getId());
        resp.setUsuarioId(pedido.getUsuario().getId());
        resp.setEstabelecimentoId(pedido.getEstabelecimento().getId());

        resp.setProdutosIds(
                pedido.getProdutos().stream().map(p -> p.getId()).collect(Collectors.toList())
        );

        resp.setStatus(pedido.getStatus());
        resp.setHorarioRetirada(pedido.getHorarioRetirada());

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscar(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPorId(id);

        PedidoResponse resp = new PedidoResponse();
        resp.setId(pedido.getId());
        resp.setUsuarioId(pedido.getUsuario().getId());
        resp.setEstabelecimentoId(pedido.getEstabelecimento().getId());
        resp.setProdutosIds(
                pedido.getProdutos().stream().map(p -> p.getId()).collect(Collectors.toList())
        );
        resp.setStatus(pedido.getStatus());
        resp.setHorarioRetirada(pedido.getHorarioRetirada());

        return ResponseEntity.ok(resp);
    }
}
