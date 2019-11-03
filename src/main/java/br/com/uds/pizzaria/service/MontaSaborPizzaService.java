package br.com.uds.pizzaria.service;

import br.com.uds.pizzaria.domain.Item;
import br.com.uds.pizzaria.domain.Pedido;
import br.com.uds.pizzaria.repository.PedidoRepository;
import br.com.uds.pizzaria.service.dto.SolicitacaoClienteDto;
import br.com.uds.pizzaria.service.exception.SolicitacaoClienteException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Serviço para montagem da pizza, recebendo o tamanho da pizza */
@Component
public class MontaSaborPizzaService {

  @Autowired PedidoRepository pedidoRepository;

  @Autowired GetAdicionalService getAdicionalService;

  @Transactional
  public SolicitacaoClienteDto montaSabor(SolicitacaoClienteDto solicitacao)
      throws SolicitacaoClienteException {

    Pedido pedido = pedidoRepository.getOne(solicitacao.getIdPedido());

    if (pedido != null) {
      Item item = pedido.getItemPorNomeProduto("Pizza");
      if (item != null) {
        if (!item.hasAdicionalPorCategoria("Sabor")) {
          item.addAdicional(
              getAdicionalService.getAdicional(solicitacao.getSolicitacao(), "Sabor", "Pizza"));
          pedidoRepository.save(pedido);
          solicitacao.setResposta("Sabor adicionado com sucesso");
          return solicitacao;
        } else {
          solicitacao.setResposta("Pizza já contém sabor");
        }
      } else {
        solicitacao.setResposta("Pedido não contém pizza");
      }
    } else {
      solicitacao.setResposta("Pedido não encontrado");
    }

    throw new SolicitacaoClienteException(solicitacao);
  }
}
