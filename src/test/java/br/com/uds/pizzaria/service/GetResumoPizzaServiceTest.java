package br.com.uds.pizzaria.service;

import static org.junit.Assert.*;

import br.com.uds.pizzaria.domain.Adicional;
import br.com.uds.pizzaria.domain.Item;
import br.com.uds.pizzaria.domain.Pedido;
import br.com.uds.pizzaria.domain.Produto;
import br.com.uds.pizzaria.service.dto.ResumoPizzaDto;
import br.com.uds.pizzaria.service.exception.SolicitacaoClienteException;
import br.com.uds.pizzaria.util.PopulaBancoDeDadosUtil;
import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GetResumoPizzaServiceTest {

  @Autowired
  EntityManager em;

  @Autowired GetResumoPizzaService getResumoPizzaService;

  Produto pizza;

  Adicional tamanhoGrande;

  Adicional saborMarguerita;

  Adicional extraBacon;

  @Before
  public void setUp() throws Exception {
    pizza = PopulaBancoDeDadosUtil.popula(em);
    tamanhoGrande =
        pizza.getCategoriasAdicionais().stream()
            .filter(c -> c.getNome().equals("Tamanho"))
            .findFirst()
            .get()
            .getAdicionais()
            .stream()
            .filter(a -> a.getNome().equals("Grande"))
            .findFirst()
            .orElse(null);
    saborMarguerita =
        pizza.getCategoriasAdicionais().stream()
            .filter(c -> c.getNome().equals("Sabor"))
            .findFirst()
            .get()
            .getAdicionais()
            .stream()
            .filter(a -> a.getNome().equals("Marguerita"))
            .findFirst()
            .orElse(null);
    extraBacon =
        pizza.getCategoriasAdicionais().stream()
            .filter(c -> c.getNome().equals("Adicional"))
            .findFirst()
            .get()
            .getAdicionais()
            .stream()
            .filter(a -> a.getNome().equals("Extra Bacon"))
            .findFirst()
            .orElse(null);
  }


  @Test
  public void getResumo() {
    Pedido pedido = new Pedido();
    pedido.addItem(new Item(pizza, tamanhoGrande));
    pedido.getItemPorNomeProduto("Pizza").addAdicional(saborMarguerita);
    pedido.getItemPorNomeProduto("Pizza").addAdicional(extraBacon);
    em.persist(pedido);

    ResumoPizzaDto resumo = getResumoPizzaService.getResumo(pedido.getId());

    assertEquals("Grande", resumo.getTamanho().getNome());
    assertEquals("R$ 35,00", resumo.getTamanho().getValor());
    assertEquals("Marguerita", resumo.getSabor().getNome());
    assertEquals("R$ 0,00", resumo.getSabor().getValor());
    assertEquals("Extra Bacon", resumo.getPersonalizacoes().stream().findFirst().get().getNome());
    assertEquals("R$ 3,00", resumo.getPersonalizacoes().stream().findFirst().get().getValor());
    assertEquals(Long.valueOf(25), resumo.getTempoDePreparo());
    assertEquals("R$ 38,00", resumo.getValorTotal());
  }

  @Test(expected = SolicitacaoClienteException.class)
  public void getResumo_pedidoNaoEncontrado() {
     getResumoPizzaService.getResumo(0L);
  }

  @Test(expected = SolicitacaoClienteException.class)
  public void getResumo_pizzaIncompleta() {
    Pedido pedido = new Pedido();
    pedido.addItem(new Item(pizza, tamanhoGrande));
    em.persist(pedido);

    getResumoPizzaService.getResumo(pedido.getId());
  }
}