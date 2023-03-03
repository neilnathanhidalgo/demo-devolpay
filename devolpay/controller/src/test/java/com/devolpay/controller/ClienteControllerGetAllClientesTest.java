package com.devolpay.controller;

import com.devolpay.entity.Cliente;
import com.devolpay.inter.ClienteService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerGetAllClientesTest {
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
    public void testGetAllClientes() throws Exception {
        // Crear nuevos clientes
        Cliente cliente1 = new Cliente();
        cliente1.setId("123");
        cliente1.setName("Nat");
        cliente1.setLastname("Hidalgo");
        cliente1.setDni("12345678");

        Cliente cliente2 = new Cliente();
        cliente2.setId("456");
        cliente2.setName("Jin");
        cliente2.setLastname("Hwang");
        cliente2.setDni("87654321");

        // Guardar los clientes en la base de datos
        clienteService.saveCliente(cliente1);
        clienteService.saveCliente(cliente2);

        // Realizar la petición GET al endpoint "/cliente" para obtener todos los clientes
        MvcResult result = mockMvc.perform(get("/cliente"))
                .andExpect(status().isOk())
                .andReturn();

        // Convertir la respuesta en formato JSON a lista de objetos Cliente
        String json = result.getResponse().getContentAsString();
        List<Cliente> clientesObtenidos = objectMapper.readValue(json, new TypeReference<List<Cliente>>() {});

        // Comprobar que se han obtenido todos los clientes guardados
        assertNotNull(clientesObtenidos);
        assertEquals(2, clientesObtenidos.size());
    }


}
