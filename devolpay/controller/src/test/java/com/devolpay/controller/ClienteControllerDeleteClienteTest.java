package com.devolpay.controller;

import com.devolpay.entity.Cliente;
import com.devolpay.inter.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerDeleteClienteTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClienteService clienteService;

    @AfterEach
    void tearDown() {
        //clienteService.deleteAllClientes();
    }

    @Test
    public void deleteCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId("123");
        cliente.setName("Hob");

        //clienteService.saveCliente(cliente);

        mockMvc.perform(delete("/cliente/{id}", cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Cliente comprobar = clienteService.getClienteById("123");
        assertNull(comprobar);
    }

}