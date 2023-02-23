package com.devolpay.dao.inter;

import com.devolpay.entity.Cliente;

import java.util.List;

public interface ClienteDaoInter {

    List<Cliente> getALlClientes();
    Cliente getClienteById(String id);
    Cliente saveCliente(Cliente cliente);
    void deleteCliente(String id);

    void insert(Cliente cliente);

    void update(Cliente cliente);



}
