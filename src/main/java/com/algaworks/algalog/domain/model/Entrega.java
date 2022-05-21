package com.algaworks.algalog.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)   //Definindo apenas para propriedade que inclui
@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    //Não queremos fazer uma tabela Destinatario, apenas outra classe para fazer bom uso do OO. Então o Embedded
    //diz que é outra classe, porem nao outra entidade, e sim apenas mais uma coluna dessa tabela (Entrega)
    //Tambem colocamos na classe (Destinatairio) a anotação @Embeddable para mapear. Ou seja, nada mais e nada menos
    //do que separar as colunas em outra classe.
    @Embedded
    private Destinatario destinatario;

    private BigDecimal taxa;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    //Estamos dizendo para o JPA para armazenar essa enum como string, caso contrario, ele iria armazena-la como
    //numero ordinal
    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataFinalizacao;
}
