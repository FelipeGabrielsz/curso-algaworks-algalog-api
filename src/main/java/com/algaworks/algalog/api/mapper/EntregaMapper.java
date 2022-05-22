package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.model.EntregaModelDto;
import com.algaworks.algalog.api.model.input.EntregaInputDto;
import com.algaworks.algalog.domain.model.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaMapper {

    private ModelMapper modelMapper;

    public EntregaModelDto toModelDto(Entrega entrega){
        return modelMapper.map(entrega, EntregaModelDto.class);
    }

    public List<EntregaModelDto> toCollectionEntregaDto(List<Entrega> entregas){
        return entregas.stream()
                //Aplicando uma função aos elementos do stream e retorna um novo stream com o resultado
                .map(this::toModelDto) //Reaproveitando o metodo que converte um por um
                .collect(Collectors.toList());  //Convertendo a stream em lista.
    }

    public Entrega toEntityEntrega(EntregaInputDto entregaInputDto){
        return modelMapper.map(entregaInputDto, Entrega.class);
    }
}
