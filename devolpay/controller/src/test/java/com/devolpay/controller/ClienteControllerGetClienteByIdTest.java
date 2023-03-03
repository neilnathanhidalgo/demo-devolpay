package com.devolpay.controller;

import com.devolpay.entity.Cliente;
import com.devolpay.inter.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerGetClienteByIdTest {
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
    public void testGetClienteById() throws Exception {
        // Crear un nuevo cliente
        Cliente cliente = new Cliente();
        cliente.setId("123");
        cliente.setName("Nat");
        cliente.setLastname("Hidalgo");
        cliente.setDni("12345678");

        // Guardar el cliente en la base de datos
        clienteService.saveCliente(cliente);

        // Realizar la petición GET al endpoint "/clientes/{id}" para obtener el cliente por su id
        MvcResult result = mockMvc.perform(get("/cliente/123"))
                .andExpect(status().isOk())
                .andReturn();

        // Convertir la respuesta en formato JSON a objeto Cliente
        String json = result.getResponse().getContentAsString();
        Cliente clienteObtenido = objectMapper.readValue(json, Cliente.class);

        // Comprobar que el cliente obtenido es el mismo que el guardado
        assertNotNull(clienteObtenido);
        assertEquals("Nat", clienteObtenido.getName());
        assertEquals("Hidalgo", clienteObtenido.getLastname());
        assertEquals("12345678", clienteObtenido.getDni());
    }

}
