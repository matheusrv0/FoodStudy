// service/impl/PedidoServiceImpl.java
package br.com.foodstudy.service.impl;

import br.com.foodstudy.domain.dto.PedidoRequest;
import br.com.foodstudy.domain.dto.PedidoResponse;
import br.com.foodstudy.domain.enums.StatusPedido;
import br.com.foodstudy.domain.model.*;
import br.com.foodstudy.repository.*;
import br.com.foodstudy.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final ProdutoRepository produtoRepository;
    private final FoodCashRepository foodCashRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository,
                             UsuarioRepository usuarioRepository,
                             EstabelecimentoRepository estabelecimentoRepository,
                             ProdutoRepository produtoRepository,
                             FoodCashRepository foodCashRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.produtoRepository = produtoRepository;
        this.foodCashRepository = foodCashRepository;
    }

    @Override
    public PedidoResponse criarPedido(PedidoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Estabelecimento est = estabelecimentoRepository.findById(request.getEstabelecimentoId())
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));

        if (request.getHorarioRetirada().isBefore(LocalDateTime.now().plusMinutes(15))) {
            throw new RuntimeException("Horário de retirada deve ter no mínimo 15 minutos");
        }

        List<Produto> produtos = produtoRepository.findAllById(request.getProdutosIds());

        // calcula valor total
        var total = produtos.stream()
                .map(p -> p.getPreco())
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);

        // debita FoodCash (RF08)
        FoodCash carteira = foodCashRepository.findByUsuarioId(usuario.getId());
        if (carteira.getSaldo().compareTo(total) < 0) {
            throw new RuntimeException("Saldo FoodCash insuficiente");
        }
        carteira.setSaldo(carteira.getSaldo().subtract(total));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEstabelecimento(est);
        pedido.setProdutos(produtos);
        pedido.setHorarioRetirada(request.getHorarioRetirada());
        pedido.setStatus(StatusPedido.AGENDADO);

        Pedido salvo = pedidoRepository.save(pedido);
        foodCashRepository.save(carteira);

        return toResponse(salvo);
    }

    @Override
    public PedidoResponse cancelarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatus(StatusPedido.CANCELADO);
        return toResponse(pedidoRepository.save(pedido));
    }

    @Override
    public PedidoResponse marcarComoRetirado(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatus(StatusPedido.RETIRADO);
        return toResponse(pedidoRepository.save(pedido));
    }

    @Override
    public List<PedidoResponse> listarPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId)
                .stream().map(this::toResponse).toList();
    }

    @Override
    public List<PedidoResponse> listarPorEstabelecimento(Long estabelecimentoId) {
        return pedidoRepository.findByEstabelecimentoId(estabelecimentoId)
                .stream().map(this::toResponse).toList();
    }

    @Override
    public void processarPedidosNaoRetirados() {
        LocalDateTime limite = LocalDateTime.now().minusMinutes(15);
        List<Pedido> pedidos = pedidoRepository
                .findByStatusAndHorarioRetiradaBefore(StatusPedido.AGENDADO, limite);

        for (Pedido pedido : pedidos) {
            // aplica regra de penalidade RF05/RF06
            var total = pedido.getProdutos().stream()
                    .map(Produto::getPreco)
                    .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);

            var estabelecimento = pedido.getEstabelecimento();
            // aqui você definiria como o valor é creditado ao estabelecimento:
            // ex: cria registro de relatório/financeiro

            pedido.setStatus(StatusPedido.NAO_RETIRADO);
            pedidoRepository.save(pedido);
        }
    }

    private PedidoResponse toResponse(Pedido pedido) {
        PedidoResponse resp = new PedidoResponse();
        resp.setId(pedido.getId());
        resp.setUsuarioId(pedido.getUsuario().getId());
        resp.setEstabelecimentoId(pedido.getEstabelecimento().getId());
        resp.setProdutosIds(pedido.getProdutos().stream().map(Produto::getId).toList());
        resp.setHorarioRetirada(pedido.getHorarioRetirada());
        resp.setStatus(pedido.getStatus());
        return resp;
    }
}
