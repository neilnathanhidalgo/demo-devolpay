package com.devolpay.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@ComponentScan("com.devolpay")
public class ConfigService {
    private final MongoTemplate mongoTemplate;

    public ConfigService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


}



