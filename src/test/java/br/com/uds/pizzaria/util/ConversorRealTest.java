package br.com.uds.pizzaria.util;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import org.junit.Test;

public class ConversorRealTest {

  @Test
  public void formata() {
    assertEquals("R$ 1,00", ConversorReal.formata(BigDecimal.ONE));
    assertEquals("R$ 0,00", ConversorReal.formata(BigDecimal.ZERO));
    assertEquals("R$ 17,00", ConversorReal.formata(new BigDecimal("17")));
  }
}