package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        var cliente1 = new Cliente(1L,
                "João",
                "joaodascouves@algaworks.com",
                "34 99999-1111");

        var cliente2 = new Cliente(1L,
                "Maria",
                "mariadasilva@algaworks.com",
                "34 99999-1111");

        return Arrays.asList(cliente1, cliente2);
    }

}
