package com.devolpay.controller;

import com.devolpay.entity.Cliente;
import com.devolpay.inter.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerSaveClienteTest {
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
    public void saveClientes() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId("123");
        cliente.setName("Hob");

        String json = objectMapper.writeValueAsString(cliente);

        MvcResult result = mockMvc.perform(post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        // Verificar si el estado de la respuesta es el esperado
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());

        // Verificar si el objeto guardado en la base de datos es el mismo que el enviado en la solicitud
        assertEquals(cliente, clienteService.getClienteById(cliente.getId()));

        // Verificar si los atributos del objeto guardado en la base de datos son correctos
        assertEquals(cliente.getName(), clienteService.getClienteById(cliente.getId()).getName());



    }


}
