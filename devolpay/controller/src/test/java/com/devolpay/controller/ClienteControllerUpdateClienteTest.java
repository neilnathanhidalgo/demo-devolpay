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
public class ClienteControllerUpdateClienteTest {
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
    public void testUpdateCliente() throws Exception {
        // Crea un nuevo cliente
        Cliente cliente = new Cliente();
        cliente.setDni("12345678A");
        cliente.setName("Nat");
        cliente.setLastname("Hidalgo");
        cliente.setTelef("123456789");
        cliente.setDireccion("Calle Falsa 123");

        // Guarda el cliente en la base de datos
        Cliente savedCliente = clienteService.saveCliente(cliente);

        // Actualiza los datos del cliente
        savedCliente.setName("Jin");
        savedCliente.setLastname("Hwang");

        // Envía la solicitud de actualización al controlador
        mockMvc.perform(put("/cliente/" + savedCliente.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(savedCliente)))
                .andExpect(status().isOk());

        // Obtiene el cliente actualizado de la base de datos
        Cliente updatedCliente = clienteService.getClienteById(savedCliente.getId());

        // Comprueba que los datos del cliente se hayan actualizado correctamente
        assertEquals("Jin", updatedCliente.getName());
        assertEquals("Hwang", updatedCliente.getLastname());
    }

}
