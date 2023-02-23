package com.devolpay.entity;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.io.Serializable;

@Log4j2
@Data
@MappedSuperclass
public abstract class GlobalEntityPkNumeric implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "version")
    private Long version;
    @Column(name = "is_persistente")
    private Boolean isPersistente;
    @Transient
    private String operacion;
}
