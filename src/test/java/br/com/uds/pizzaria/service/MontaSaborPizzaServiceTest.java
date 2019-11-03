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
public class MontaSaborPizzaServiceTest {

  @Autowired EntityManager em;

  @Autowired MontaSaborPizzaService montaSaborPizzaService;

  @Autowired
  PedidoRepository pedidoRepository;

  Produto pizza;

  Adicional tamanhoGrande;

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
  }

  @Test
  public void testMontaSabor() {
    Pedido pedido = new Pedido();
    pedido.addItem(new Item(pizza, tamanhoGrande));
    em.persist(pedido);

    SolicitacaoClienteDto solicitacao = new SolicitacaoClienteDto();
    solicitacao.setIdPedido(pedido.getId());
    solicitacao.setSolicitacao("Calabresa");

    int sizeBefore = pedido.getItemPorNomeProduto("Pizza").getAdicionais().size();

    assertEquals(
        "Sabor adicionado com sucesso",
        montaSaborPizzaService.montaSabor(solicitacao).getResposta());

    assertEquals(
        sizeBefore + 1,
        pedidoRepository
            .getOne(pedido.getId())
            .getItemPorNomeProduto("Pizza")
            .getAdicionais()
            .size());
  }

  @Test(expected = SolicitacaoClienteException.class)
  public void testMontaSabor_semPedido() {
    SolicitacaoClienteDto solicitacao = new SolicitacaoClienteDto();
    solicitacao.setIdPedido(0L);
    solicitacao.setSolicitacao("Calabresa");

    assertEquals(
        "Pedido não encontrado", montaSaborPizzaService.montaSabor(solicitacao).getResposta());
  }

  @Test(expected = SolicitacaoClienteException.class)
  public void testMontaSabor_semPizza() {
    Pedido pedido = new Pedido();
    em.persist(pedido);

    SolicitacaoClienteDto solicitacao = new SolicitacaoClienteDto();
    solicitacao.setIdPedido(pedido.getId());
    solicitacao.setSolicitacao("Calabresa");

    assertEquals(
        "Pedido não contém pizza", montaSaborPizzaService.montaSabor(solicitacao).getResposta());
  }

  @Test(expected = SolicitacaoClienteException.class)
  public void testMontaSabor_jaPossuiSabor() {
    Pedido pedido = new Pedido();
    pedido.addItem(new Item(pizza, tamanhoGrande));
    em.persist(pedido);

    SolicitacaoClienteDto solicitacao = new SolicitacaoClienteDto();
    solicitacao.setIdPedido(pedido.getId());
    solicitacao.setSolicitacao("Calabresa");

    montaSaborPizzaService.montaSabor(solicitacao);
    assertEquals(
        "Pedido já contem sabor", montaSaborPizzaService.montaSabor(solicitacao).getResposta());
  }
}
