package br.com.uds.pizzaria.service;

import br.com.uds.pizzaria.domain.Adicional;
import br.com.uds.pizzaria.domain.Item;
import br.com.uds.pizzaria.domain.Pedido;
import br.com.uds.pizzaria.repository.PedidoRepository;
import br.com.uds.pizzaria.repository.ProdutoRepository;
import br.com.uds.pizzaria.service.dto.SolicitacaoClienteDto;
import br.com.uds.pizzaria.service.exception.SolicitacaoClienteException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Serviço para montagem da pizza, recebendo o adicional da pizza */
@Component
public class GetAdicionalService {

  @Autowired ProdutoRepository produtoRepository;

  @Transactional
  public Adicional getAdicional(String adicional, String categoria, String produto) throws SolicitacaoClienteException {
    Optional<Adicional> optAdicional =
        produtoRepository
            .findByAdicionalAndCategoriaAndProduto(adicional, categoria, produto)
            .findFirst();

    if (optAdicional.isPresent()
        && optAdicional.get().getCategoria() != null
        && optAdicional.get().getCategoria().getProduto() != null) {
      return optAdicional.get();
    } else {
      throw new SolicitacaoClienteException(
          new SolicitacaoClienteDto(adicional, adicional + " não encontrado"));
    }
  }
}
