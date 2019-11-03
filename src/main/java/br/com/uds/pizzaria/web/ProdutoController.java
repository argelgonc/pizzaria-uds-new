package br.com.uds.pizzaria.web;


import br.com.uds.pizzaria.domain.Produto;
import br.com.uds.pizzaria.service.ProdutoService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Camada Web para produtos
 */
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

  @Autowired
  ProdutoService produtoService;

  @GetMapping
  public ResponseEntity<Set<Produto>> getProdutos() {
    return ResponseEntity.ok(produtoService.getProdutos());
  }

}
