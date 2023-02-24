package com.devolpay.controller;

import com.devolpay.entity.Cliente;
import com.devolpay.inter.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
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
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(cliente);
    }

    //curl -X POST -H "Content-Type: application/json" -d '{"dni":"12345678","name":"Nat","lastname":"Hidalgo"}' http://localhost:8080/clientes

    /*@PutMapping("/{id}")
    public ResponseEntity<Void> updateCliente(@PathVariable String id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        clienteService.updateCliente(cliente);
        return ResponseEntity.ok().build();
    }

     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable String id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.ok().build();
    }
}
