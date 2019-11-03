package br.com.uds.pizzaria.service;

import br.com.uds.pizzaria.domain.Adicional;
import br.com.uds.pizzaria.domain.Item;
import br.com.uds.pizzaria.domain.Pedido;
import br.com.uds.pizzaria.domain.Produto;
import br.com.uds.pizzaria.repository.PedidoRepository;
import br.com.uds.pizzaria.repository.ProdutoRepository;
import br.com.uds.pizzaria.service.dto.SolicitacaoClienteDto;
import br.com.uds.pizzaria.service.exception.SolicitacaoClienteException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Serviço para montagem da pizza, recebendo o tamanho da pizza */
@Component
public class MontaTamanhoPizzaService {

  @Autowired ProdutoRepository produtoRepository;

  @Autowired PedidoRepository pedidoRepository;

  public SolicitacaoClienteDto montaTamanho(SolicitacaoClienteDto solicitacao)
      throws SolicitacaoClienteException {

    Adicional tamanho = getTamanho(solicitacao.getSolicitacao());
    Pedido pedido =
        pedidoRepository.save(new Pedido(new Item(tamanho.getCategoria().getProduto(), tamanho)));

    solicitacao.setResposta("Tamanho adicionado com sucesso");
    solicitacao.setIdPedido(pedido.getId());

    return solicitacao;
  }

  private Adicional getTamanho(String adicional) throws SolicitacaoClienteException {
    Optional<Adicional> tamanho =
        produtoRepository
            .findByAdicionalAndCategoriaAndProduto(adicional, "Tamanho", "Pizza")
            .findFirst();

    if (tamanho.isPresent()
        && tamanho.get().getCategoria() != null
        && tamanho.get().getCategoria().getProduto() != null) {
      return tamanho.get();
    } else {
      throw new SolicitacaoClienteException(
          new SolicitacaoClienteDto(adicional, "Tamanho não encontrado"));
    }
  }
}
