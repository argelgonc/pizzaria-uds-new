package br.com.uds.pizzaria.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Converte um BigDecimal para o formato de moeda brasileita
 */
public class ConversorReal {

  public static String formata(final BigDecimal valor) {
    if (valor != null) {
      return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor);
    } else {
      return null;
    }
  }

}
