package br.com.uds.pizzaria.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Um pedido representa uma ordem de compra na pizzaria, onde cada um pode ter um cliente e seus
 * itens
 */
@Entity
@Table(name = "pedido")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public @Data class Pedido extends DominioEntity {

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pedido")
  private Set<Item> itens = new HashSet<>();

  public Pedido(Item item) {
    addItem(item);
  }

  public void addItem(Item item) {
    if (item != null) {
      item.setPedido(this);
      this.getItens().add(item);
    }
  }

  public Item getItemPorNomeProduto(String nomeProduto) {
    return itens.stream()
        .filter(i -> i.getProduto().getNome().equals(nomeProduto))
        .findFirst()
        .orElse(null);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Pedido)) {
      return false;
    }

    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
