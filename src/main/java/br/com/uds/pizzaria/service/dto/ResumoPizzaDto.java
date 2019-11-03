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

  private ItemValoradoDto tamanho;

  private ItemValoradoDto sabor;

  private Set<ItemValoradoDto> personalizacoes = new HashSet<>();

  private String valorTotal;

  private Long tempoDePreparo;

}
