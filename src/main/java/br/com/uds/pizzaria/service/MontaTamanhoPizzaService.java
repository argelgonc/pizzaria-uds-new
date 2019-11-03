package br.com.uds.pizzaria.service;

import br.com.uds.pizzaria.domain.Adicional;
import br.com.uds.pizzaria.domain.Item;
import br.com.uds.pizzaria.domain.Pedido;
import br.com.uds.pizzaria.repository.PedidoRepository;
import br.com.uds.pizzaria.service.dto.SolicitacaoClienteDto;
import br.com.uds.pizzaria.service.exception.SolicitacaoClienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Serviço para montagem da pizza, recebendo o tamanho da pizza */
@Component
public class MontaTamanhoPizzaService {

  @Autowired PedidoRepository pedidoRepository;

  @Autowired GetAdicionalService getAdicionalService;

  @Transactional
  public SolicitacaoClienteDto montaTamanho(SolicitacaoClienteDto solicitacao)
      throws SolicitacaoClienteException {

    Adicional tamanho =
        getAdicionalService.getAdicional(solicitacao.getSolicitacao(), "Tamanho", "Pizza");

    Pedido pedido =
        pedidoRepository.save(new Pedido(new Item(tamanho.getCategoria().getProduto(), tamanho)));

    solicitacao.setResposta("Tamanho adicionado com sucesso");
    solicitacao.setIdPedido(pedido.getId());

    return solicitacao;
  }
}
