package com.algaworks.algalog.domain.model;

import com.algaworks.algalog.domain.ValidationGroups;
import com.algaworks.algalog.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)   //Definindo apenas para propriedade que inclui
@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //como padrao o @Valid usa um Group chamado Default.class. Então estamos convertendo esse group para uma interface
    //que criamos chamada ValidationGroups que dentro dela tem outra chamado ClienteId. O motivo foi que o id de
    //cliente está notnull dentro, porém, quando vamos cadastra-lo somos obrigado, entao isso resolve.
//    @Valid
//    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    @ManyToOne
    private Cliente cliente;

    //Não queremos fazer uma tabela Destinatario, apenas outra classe para fazer bom uso do OO. Então o Embedded
    //diz que é outra classe, porem nao outra entidade, e sim apenas mais uma coluna dessa tabela (Entrega)
    //Tambem colocamos na classe (Destinatairio) a anotação @Embeddable para mapear. Ou seja, nada mais e nada menos
    //do que separar as colunas em outra classe.
    @Embedded
    private Destinatario destinatario;

    @OneToMany(mappedBy = "entrega", cascade =  CascadeType.ALL)
    private List<Ocorrencia> ocorrencia;

    //Esse validation pertence a o group Default
    private BigDecimal taxa;

    //Estamos dizendo para o JPA para armazenar essa enum como string, caso contrario, ele iria armazena-la como
    //numero ordinal
    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    private OffsetDateTime dataPedido;  //OffsetDateTime coloca um -3, que em relacao ao Meridiano, Brasil tem -3H

    //Nao permite atribuir valor a nesse campo no json, apenas leitura.
    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataFinalizacao;


    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);// O this referencia a propria classe (Entrega)

        this.getOcorrencia().add(ocorrencia);
        return ocorrencia;
    }

    public void finalizar() {
        if (!podeSerFinalizada()){
            throw new NegocioException("Entrega não pode ser finalizada");
        }

        setStatus(StatusEntrega.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }

    public boolean podeSerFinalizada(){
        return StatusEntrega.PENDENTE.equals(getStatus());
    }

    public boolean naoPodeSerFinalizada(){
        return !podeSerFinalizada();
    }
}
