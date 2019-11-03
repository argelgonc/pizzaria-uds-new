package br.com.uds.pizzaria.service;

import br.com.uds.pizzaria.domain.Item;
import br.com.uds.pizzaria.domain.Pedido;
import br.com.uds.pizzaria.repository.PedidoRepository;
import br.com.uds.pizzaria.service.dto.SolicitacaoClienteDto;
import br.com.uds.pizzaria.service.exception.SolicitacaoClienteException;
import java.time.ZonedDateTime;
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

    Optional<Pedido> pedido = pedidoRepository.findById(solicitacao.getIdPedido());

    if (pedido.isPresent()) {
      Item item = pedido.get().getItemPorNomeProduto("Pizza");
      if (item != null) {
        if (!item.hasAdicionalPorCategoria("Sabor")) {
          item.addAdicional(
              getAdicionalService.getAdicional(solicitacao.getSolicitacao(), "Sabor", "Pizza"));
          pedidoRepository.save(pedido.get());
          solicitacao.setResposta("Sabor adicionado com sucesso");
        } else {
          solicitacao.setResposta("Pizza já contém sabor escolhido");
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
