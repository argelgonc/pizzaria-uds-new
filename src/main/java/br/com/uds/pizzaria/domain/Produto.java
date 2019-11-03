package br.com.uds.pizzaria.domain;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Um produto representa um item a ser adicionado no pedido, com suas características
 */
@Entity
@Table(name = "produto")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public @Data class Produto extends ValoradoEntity{

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "produto")
  Set<AdicionalCategoria> categoriasAdicionais = new HashSet<>();

}
