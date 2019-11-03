package br.com.uds.pizzaria.service.exception;

import br.com.uds.pizzaria.service.dto.SolicitacaoClienteDto;
import lombok.Data;

/**
 * Exception para tratamento de erros da solicitação do cliente
 */
public @Data class SolicitacaoClienteException extends RuntimeException{

  private SolicitacaoClienteDto solicitacao;

  public SolicitacaoClienteException(SolicitacaoClienteDto solicitacao) {
    super(solicitacao.getResposta());
    this.solicitacao = solicitacao;
  }
}
