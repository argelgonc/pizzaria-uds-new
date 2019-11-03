/*
 * JUSTTO<br>
 *  Produto $JUSTTO APP - $${product_description}<br>
 *
 * Created: 11 2019, 03 <br>
 * <br>
 * All rights reserved.
 */

package br.com.uds.pizzaria.repository;

import br.com.uds.pizzaria.domain.Adicional;
import br.com.uds.pizzaria.domain.Produto;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** Repositório de pedidos */
public interface ProdutoRepository
    extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {

  Set<Produto> findAllByAtivoTrue();

  @Query(
      "SELECT a FROM Adicional a "
          + "INNER JOIN a.categoria c "
          + "INNER JOIN c.produto p "
          + "WHERE  upper(c.nome) = upper(:categoria) "
          + "AND upper(a.nome) = upper(:adicional) "
          + "AND upper(p.nome) = upper(:produto)")
  Stream<Adicional> findByAdicionalAndCategoriaAndProduto(
      @Param("adicional") String adicional,
      @Param("categoria") String categoria,
      @Param("produto") String produto);

  Optional<Produto> findFirstByNomeIgnoreCase(String nome);
}
