package com.devolpay.entity;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Log4j2
@Data
@MappedSuperclass
public abstract class GlobalEntityPkString implements Serializable {
}
