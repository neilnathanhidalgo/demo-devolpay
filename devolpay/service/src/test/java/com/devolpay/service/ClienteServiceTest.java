package com.devolpay.service;

import com.devolpay.config.ConfigService;
import com.devolpay.entity.Cliente;
import com.devolpay.inter.ClienteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {ConfigService.class})
@ComponentScan
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    public void testSaveCliente() {
        Cliente cliente = new Cliente();
        cliente.setId("1");
        cliente.setDni("72087008");
        cliente.setName("Nat");
        clienteService.saveCliente(cliente);
        assertNotNull(cliente.getId());
    }

    @Test
    public void testGetClienteById() {
        Cliente cliente = new Cliente();
        cliente.setId("1");
        cliente.setDni("72087008");
        cliente.setName("Nat");
        clienteService.saveCliente(cliente);
        Cliente clienteEncontrado = clienteService.getClienteById("1");
        assertEquals(cliente, clienteEncontrado);
    }

    @Test
    public void testDeleteCliente() {
        Cliente cliente = new Cliente();
        cliente.setId("1");
        cliente.setDni("72087008");
        cliente.setName("Nat");
        clienteService.saveCliente(cliente);
        clienteService.deleteCliente(cliente.getId());

    }

}


