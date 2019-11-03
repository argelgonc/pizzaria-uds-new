package br.com.uds.pizzaria.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Um adicional representa personalização do produto, ela deve ser categoria para melhor tratamento
 * de regra de negócio ao finalizar um pedido
 */
@Entity
@Table(name = "adicional")
@Data
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Adicional extends ValoradoEntity {

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private AdicionalCategoria categoria;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Adicional)) {
      return false;
    }

    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
