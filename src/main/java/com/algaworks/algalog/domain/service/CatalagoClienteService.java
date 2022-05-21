package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatalagoClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscar(Long clienteId){
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }
    @Transactional
    public Cliente salvar(Cliente cliente){
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()//Verificando se o cliente já existe. atribuindo os dados do cliente recebido para o objeto..
                        //clienteExiste, que ciramos na lambda
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
        if (emailEmUso){
            throw new NegocioException("Já existe um cliente cadastrado com esse e-mail.");
        }

        return clienteRepository.save(cliente);
    }

    public void excluir(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }
}
