package br.com.uds.pizzaria.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/** Classe que representa todos os domínios da aplicação, contendo id e dados de auditoria */
@MappedSuperclass
public abstract @Data class DominioEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "criado_em")
  @CreationTimestamp
  private ZonedDateTime criadoEm;

  @Column(name = "atualizado_em")
  @UpdateTimestamp
  private ZonedDateTime atualizadoEm;
}
