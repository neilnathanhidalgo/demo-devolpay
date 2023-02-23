package com.devolpay.dao;

import com.devolpay.config.ConfigMongo;
import com.devolpay.dao.impl.ClienteDaoImpl;
import com.devolpay.dao.inter.ClienteDaoInter;
import com.devolpay.entity.Cliente;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(SpringRunner.class)
@DataMongoTest
@ContextConfiguration(classes = {ConfigMongo.class})
public class TestClienteDao {
    @Autowired
    private ClienteDaoInter clienteDaoInter;
    @Autowired
    private ClienteDaoImpl clienteRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setUp() {
        // No es necesario hacer nada aquí, ya que los objetos inyectados a través de @Autowired están disponibles
    }

    @Test
    public void testGetAllClientes() {
        mongoTemplate.save(new Cliente("1","72087008","Nat"));
        mongoTemplate.save(new Cliente("2","12345678","Jin"));

        List<Cliente> clients = clienteDaoInter.getALlClientes();

        // Comprobar que se devolvieron dos usuarios
        assertEquals(2, clients.size());

        // Comprobar que los usuarios obtenidos son los mismos que se insertaron
        assertEquals("Nat", clients.get(0).getName());
        assertEquals("Jin", clients.get(1).getName());

        clienteDaoInter.deleteCliente("1");
        clienteDaoInter.deleteCliente("2");
    }
    @Test
    public void testGetUserById() {
        // Insertar un usuario con el ID "1" en la base de datos de prueba
        mongoTemplate.save(new Cliente("1","72087008","Nat"));

        // Obtener el usuario con ID "1" utilizando el método getUserById()
        Cliente cliente = clienteDaoInter.getClienteById("1");

        // Comprobar que el usuario obtenido tiene el nombre correcto
        assertEquals("Nat", cliente.getName());

        clienteDaoInter.deleteCliente("1");
    }
    @Test
    public void testSaveUser() {
        // Crear un nuevo usuario y guardarlo en la base de datos utilizando el método saveUser()
        Cliente cliente = new Cliente("1","72087008","Nat");
        clienteDaoInter.saveCliente(cliente);

        // Obtener el usuario recién guardado utilizando el método getUserById()
        Cliente saveCliente = clienteDaoInter.getClienteById("1");

        // Comprobar que el usuario obtenido es el mismo que se guardó
        assertEquals(cliente, saveCliente);

        clienteDaoInter.deleteCliente("1");
    }
    @Test
    public void testDeleteUser() {
        // Insertar un usuario con el ID "1" en la base de datos de prueba
        mongoTemplate.save(new Cliente("1","72087008","Nat"));

        // Eliminar el usuario con ID "1" utilizando el método deleteUser()
        clienteDaoInter.deleteCliente("1");

        // Comprobar que no hay usuarios en la base de datos
        assertEquals(0, mongoTemplate.findAll(Cliente.class).size());
    }

}