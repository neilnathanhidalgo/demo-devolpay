package com.devolpay.inter;

import ch.qos.logback.core.net.server.Client;
import com.devolpay.entity.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> getAllClientes();
    Cliente getClienteById(String id);
    Cliente saveCliente(Cliente cliente);
    void deleteCliente(String id);
    void updateCliente(Cliente cliente);

    void deleteAllClientes();
}



