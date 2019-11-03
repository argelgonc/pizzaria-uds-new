package br.com.uds.pizzaria.web;

import br.com.uds.pizzaria.domain.Produto;
import br.com.uds.pizzaria.service.MontaSaborPizzaService;
import br.com.uds.pizzaria.service.MontaTamanhoPizzaService;
import br.com.uds.pizzaria.service.ProdutoService;
import br.com.uds.pizzaria.service.dto.ResumoPizzaDto;
import br.com.uds.pizzaria.service.dto.SolicitacaoClienteDto;
import br.com.uds.pizzaria.service.exception.SolicitacaoClienteException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Camada Web para produtos */
@RestController
@RequestMapping("/api/pedidos/pizza")
public class PizzaController {

  @Autowired MontaTamanhoPizzaService montaTamanhoPizzaService;

  @Autowired MontaSaborPizzaService montaSaborPizzaService;

  @PostMapping("/montar/tamanho")
  public ResponseEntity<SolicitacaoClienteDto> montarTamanho(
      @RequestBody SolicitacaoClienteDto solicitacao) {
    try {
      return ResponseEntity.ok(montaTamanhoPizzaService.montaTamanho(solicitacao));
    } catch (SolicitacaoClienteException exception) {
      return ResponseEntity.badRequest().body(exception.getSolicitacao());
    }
  }

  @PutMapping("/{idPedido}/montar/sabor")
  public ResponseEntity<SolicitacaoClienteDto> montarSabor(
      @PathVariable Long idPedido, @RequestBody SolicitacaoClienteDto solicitacao) {
    try {
      solicitacao.setIdPedido(idPedido);
      return ResponseEntity.ok(montaSaborPizzaService.montaSabor(solicitacao));
    } catch (SolicitacaoClienteException exception) {
      return ResponseEntity.badRequest().body(exception.getSolicitacao());
    }
  }

  @PutMapping("/{idPedido}/montar/personalizar")
  public ResponseEntity<SolicitacaoClienteDto> personalizar(
      @PathVariable Long idPedido, @RequestBody SolicitacaoClienteDto body) {
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{idPedido}/resumo")
  public ResponseEntity<ResumoPizzaDto> resumo(
      @PathVariable Long idPedido, @RequestBody SolicitacaoClienteDto body) {
    return ResponseEntity.ok().build();
  }
}
