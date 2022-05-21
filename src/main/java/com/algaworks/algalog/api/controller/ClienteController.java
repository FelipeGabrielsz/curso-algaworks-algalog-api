package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalagoClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private CatalagoClienteService catalagoClienteService;
    private ClienteRepository clienteRepository;

    @GetMapping()
    public List<Cliente> listar() {
       return clienteRepository.findAll();
    }

    @GetMapping("{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
        //Optional é como se fosse um container, pode ou nao ter um conteudo dentro
         Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        //.map irá mapear e verificar se o cliente está com conteudo e retornar um ResponseEntity.ok
        //Se não, o mesmo irá retornar um ResponseEntity.notFound.build
        //.map, mapereia o objeto e executa o que estiver em paramentro, ex (ResponseEntity::ok)
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
        return catalagoClienteService.salvar(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id,
                                             @RequestBody Cliente cliente){
        if(!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        //Setando o ID antes de atualizar, se nao fosse setado o mesmo iria criar um novo cliente
        cliente.setId(id);
        catalagoClienteService.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        if(!clienteRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        catalagoClienteService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
