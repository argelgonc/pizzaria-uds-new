package br.com.uds.pizzaria.service;

import static org.junit.Assert.*;

import br.com.uds.pizzaria.domain.Adicional;
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
public class GetAdicionalServiceTest {

  @Autowired EntityManager em;

  @Autowired GetAdicionalService getAdicionalService;

  @Before
  public void setUp() throws Exception {
    PopulaBancoDeDadosUtil.popula(em);
  }

  @Test
  public void getAdicional() {
    assertEquals(
        "Calabresa", getAdicionalService.getAdicional("Calabresa", "Sabor", "Pizza").getNome());
    assertEquals(
        "Sem Cebola",
        getAdicionalService.getAdicional("Sem Cebola", "Adicional", "Pizza").getNome());
    assertEquals(
        "Grande", getAdicionalService.getAdicional("Grande", "Tamanho", "Pizza").getNome());
  }

  @Test(expected = SolicitacaoClienteException.class)
  public void getAdicional_notFound() {
    getAdicionalService.getAdicional("Calabresa", "Tamanho", "Pizza");
  }
}
