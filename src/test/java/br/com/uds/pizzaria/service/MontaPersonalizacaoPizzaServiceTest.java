package br.com.uds.pizzaria.service;

import static org.junit.Assert.*;

import br.com.uds.pizzaria.domain.Adicional;
import br.com.uds.pizzaria.domain.Item;
import br.com.uds.pizzaria.domain.Pedido;
import br.com.uds.pizzaria.domain.Produto;
import br.com.uds.pizzaria.repository.PedidoRepository;
import br.com.uds.pizzaria.service.dto.SolicitacaoClienteDto;
import br.com.uds.pizzaria.service.exception.SolicitacaoClienteException;
import br.com.uds.pizzaria.util.PopulaBancoDeDadosUtil;
import java.util.Arrays;
import java.util.HashSet;
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
public class MontaPersonalizacaoPizzaServiceTest {

  @Autowired EntityManager em;

  @Autowired MontaPersonalizacaoPizzaService montaPersonalizacaoPizzaService;

  @Autowired PedidoRepository pedidoRepository;

  Produto pizza;

  Adicional tamanhoGrande;

  Adicional saborMarguerita;

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
  }

  @Test
  public void testMontaPersonalizacao() {
    Pedido pedido = new Pedido();
    pedido.addItem(new Item(pizza, tamanhoGrande));
    pedido.getItemPorNomeProduto("Pizza").addAdicional(saborMarguerita);
    em.persist(pedido);

    int sizeBefore = pedido.getItemPorNomeProduto("Pizza").getAdicionais().size();

    assertEquals(
        "Adicionais incluídos com sucesso",
        montaPersonalizacaoPizzaService
            .montaPersonalizacao(
                new HashSet<>(
                    Arrays.asList(
                        new SolicitacaoClienteDto("Sem cebola"),
                        new SolicitacaoClienteDto("Extra bacon"))),
                pedido.getId())
            .getResposta());

    assertEquals(
        sizeBefore + 2,
        pedidoRepository
            .getOne(pedido.getId())
            .getItemPorNomeProduto("Pizza")
            .getAdicionais()
            .size());
  }

  @Test(expected = SolicitacaoClienteException.class)
  public void testMontaPersonalizacao_pedidoNaoEncontrado() {
    assertEquals(
        "Pedido não encontrado",
        montaPersonalizacaoPizzaService
            .montaPersonalizacao(
                new HashSet<>(
                    Arrays.asList(
                        new SolicitacaoClienteDto("Sem cebola"),
                        new SolicitacaoClienteDto("Extra bacon"))),
                0L)
            .getResposta());
  }

  @Test(expected = SolicitacaoClienteException.class)
  public void testMontaPersonalizacao_pizzaIncompleta() {
    Pedido pedido = new Pedido();
    pedido.addItem(new Item(pizza, tamanhoGrande));
    em.persist(pedido);

    assertEquals(
        "Pizza não contém sabor",
        montaPersonalizacaoPizzaService
            .montaPersonalizacao(
                new HashSet<>(
                    Arrays.asList(
                        new SolicitacaoClienteDto("Sem cebola"),
                        new SolicitacaoClienteDto("Extra bacon"))),
                pedido.getId())
            .getResposta());
  }

  @Test(expected = SolicitacaoClienteException.class)
  public void testMontaPersonalizacao_adicionalJaIncluso() {
    Pedido pedido = new Pedido();
    pedido.addItem(new Item(pizza, tamanhoGrande));
    pedido.getItemPorNomeProduto("Pizza").addAdicional(saborMarguerita);
    em.persist(pedido);

    montaPersonalizacaoPizzaService.montaPersonalizacao(
        new HashSet<>(Arrays.asList(new SolicitacaoClienteDto("Sem cebola"))), pedido.getId());

    assertEquals(
        "Já está incluso no pedido",
        montaPersonalizacaoPizzaService
            .montaPersonalizacao(
                new HashSet<>(
                    Arrays.asList(
                        new SolicitacaoClienteDto("Sem cebola"),
                        new SolicitacaoClienteDto("Extra bacon"))),
                pedido.getId())
            .getResposta());
  }
}
