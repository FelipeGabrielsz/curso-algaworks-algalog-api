package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.mapper.OcorrenciaMapper;
import com.algaworks.algalog.api.model.OcorrenciaModelDto;
import com.algaworks.algalog.api.model.input.OcorrenciaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.service.BuscaEntregaService;
import com.algaworks.algalog.domain.service.RegistroOcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
public class OcorrenciaController {

    private BuscaEntregaService buscaEntregaService;
    private RegistroOcorrenciaService registroOcorrenciaService;
    private OcorrenciaMapper ocorrenciaMapper;

    @GetMapping("/entregas/{entregaId}/ocorrencias")
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModelDto registrar(@PathVariable Long entregaId,
                                        @Valid @RequestBody OcorrenciaInput ocorrenciaInput){
        Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId,
                ocorrenciaInput.getDescricao());

        return ocorrenciaMapper.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    public List<OcorrenciaModelDto> listar(@PathVariable Long entregaId){
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return ocorrenciaMapper.toCollectionModel(entrega.getOcorrencia());
    }
}
