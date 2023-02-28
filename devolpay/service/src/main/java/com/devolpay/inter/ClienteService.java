package com.devolpay.inter;

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



