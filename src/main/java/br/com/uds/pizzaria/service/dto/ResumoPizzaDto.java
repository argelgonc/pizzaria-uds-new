package br.com.uds.pizzaria.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa o resumo de um pedido de pizza
 */
@NoArgsConstructor
public @Data class ResumoPizzaDto implements Serializable {

  private String tamanho;

  private String sabor;

  private Set<String> personalizacoes = new HashSet<>();

  private String valorTotal;

  private Long tempoDePreparo;

}
