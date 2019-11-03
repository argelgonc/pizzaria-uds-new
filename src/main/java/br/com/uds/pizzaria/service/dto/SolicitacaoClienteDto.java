package br.com.uds.pizzaria.service.dto;


import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa uma solicitação do cliente para montagem do pedido
 */
@NoArgsConstructor
public @Data class SolicitacaoClienteDto implements Serializable {

  private Long idPedido;

  @NotNull
  private String solicitacao;

  private String resposta;

  public SolicitacaoClienteDto(String solicitacao) {
    this.solicitacao = solicitacao;
  }

  public SolicitacaoClienteDto(String solicitacao, String resposta) {
    this.solicitacao = solicitacao;
    this.resposta = resposta;
  }
}
