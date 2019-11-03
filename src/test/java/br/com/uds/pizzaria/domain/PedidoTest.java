package br.com.uds.pizzaria.domain;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.Test;

public class PedidoTest {

  @Test
  public void getItemPorNomeProduto() {
    Pedido pedido = new Pedido();

    Produto produto = new Produto();
    produto.setNome("Pizza");

    pedido.addItem(new Item(produto, null));

    assertNotNull(pedido.getItemPorNomeProduto("Pizza"));
    assertNull(pedido.getItemPorNomeProduto("Lanche"));
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

    Pedido pedido = new Pedido();
    pedido.addItem(item);

    assertEquals(new BigDecimal("30").setScale(2, RoundingMode.CEILING),
        pedido.getValorTotal().setScale(2, RoundingMode.CEILING));
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

    Pedido pedido = new Pedido();
    pedido.addItem(item);

    assertEquals(Long.valueOf(30L), pedido.getTempoPreparo());
  }
}