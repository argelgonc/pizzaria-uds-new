package br.com.uds.pizzaria.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

/** Classe que representa todas as entidades que podem ser valoradas com tempo de preparo e preço */
@MappedSuperclass
public abstract @Data class ValoradoEntity extends DominioEntity {

  @NotNull
  private String nome;

  @NotNull
  @ColumnDefault("0.00")
  private BigDecimal preco;

  @Column(name = "tempo_preparo")
  @ColumnDefault("0")
  private Long tempoPreparo;

  @ColumnDefault("FALSE")
  private Boolean ativo = Boolean.FALSE;

}
