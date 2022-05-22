package com.algaworks.algalog.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OcorrenciaModelDto {

    private Long id;
    private String descricao;
    private OffsetDateTime dataRegistro;
}
