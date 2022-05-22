package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.model.OcorrenciaModelDto;
import com.algaworks.algalog.domain.model.Ocorrencia;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OcorrenciaMapper {

    private ModelMapper modelMapper;

    public OcorrenciaModelDto toModel(Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaModelDto.class);
    }

    public List<OcorrenciaModelDto> toCollectionModel(List<Ocorrencia> ocorrencias){
        return ocorrencias.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
