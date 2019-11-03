package br.com.uds.pizzaria.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.Test;

public class ItemTest {

  @Test
  public void addAdicional() {
    Item item = new Item();
    item.addAdicional(new Adicional());
    assertEquals(1, item.getAdicionais().size());
    item.addAdicional(null);
    assertEquals(1, item.getAdicionais().size());
  }

  @Test
  public void hasAdicionalPorCategoria() {
    Adicional adicional = new Adicional();
    adicional.setCategoria(new AdicionalCategoria());
    adicional.getCategoria().setNome("Categoria");

    Item item = new Item();
    item.addAdicional(adicional);

    assertTrue(item.hasAdicionalPorCategoria("Categoria"));
    assertFalse(item.hasAdicionalPorCategoria("Categoria1"));
  }

  @Test
  public void hasAdicionalPorNomeECategoria() {
    Adicional adicional = new Adicional();
    adicional.setCategoria(new AdicionalCategoria());
    adicional.getCategoria().setNome("Categoria");
    adicional.setNome("Nome");

    Item item = new Item();
    item.addAdicional(adicional);

    assertTrue(item.hasAdicionalPorNomeECategoria("Nome", "Categoria"));
    assertFalse(item.hasAdicionalPorNomeECategoria("Nome2", "Categoria"));
  }

  @Test
  public void isCompleto() {
    Adicional adicional = new Adicional();
    adicional.setCategoria(new AdicionalCategoria());
    adicional.getCategoria().getAdicionais().add(adicional);
    adicional.getCategoria().setNome("Categoria");
    adicional.getCategoria().setMinimo(1);
    adicional.getCategoria().setMaximo(1);
    adicional.setNome("Nome");

    Produto produto = new Produto();
    produto.getCategoriasAdicionais().add(adicional.getCategoria());

    Item item = new Item();
    item.setProduto(produto);
    item.addAdicional(adicional);

    assertTrue(item.isCompleto());
    adicional.getCategoria().setMinimo(2);
    assertFalse(item.isCompleto());
  }

  @Test
  public void getValorTotal() {

    Item item = new Item();
    Adicional adicional1 = new Adicional();
    adicional1.setPreco(BigDecimal.TEN);
    item.addAdicional(adicional1);

    Adicional adicional2 = new Adicional();
    adicional2.setPreco(BigDecimal.ONE);
    item.addAdicional(adicional2);

    Adicional adicional3 = new Adicional();
    adicional3.setPreco(BigDecimal.ZERO);
    item.addAdicional(adicional3);

    Produto produto = new Produto();
    produto.setPreco(new BigDecimal(("19")));
    item.setProduto(produto);

    assertEquals(new BigDecimal("30").setScale(2, RoundingMode.CEILING),
        item.getValorTotal().setScale(2, RoundingMode.CEILING));

  }

  @Test
  public void getTempoPreparo() {
    Item item = new Item();
    Adicional adicional1 = new Adicional();
    adicional1.setTempoPreparo(10L);
    item.addAdicional(adicional1);

    Adicional adicional2 = new Adicional();
    adicional2.setTempoPreparo(1L);
    item.addAdicional(adicional2);

    Adicional adicional3 = new Adicional();
    adicional3.setTempoPreparo(0L);
    item.addAdicional(adicional3);

    Produto produto = new Produto();
    produto.setTempoPreparo(19L);
    item.setProduto(produto);

    assertEquals(Long.valueOf(30L), item.getTempoPreparo());
  }
}