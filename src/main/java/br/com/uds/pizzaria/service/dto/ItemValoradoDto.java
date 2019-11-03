package br.com.uds.pizzaria.service.dto;


import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa uma solicitação do cliente para montagem do pedido
 */
@NoArgsConstructor
@AllArgsConstructor
public @Data class ItemValoradoDto implements Serializable {

  private String nome;

  private String valor;
}
