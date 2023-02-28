package com.devolpay.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ClienteTest {

    @Test
    public void givenTwoEqualClients_whenCompared_returnTrue() {
        Cliente cliente1 = new Cliente();
        cliente1.setDni("72087008");
        cliente1.setName("Nat");
        Cliente cliente2 = new Cliente();
        cliente2.setDni("72087008");
        cliente2.setName("Nat");

        assertThat(cliente1).isEqualTo(cliente2);
    }

    @Test
    public void givenTwoDifferentClients_whenCompared_returnFalse() {
        Cliente cliente1 = new Cliente();
        cliente1.setDni("72087008");
        cliente1.setName("Nat");
        Cliente cliente2 = new Cliente();
        cliente2.setDni("12345678");
        cliente2.setName("Jin");

        assertThat(cliente1).isNotEqualTo(cliente2);
    }

    @Test
    public void givenAClient_whenGetName_returnCorrectName() {
        Cliente cliente = new Cliente();
        cliente.setDni("72087008");
        cliente.setName("Nat");

        assertThat(cliente.getName()).isEqualTo("Nat");
    }

    @Test
    public void givenAClient_whenSetName_thenNameIsUpdated() {
        Cliente cliente = new Cliente();
        cliente.setDni("72087008");
        cliente.setName("Nat");
        cliente.setName("Pedro");

        assertThat(cliente.getName()).isEqualTo("Pedro");
    }
}

