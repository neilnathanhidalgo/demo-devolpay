package com.devolpay.service;

import com.devolpay.config.ConfigService;
import com.devolpay.entity.Cliente;
import com.devolpay.inter.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {ConfigService.class})
class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;
    Cliente c1 = new Cliente();
    Cliente c2 = new Cliente();

    @BeforeEach
    void setUp() {
        c1.setId("1");
        c1.setName("Nat");
        c1.setLastname("Hidalgo");
        c1.setTelef("12345");
        c1.setDni("12345678");
        c2.setId("2");
        c2.setName("Jin");
        c2.setLastname("Hwang");
        c2.setTelef("67890");
        c2.setDni("87654321");

    }

    @AfterEach
    void tearDown() {
        clienteService.deleteAllClientes();
    }

    @Test
    void testSaveCliente() {
        Cliente savedCliente = clienteService.saveCliente(c1);
        assertNotNull(savedCliente.getId());
        assertEquals(c1.getName(), savedCliente.getName());
        assertEquals(c1.getLastname(), savedCliente.getLastname());
        assertEquals(c1.getTelef(), savedCliente.getTelef());
        assertEquals(c1.getDni(), savedCliente.getDni());
    }

    @Test
    void testGetClienteById() {
        clienteService.saveCliente(c1);
        Cliente foundCliente = clienteService.getClienteById(c1.getId());
        assertEquals(c1.getId(), foundCliente.getId());
        assertEquals(c1.getName(), foundCliente.getName());
        assertEquals(c1.getLastname(), foundCliente.getLastname());
        assertEquals(c1.getTelef(), foundCliente.getTelef());
        assertEquals(c1.getDni(), foundCliente.getDni());
    }

    @Test
    void testGetAllClientes() {
        clienteService.saveCliente(c1);
        clienteService.saveCliente(c2);
        List<Cliente> clientes = clienteService.getAllClientes();
        assertEquals(2, clientes.size());
        assertEquals(c1.getName(), clientes.get(0).getName());
        assertEquals(c1.getLastname(), clientes.get(0).getLastname());
        assertEquals(c1.getTelef(), clientes.get(0).getTelef());
        assertEquals(c1.getDni(), clientes.get(0).getDni());
        assertEquals(c2.getName(), clientes.get(1).getName());
        assertEquals(c2.getLastname(), clientes.get(1).getLastname());
        assertEquals(c2.getTelef(), clientes.get(1).getTelef());
        assertEquals(c2.getDni(), clientes.get(1).getDni());
    }

    @Test
    void testDeleteCliente() {
        clienteService.saveCliente(c1);

        String clienteId = c1.getId();

        clienteService.deleteCliente(c1.getId());

        Cliente clienteEliminado = clienteService.getClienteById(clienteId);

        assertNull(clienteEliminado);
    }

    @Test
    void testDeleteAllClientes() {
        clienteService.saveCliente(c1);
        clienteService.saveCliente(c2);
        clienteService.deleteAllClientes();
        List<Cliente> clientes = clienteService.getAllClientes();
        assertTrue(clientes.isEmpty());
    }

    @Test
    void testUpdateCliente() {
        clienteService.saveCliente(c1);

        String clienteId = c1.getId();

        c1.setName("Cillian");
        c1.setLastname("Murphy");

        clienteService.updateCliente(c1);

        Cliente clienteActualizado = clienteService.getClienteById(clienteId);
        assertNotNull(clienteActualizado);
        assertEquals("Cillian", clienteActualizado.getName());
        assertEquals("Murphy", clienteActualizado.getLastname());
        assertEquals("12345678", clienteActualizado.getDni());

    }
}



