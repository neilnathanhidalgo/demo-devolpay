package com.devolpay.dao;

import com.devolpay.config.ConfigMongo;
import com.devolpay.dao.impl.ClienteDaoImpl;
import com.devolpay.dao.inter.ClienteDaoInter;
import com.devolpay.entity.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Slf4j
@RunWith(SpringRunner.class)
@DataMongoTest
@ContextConfiguration(classes = {ConfigMongo.class})
public class TestClienteDao {
    @Autowired
    private ClienteDaoInter clienteDaoInter;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setUp() {
        // No es necesario hacer nada aquí, ya que los objetos inyectados a través de @Autowired están disponibles
    }
    @AfterEach
    public void AfterEach(){
        clienteDaoInter.deleteAllClientes();
    }
    @Test
    public void testGetAllClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setId("1");
        cliente1.setDni("72087008");
        cliente1.setName("Nat");

        Cliente cliente2 = new Cliente();
        cliente2.setId("2");
        cliente2.setDni("12345678");
        cliente2.setName("Jin");

        clienteDaoInter.saveCliente(cliente1);
        clienteDaoInter.saveCliente(cliente2);

        List<Cliente> clients = clienteDaoInter.getALlClientes();

        // Comprobar que se devolvieron dos usuarios
        assertEquals(2, clients.size());

        // Comprobar que los usuarios obtenidos son los mismos que se insertaron
        assertEquals("Nat", clients.get(0).getName());
        assertEquals("Jin", clients.get(1).getName());

    }
    @Test
    public void testGetClienteById() {
        // Insertar un usuario con el ID "1" en la base de datos de prueba
        Cliente cliente1 = new Cliente();
        cliente1.setId("1");
        cliente1.setDni("72087008");
        cliente1.setName("Nat");

        clienteDaoInter.saveCliente(cliente1);

        // Obtener el usuario con ID "1" utilizando el método getUserById()
        Cliente cliente = clienteDaoInter.getClienteById("1");

        // Comprobar que el usuario obtenido tiene el nombre correcto
        assertEquals("Nat", cliente.getName());

    }
    @Test
    public void testSaveCliente() {
        // Crear un nuevo usuario y guardarlo en la base de datos utilizando el método saveUser()
        Cliente cliente = new Cliente();
        cliente.setId("1");
        cliente.setDni("72087008");
        cliente.setName("Nat");
        clienteDaoInter.saveCliente(cliente);

        // Obtener el usuario recién guardado utilizando el método getUserById()
        Cliente saveCliente = clienteDaoInter.getClienteById("1");

        // Comprobar que el usuario obtenido es el mismo que se guardó
        assertEquals(cliente, saveCliente);

    }
    @Test
    public void testDeleteCliente() {
        // Insertar un usuario con el ID "1" en la base de datos de prueba
        Cliente cliente1 = new Cliente();
        cliente1.setId("1");
        cliente1.setDni("72087008");
        cliente1.setName("Nat");

        clienteDaoInter.saveCliente(cliente1);

        // Eliminar el usuario con ID "1" utilizando el método deleteUser()
        clienteDaoInter.deleteCliente("1");

        // Comprobar que no hay usuarios en la base de datos
        assertEquals(0, mongoTemplate.findAll(Cliente.class).size());
    }

    @Test
    public void testUpdateCliente() {
        Cliente cliente = new Cliente();
        cliente.setId("1");
        cliente.setDni("72087008");
        cliente.setName("Nat");

        clienteDaoInter.saveCliente(cliente);
        log.info(cliente.getName());

        cliente.setName("Jin");

        clienteDaoInter.updateCliente(cliente);

        log.info(cliente.getName());

    }

}