package br.com.uds.pizzaria.domain;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

/**
 * Classe que representa uma categoria de adicionais Uma categoria de adicionais é utilizada para
 * permitir diferentes personalizações de um produto. Por exemplo, para uma pizza é possivel
 * adicionar categorias de tamanhos, sabores e outros
 */
@Entity
@Table(name = "adicional_categoria")
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public @Data class AdicionalCategoria extends DominioEntity {

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  private Produto produto;

  @NotNull
  private String nome;

  @ColumnDefault("FALSE")
  private Boolean ativo = Boolean.FALSE;

  @ColumnDefault("0")
  private Integer minimo;

  @ColumnDefault("100")
  private Integer maximo;

  @NotNull
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "categoria")
  private Set<Adicional> adicionais = new HashSet<>();

}
