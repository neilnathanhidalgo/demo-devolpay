package com.devolpay.impl;

import com.devolpay.dao.inter.ClienteDaoInter;
import com.devolpay.entity.Cliente;
import com.devolpay.inter.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDaoInter clienteDaoInter;


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
        return clienteDaoInter.saveCliente(cliente);
    }
    @Override
    public void deleteCliente(String id) {
        clienteDaoInter.deleteCliente(id);
    }
    @Override
    public void updateCliente(Cliente cliente) {
        clienteDaoInter.updateCliente(cliente);
    }
    @Override
    public void deleteAllClientes(){
        clienteDaoInter.deleteAllClientes(); }
}





