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
public class ClienteControllerDeleteAllClientesTest {
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
    public void deleteAllClientes() throws Exception {
        Cliente cliente1 = new Cliente();
        cliente1.setId("123");
        cliente1.setName("Hob");

        Cliente cliente2 = new Cliente();
        cliente2.setId("456");
        cliente2.setName("Rob");

        clienteService.saveCliente(cliente1);
        clienteService.saveCliente(cliente2);

        mockMvc.perform(delete("/cliente")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(0, clienteService.getAllClientes().size());
    }

}
