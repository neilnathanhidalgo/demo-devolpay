package com.devolpay.impl;

import com.devolpay.dao.inter.ClienteDaoInter;
import com.devolpay.entity.Cliente;
import com.devolpay.inter.ClienteService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteDaoInter clienteDaoInter;

    public ClienteServiceImpl(ClienteDaoInter clienteDaoInter) {
        this.clienteDaoInter = clienteDaoInter;
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clienteDaoInter.getALlClientes();
    }
    @Override
    public Cliente getClienteById(String id) {
        return clienteDaoInter.getClienteById(id);
    }
    @Override
    public Cliente saveCliente(Cliente cliente) {
        clienteDaoInter.saveCliente(cliente);
        return cliente;
    }
    @Override
    public void deleteCliente(String id) {
        clienteDaoInter.deleteCliente(id);
    }
}



