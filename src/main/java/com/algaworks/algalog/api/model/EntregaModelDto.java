package com.algaworks.algalog.api.model;

import com.algaworks.algalog.domain.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

//DTO
@Getter
@Setter
public class EntregaModelDto {

    private Long id;
    private ClienteResumoModelDto clienteResumoModelDto;
    private DestinatarioModelDto destinatarioModelDto;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
}
