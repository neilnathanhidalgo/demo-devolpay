package com.devolpay.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Component
@Scope("prototype")
@Log4j2
@EqualsAndHashCode(callSuper = false)
@Data
@Document(collection = "cliente")
public class Cliente implements Serializable {
    @Id
    private String id;
    @NotEmpty
    private String dni;
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastname;
    private String telef;
    private String direccion;


}