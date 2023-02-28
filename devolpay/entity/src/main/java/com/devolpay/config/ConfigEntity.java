package com.devolpay.config;

import com.devolpay.entity.Cliente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.devolpay.entity"})
public class ConfigEntity {

}
