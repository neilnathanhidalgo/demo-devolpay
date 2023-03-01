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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClienteService clienteService;

    @AfterEach
    void tearDown() {
        clienteService.deleteAllClientes();
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

    @Test
    public void deleteCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId("123");
        cliente.setName("Hob");

        clienteService.saveCliente(cliente);

        mockMvc.perform(delete("/cliente/{id}", cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Cliente comprobar = clienteService.getClienteById("123");
        assertNull(comprobar);
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


