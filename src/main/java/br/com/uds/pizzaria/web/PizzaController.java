package br.com.uds.pizzaria.web;


import br.com.uds.pizzaria.domain.Produto;
import br.com.uds.pizzaria.service.ProdutoService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Camada Web para produtos
 */
@RestController
@RequestMapping("/api/pedidos/pizza")
public class PizzaController {

  @Autowired
  ProdutoService produtoService;

  @PostMapping("/montar/tamanho")
  public ResponseEntity<Void> montarTamanho() {
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{idPedido}/montar/sabor")
  public ResponseEntity<Void> montarSabor(@PathVariable Long idPedido) {
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{idPedido}/personalizar")
  public ResponseEntity<Void> personalizar(@PathVariable Long idPedido) {
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{idPedido}/resumo")
  public ResponseEntity<Void> resumo(@PathVariable Long idPedido) {
    return ResponseEntity.ok().build();
  }

}
