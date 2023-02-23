package com.devolpay.dao.impl;

import com.devolpay.config.ConfigMongo;
import com.devolpay.dao.inter.ClienteDaoInter;
import com.devolpay.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public class ClienteDaoImpl implements ClienteDaoInter {

    @Autowired
    @Qualifier(ConfigMongo.MONGODB)
    private MongoTemplate mongoTemplate;

    public ClienteDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public List<Cliente> getALlClientes(){
        return mongoTemplate.findAll(Cliente.class);
    }
    @Override
    public Cliente getClienteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Cliente.class);
    }
    @Override
    public Cliente saveCliente(Cliente cliente) {
        mongoTemplate.save(cliente);
        return cliente;
    }
    @Override
    public void deleteCliente(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Cliente.class);
    }
    @Override
    public void insert(Cliente cliente) {
        mongoTemplate.insert(cliente,"cliente");
    }

    @Override
    public void update(Cliente cliente) {
        mongoTemplate.save(cliente,"cliente");
    }
}
