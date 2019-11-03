package br.com.uds.pizzaria.util;

import br.com.uds.pizzaria.domain.Adicional;
import br.com.uds.pizzaria.domain.AdicionalCategoria;
import br.com.uds.pizzaria.domain.Produto;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.persistence.EntityManager;

/**
 *
 */
public class PopulaBancoDeDadosUtil {

  public static Produto popula(EntityManager entityManager) {

    Produto produto = new Produto();
    produto.setNome("Pizza");
    produto.setPreco(BigDecimal.ZERO);
    produto.setCriadoEm(ZonedDateTime.now());
    produto.setAtivo(true);
    produto.setTempoPreparo(0L);

    produto = adicionaTamanhos(produto);
    produto = adicionaSabores(produto);
    produto = adicionaAdicionais(produto);

    entityManager.clear();
    entityManager.persist(produto);
    entityManager.flush();

    return produto;
  }

  private static Produto adicionaTamanhos(Produto produto) {
    AdicionalCategoria tamanho = new AdicionalCategoria();
    tamanho.setNome("Tamanho");
    tamanho.setAtivo(true);
    tamanho.setMinimo(1);
    tamanho.setMaximo(1);
    tamanho.setProduto(produto);
    produto.getCategoriasAdicionais().add(tamanho);

    Adicional pequena = new Adicional();
    pequena.setNome("Pequena");
    pequena.setPreco(new BigDecimal("20.00"));
    pequena.setCriadoEm(ZonedDateTime.now());
    pequena.setAtivo(true);
    pequena.setTempoPreparo(15L);
    pequena.setCategoria(tamanho);
    tamanho.getAdicionais().add(pequena);

    Adicional media = new Adicional();
    media.setNome("Média");
    media.setPreco(new BigDecimal("30.00"));
    media.setCriadoEm(ZonedDateTime.now());
    media.setAtivo(true);
    media.setTempoPreparo(20L);
    media.setCategoria(tamanho);
    tamanho.getAdicionais().add(media);

    Adicional grande = new Adicional();
    grande.setNome("Grande");
    grande.setPreco(new BigDecimal("35.00"));
    grande.setCriadoEm(ZonedDateTime.now());
    grande.setAtivo(true);
    grande.setTempoPreparo(25L);
    grande.setCategoria(tamanho);
    tamanho.getAdicionais().add(grande);

    return produto;
  }

  private static Produto adicionaSabores(Produto produto) {
    AdicionalCategoria sabor = new AdicionalCategoria();
    sabor.setCriadoEm(ZonedDateTime.now());
    sabor.setNome("Sabor");
    sabor.setAtivo(true);
    sabor.setMinimo(1);
    sabor.setMaximo(1);
    sabor.setProduto(produto);
    produto.getCategoriasAdicionais().add(sabor);

    Adicional calabresa = new Adicional();
    calabresa.setNome("Calabresa");
    calabresa.setPreco(new BigDecimal("0.00"));
    calabresa.setCriadoEm(ZonedDateTime.now());
    calabresa.setAtivo(true);
    calabresa.setTempoPreparo(0L);
    calabresa.setCategoria(sabor);
    sabor.getAdicionais().add(calabresa);

    Adicional portuguesa = new Adicional();
    portuguesa.setNome("Portuguesa");
    portuguesa.setPreco(new BigDecimal("0.00"));
    portuguesa.setCriadoEm(ZonedDateTime.now());
    portuguesa.setAtivo(true);
    portuguesa.setTempoPreparo(5L);
    portuguesa.setCategoria(sabor);
    sabor.getAdicionais().add(portuguesa);

    Adicional marguerita = new Adicional();
    marguerita.setNome("Marguerita");
    marguerita.setPreco(new BigDecimal("0.00"));
    marguerita.setCriadoEm(ZonedDateTime.now());
    marguerita.setAtivo(true);
    marguerita.setTempoPreparo(0L);
    marguerita.setCategoria(sabor);
    sabor.getAdicionais().add(marguerita);

    return produto;
  }

  private static Produto adicionaAdicionais(Produto produto) {
    AdicionalCategoria adicionais = new AdicionalCategoria();
    adicionais.setCriadoEm(ZonedDateTime.now());
    adicionais.setNome("Adicional");
    adicionais.setAtivo(true);
    adicionais.setMinimo(0);
    adicionais.setMaximo(100);
    adicionais.setProduto(produto);
    produto.getCategoriasAdicionais().add(adicionais);

    Adicional semCebola = new Adicional();
    semCebola.setNome("Sem Cebola");
    semCebola.setPreco(new BigDecimal("0.00"));
    semCebola.setCriadoEm(ZonedDateTime.now());
    semCebola.setAtivo(true);
    semCebola.setTempoPreparo(0L);
    semCebola.setCategoria(adicionais);
    adicionais.getAdicionais().add(semCebola);

    Adicional extraBacon = new Adicional();
    extraBacon.setNome("Extra Bacon");
    extraBacon.setPreco(new BigDecimal("3.00"));
    extraBacon.setCriadoEm(ZonedDateTime.now());
    extraBacon.setAtivo(true);
    extraBacon.setTempoPreparo(0L);
    extraBacon.setCategoria(adicionais);
    adicionais.getAdicionais().add(extraBacon);

    Adicional bordaRecheada = new Adicional();
    bordaRecheada.setNome("Marguerita");
    bordaRecheada.setPreco(new BigDecimal("5.00"));
    bordaRecheada.setCriadoEm(ZonedDateTime.now());
    bordaRecheada.setAtivo(true);
    bordaRecheada.setTempoPreparo(5L);
    bordaRecheada.setCategoria(adicionais);
    adicionais.getAdicionais().add(bordaRecheada);

    return produto;
  }

}
