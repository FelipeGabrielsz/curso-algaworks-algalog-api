package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.mapper.EntregaMapper;
import com.algaworks.algalog.api.model.DestinatarioModelDto;
import com.algaworks.algalog.api.model.EntregaModelDto;
import com.algaworks.algalog.api.model.input.EntregaInputDto;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRespository;
import com.algaworks.algalog.domain.service.FinalizacaoEntregaService;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/entregas")
public class EntregaController {


    private EntregaRespository entregaRespository;
    private SolicitacaoEntregaService solicitacaoEntregaService;
    private FinalizacaoEntregaService finalizacaoEntregaService;
    private ModelMapper modelMapper;
    private EntregaMapper entregaMapper;

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId){
        finalizacaoEntregaService.finalizar(entregaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModelDto solicitar(@Valid @RequestBody EntregaInputDto entregaInputDto) {
        Entrega novaEntrega = entregaMapper.toEntityEntrega(entregaInputDto);

        return entregaMapper.toModelDto(novaEntrega);
    }

    @GetMapping
    public List<EntregaModelDto> listar(){
        return entregaMapper.toCollectionEntregaDto(entregaRespository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaModelDto> buscar(@PathVariable Long entregaId){
        return entregaRespository.findById(entregaId)
                .map(entrega -> { //Lambda, a var entrega é do tipo Entrega e estamos mapeando para o tipo EntregaDto
                    //Convertendo a var entrega do tipo (Entrega), que foi mapeada, para EntregaModelDto
                    EntregaModelDto entregaModelDto = entregaMapper.toModelDto(entrega);

                    return ResponseEntity.ok(entregaModelDto);
                }) //Se achar, ele mapeia e retorna um ok
                .orElse(ResponseEntity.notFound().build()); //Se não achar ele retorna um notFound.
    }
}
