package br.com.uds.pizzaria.service;

import br.com.uds.pizzaria.domain.Item;
import br.com.uds.pizzaria.domain.Pedido;
import br.com.uds.pizzaria.domain.ValoradoEntity;
import br.com.uds.pizzaria.repository.PedidoRepository;
import br.com.uds.pizzaria.service.dto.ItemValoradoDto;
import br.com.uds.pizzaria.service.dto.ResumoPizzaDto;
import br.com.uds.pizzaria.service.dto.SolicitacaoClienteDto;
import br.com.uds.pizzaria.service.exception.SolicitacaoClienteException;
import br.com.uds.pizzaria.util.ConversorReal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Serviço para montagem da pizza, recebendo as personalizações da pizza */
@Component
public class GetResumoPizzaService {

  @Autowired PedidoRepository pedidoRepository;

  @Transactional
  public ResumoPizzaDto getResumo(Long idPedido) throws SolicitacaoClienteException {

    Pedido pedido = pedidoRepository.getOne(idPedido);

    if (pedido != null) {
      Item item = pedido.getItemPorNomeProduto("Pizza");

      if (item != null) {
        if (item.isCompleto()) {
          return montaResumo(pedido);
        } else {
          throw new SolicitacaoClienteException(
              new SolicitacaoClienteDto("Resumo", "Pedido não está completo"));
        }
      } else {
        throw new SolicitacaoClienteException(
            new SolicitacaoClienteDto("Resumo", "Pedido não contém pizza"));
      }
    } else {
      throw new SolicitacaoClienteException(
          new SolicitacaoClienteDto("Resumo", "Pedido não encontrado"));
    }
  }

  private ResumoPizzaDto montaResumo(Pedido pedido) {
    ResumoPizzaDto dto = new ResumoPizzaDto();
    Item item = pedido.getItemPorNomeProduto("Pizza");
    dto.setTamanho(
        item.getAdicionais().stream()
            .filter(a -> a.getCategoria().getNome().equals("Tamanho"))
            .map(a -> new ItemValoradoDto(a.getNome(), ConversorReal.formata(a.getPreco())))
            .findFirst()
            .orElse(null));
    dto.setSabor(
        item.getAdicionais().stream()
            .filter(a -> a.getCategoria().getNome().equals("Sabor"))
            .map(a -> new ItemValoradoDto(a.getNome(), ConversorReal.formata(a.getPreco())))
            .findFirst()
            .orElse(null));
    dto.setPersonalizacoes(
        item.getAdicionais().stream()
            .filter(a -> a.getCategoria().getNome().equals("Adicional"))
            .map(a -> new ItemValoradoDto(a.getNome(), ConversorReal.formata(a.getPreco())))
            .collect(Collectors.toSet()));
    dto.setValorTotal(ConversorReal.formata(pedido.getValorTotal()));
    dto.setTempoDePreparo(pedido.getTempoPreparo());
    return dto;
  }
}
