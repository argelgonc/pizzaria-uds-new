package br.com.uds.pizzaria.service;

import br.com.uds.pizzaria.domain.Produto;
import br.com.uds.pizzaria.repository.ProdutoRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Representa os serviços básicos para acesso aos produtos
 */
@Component
@Transactional
public class ProdutoService {

  @Autowired
  ProdutoRepository produtoRepository;

  public Set<Produto> getProdutos() {
    return produtoRepository.findAllByAtivoTrue();
  }

}
