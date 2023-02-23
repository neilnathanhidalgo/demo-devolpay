package com.devolpay.entity;

import com.devolpay.config.ConfigEntity;
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@Log4j2
public class TestCliente {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private final PrintStream originalOut = System.out;

    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    @Test
    void callContextSpring(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigEntity.class);
        Cliente bean = context.getBean(Cliente.class);
        bean.setId(String.valueOf(Long.MIN_VALUE));
        Cliente bean1 = context.getBean(Cliente.class);
        bean.setId(String.valueOf(8L));
        System.out.println(bean.getId());
        System.out.println(bean1.getId());
        bean1.setId(String.valueOf(90L));
        System.out.println(bean1.getId());
        context.close();
    }
}
