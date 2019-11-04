package br.com.uds.pizzaria.service;

import br.com.uds.pizzaria.domain.Item;
import br.com.uds.pizzaria.domain.Pedido;
import br.com.uds.pizzaria.repository.PedidoRepository;
import br.com.uds.pizzaria.service.dto.SolicitacaoClienteDto;
import br.com.uds.pizzaria.service.exception.SolicitacaoClienteException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Serviço para montagem da pizza, recebendo as personalizações da pizza */
@Component
public class MontaPersonalizacaoPizzaService {

  @Autowired PedidoRepository pedidoRepository;

  @Autowired GetAdicionalService getAdicionalService;

  @Transactional
  public SolicitacaoClienteDto montaPersonalizacao(
      Set<SolicitacaoClienteDto> solicitacoes, Long idPedido) throws SolicitacaoClienteException {

    Pedido pedido = pedidoRepository.findById(idPedido).orElse(null);

    if (pedido != null) {
      Item item = pedido.getItemPorNomeProduto("Pizza");

      if (item != null) {

        if (!item.hasAdicionalPorCategoria("Sabor")) {
          throw new SolicitacaoClienteException(
              new SolicitacaoClienteDto(
                  "Adicionais", "Pizza necessita de sabor para incluir adicionais"));
        }

        for (SolicitacaoClienteDto solicitacao : solicitacoes) {
          if (item.hasAdicionalPorNomeECategoria(solicitacao.getSolicitacao(), "Adicional")) {
            throw new SolicitacaoClienteException(
                new SolicitacaoClienteDto(
                    solicitacao.getSolicitacao(), "Já está incluso no pedido"));
          } else {
            item.addAdicional(
                getAdicionalService.getAdicional(
                    solicitacao.getSolicitacao(), "Adicional", "Pizza"));
          }

          pedidoRepository.save(pedido);
        }
      } else {
        throw new SolicitacaoClienteException(
            new SolicitacaoClienteDto(
                "Adicionais", "Pedido não contém pizza"));
      }
    } else {
      throw new SolicitacaoClienteException(
          new SolicitacaoClienteDto(
              "Adicionais", "Pedido não encontrado"));
    }

    return new SolicitacaoClienteDto("Adicionais", "Adicionais incluídos com sucesso");
  }
}
