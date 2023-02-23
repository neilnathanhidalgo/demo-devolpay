package com.devolpay.controller;

import com.devolpay.config.ConfigMongo;
import com.devolpay.dao.impl.ClienteDaoImpl;
import com.devolpay.entity.Cliente;
import com.devolpay.inter.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    @Qualifier(ConfigMongo.MONGODB)
    private MongoTemplate mongoTemplate;
    @Autowired
    private ClienteDaoImpl clienteRepository;

    /*@GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable String id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }
     */

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        clienteRepository.insert(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }
}
