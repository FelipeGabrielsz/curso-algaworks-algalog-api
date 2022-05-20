package com.algaworks.algalog.api;

import com.algaworks.algalog.api.controller.ClienteController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveraRetornarSucessoAoListarTodosClientes() throws Exception {

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk());
    }
}