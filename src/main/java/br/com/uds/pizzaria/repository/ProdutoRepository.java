
/*
 * JUSTTO<br>
 *  Produto $JUSTTO APP - $${product_description}<br>
 *
 * Created: 11 2019, 03 <br>
 * <br>
 * All rights reserved.
 */

package br.com.uds.pizzaria.repository;


import br.com.uds.pizzaria.domain.Produto;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repositório de pedidos
 */

public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {

  Set<Produto> findAllByAtivoTrue();
}
