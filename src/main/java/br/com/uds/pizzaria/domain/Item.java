package br.com.uds.pizzaria.domain;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

/**
 * Um item representa um produto dentro de um pedido, com seus atributos como os adicionais e quantidade
 */
@Entity
@Table(name = "item")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public @Data class Item extends DominioEntity{

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  private Pedido pedido;

  @NotNull
  @ColumnDefault("0")
  private Long quantidade;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  private Produto produto;

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Adicional> adicionais = new HashSet<>();

}
