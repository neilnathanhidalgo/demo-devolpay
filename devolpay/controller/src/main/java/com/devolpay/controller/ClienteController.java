package com.devolpay.controller;

import ch.qos.logback.core.net.server.Client;
import com.devolpay.controller.exceptions.ResourceNotFoundException;
import com.devolpay.entity.Cliente;
import com.devolpay.inter.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable String id) {
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
        clienteService.saveCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //curl -X POST -H "Content-Type: application/json" -d '{"dni":"12345678","name":"Nat","lastname":"Hidalgo"}' http://localhost:8080/clientes


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable String id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllClientes() {
        clienteService.deleteAllClientes();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable(value = "id") String clienteId,
                                                 @RequestBody Cliente clienteDetails) throws ResourceNotFoundException {
        Cliente cliente = clienteService.getClienteById(clienteId);

        cliente.setDni(clienteDetails.getDni());
        cliente.setName(clienteDetails.getName());
        cliente.setLastname(clienteDetails.getLastname());
        cliente.setTelef(clienteDetails.getTelef());
        cliente.setDireccion(clienteDetails.getDireccion());

        clienteService.updateCliente(cliente);

        return ResponseEntity.ok(cliente);
    }

}
