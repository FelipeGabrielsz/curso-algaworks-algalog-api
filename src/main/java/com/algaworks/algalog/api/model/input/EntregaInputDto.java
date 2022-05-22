package com.algaworks.algalog.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

//Class de Dto para entrada de dados (POST)
@Getter
@Setter
public class EntregaInputDto {

    @Valid
    @NotNull
    private ClienteIdInput clienteIdInput;

    @Valid
    @NotNull
    private DestinatarioInput destinatarioInput;

    private BigDecimal taxa;

}
