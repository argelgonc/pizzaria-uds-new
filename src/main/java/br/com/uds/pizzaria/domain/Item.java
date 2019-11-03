package br.com.uds.pizzaria.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

/**
 * Um item representa um produto dentro de um pedido, com seus atributos como os adicionais e
 * quantidade
 */
@Entity
@Table(name = "item")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public @Data class Item extends DominioEntity {

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private Pedido pedido;

  @NotNull
  @ColumnDefault("0")
  private Long quantidade;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  private Produto produto;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "item_adicionais",
      joinColumns = {@JoinColumn(name = "item_id")},
      inverseJoinColumns = {@JoinColumn(name = "adicionais_id")})
  private Set<Adicional> adicionais = new HashSet<>();

  public Item(Produto produto, Adicional adicional) {
    this.quantidade = 1L;
    this.produto = produto;
    this.addAdicional(adicional);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Item)) {
      return false;
    }

    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  public void addAdicional(final Adicional adicional) {
    if (adicional != null) {
      getAdicionais().add(adicional);
    }
  }

  public boolean hasAdicionalPorCategoria(final String categoria) {
    return getAdicionais().stream().anyMatch(a -> a.getCategoria().getNome().equals(categoria));
  }

  public boolean hasAdicionalPorNomeECategoria(final String adicional, final String categoria) {
    return getAdicionais().stream()
        .anyMatch(
            a -> a.getCategoria().getNome().equals(categoria) && a.getNome().equals(adicional));
  }
}
