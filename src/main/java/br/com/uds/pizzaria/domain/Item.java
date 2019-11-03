package br.com.uds.pizzaria.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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
    if (categoria == null) {
      return false;
    }

    return getAdicionais().stream()
        .anyMatch(a -> a.getCategoria().getNome().toUpperCase().equals(categoria.toUpperCase()));
  }

  public boolean hasAdicionalPorNomeECategoria(final String adicional, final String categoria) {

    if (adicional == null || categoria == null) {
      return false;
    }

    return getAdicionais().stream()
        .anyMatch(
            a ->
                a.getCategoria().getNome().toUpperCase().equals(categoria.toUpperCase())
                    && a.getNome().toUpperCase().equals(adicional.toUpperCase()));
  }

  public boolean isCompleto() {
    for (AdicionalCategoria categoria : produto.getCategoriasAdicionais()) {
      Long escolhasPorCategoria =
          getAdicionais().stream()
              .filter(a -> a.getCategoria().getNome().equals(categoria.getNome()))
              .count();

      if (categoria.getMinimo() > escolhasPorCategoria
          || categoria.getMaximo() < escolhasPorCategoria) {
        return false;
      }
    }

    return true;
  }

  public BigDecimal getValorTotal() {
    return produto
        .getPreco()
        .add(
            adicionais.stream()
                .map(ValoradoEntity::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
  }

  public Long getTempoPreparo() {
    return produto.getTempoPreparo()
        + adicionais.stream().mapToLong(ValoradoEntity::getTempoPreparo).sum();
  }
}
