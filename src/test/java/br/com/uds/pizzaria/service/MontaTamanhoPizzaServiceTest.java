package br.com.uds.pizzaria.service;

import static org.junit.Assert.*;

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
public class MontaTamanhoPizzaServiceTest {

  @Autowired EntityManager em;

  @Autowired MontaTamanhoPizzaService montaTamanhoPizzaService;

  @Autowired PedidoRepository pedidoRepository;

  @Before
  public void setUp() throws Exception {
    PopulaBancoDeDadosUtil.popula(em);
  }

  @Test
  public void montaTamanho() {

    long sizeBefore = pedidoRepository.count();

    assertEquals(
        "Tamanho adicionado com sucesso",
        montaTamanhoPizzaService.montaTamanho(new SolicitacaoClienteDto("média")).getResposta());

    assertEquals(
        "Tamanho adicionado com sucesso",
        montaTamanhoPizzaService.montaTamanho(new SolicitacaoClienteDto("pequena")).getResposta());

    assertEquals(
        "Tamanho adicionado com sucesso",
        montaTamanhoPizzaService.montaTamanho(new SolicitacaoClienteDto("grande")).getResposta());

    assertEquals(sizeBefore + 3, pedidoRepository.count());
  }

  @Test(expected = SolicitacaoClienteException.class)
  public void montaTamanho_notFound() {
    assertEquals(
        "broto não encontrada",
        montaTamanhoPizzaService.montaTamanho(new SolicitacaoClienteDto("broto")).getResposta());
  }
}
