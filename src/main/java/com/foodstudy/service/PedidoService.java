// src/main/java/com/foodstudy/service/PedidoService.java
package com.foodstudy.service;

import com.foodstudy.model.*;
import com.foodstudy.model.enums.StatusPedido;
import com.foodstudy.repository.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final NotificacaoRepository notificacaoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         UsuarioRepository usuarioRepository,
                         ProdutoRepository produtoRepository,
                         EstabelecimentoRepository estabelecimentoRepository,
                         NotificacaoRepository notificacaoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.notificacaoRepository = notificacaoRepository;
    }

    /**
     * RF04 - agendar pedido (mínimo 15 minutos de antecedência)
     */
    @Transactional
    public Pedido agendarPedido(Long usuarioId,
                                Long produtoId,
                                Long estabelecimentoId,
                                LocalDateTime horarioRetirada,
                                boolean perecivel) {

        if (horarioRetirada.isBefore(LocalDateTime.now().plusMinutes(15))) {
            throw new RuntimeException("Horário de retirada deve ser pelo menos 15 minutos à frente");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Estabelecimento est = estabelecimentoRepository.findById(estabelecimentoId)
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));

        // debita FoodCash (RF08)
        BigDecimal preco = produto.getPreco();
        FoodCash carteira = usuario.getFoodCash();
        if (carteira.getSaldo().compareTo(preco) < 0) {
            throw new RuntimeException("Saldo FoodCash insuficiente");
        }
        carteira.descontar(preco);

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setProduto(produto);
        pedido.setEstabelecimento(est);
        pedido.setHorarioRetirada(horarioRetirada);
        pedido.setStatus(StatusPedido.AGENDADO);
        pedido.setPerecivel(perecivel);

        Pedido salvo = pedidoRepository.save(pedido);

        // notificação
        Notificacao n = new Notificacao();
        n.setUsuario(usuario);
        n.setMensagem("Pedido #" + salvo.getId() + " agendado com sucesso.");
        notificacaoRepository.save(n);

        return salvo;
    }

    @Transactional
    public Pedido marcarRetirado(Long pedidoId) {
        Pedido p = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        p.setStatus(StatusPedido.RETIRADO);
        return pedidoRepository.save(p);
    }

    @Transactional
    public Pedido cancelar(Long pedidoId) {
        Pedido p = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        p.setStatus(StatusPedido.CANCELADO);
        return pedidoRepository.save(p);
    }

    public List<Pedido> listarPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    /**
     * Tarefa agendada (Cron Job) para RF05/06:
     * roda a cada minuto e marca pedidos não retirados.
     */
    @Scheduled(fixedDelay = 60_000)
    @Transactional
    public void processarPedidosNaoRetirados() {
        LocalDateTime limite = LocalDateTime.now().minusMinutes(15);

        List<Pedido> atrasados = pedidoRepository
                .findByStatusAndHorarioRetiradaBefore(StatusPedido.AGENDADO, limite);

        for (Pedido pedido : atrasados) {
            pedido.setStatus(StatusPedido.NAO_RETIRADO);

            Usuario usuario = pedido.getUsuario();
            Estabelecimento est = pedido.getEstabelecimento();
            Produto produto = pedido.getProduto();

            BigDecimal valorPedido = produto.getPreco();

            // política de repasse (exemplo simples)
            BigDecimal percentual;
            if (Boolean.TRUE.equals(pedido.getPerecivel())) {
                percentual = new BigDecimal("0.80"); // 80% perecível
            } else {
                percentual = new BigDecimal("0.50"); // 50% não perecível
            }

            // aqui você poderia ter um "saldo" do estabelecimento;
            // por enquanto só gerar notificação.
            Notificacao n = new Notificacao();
            n.setUsuario(usuario);
            n.setMensagem("Pedido #" + pedido.getId()
                    + " não retirado. Valor perdido. "
                    + "Comércio recebeu " + valorPedido.multiply(percentual));
            notificacaoRepository.save(n);

            pedidoRepository.save(pedido);
        }
    }
}
