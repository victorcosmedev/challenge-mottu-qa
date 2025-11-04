package com.example.challenge_mottu.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdministradorTest {

    private Administrador admin;

    @BeforeAll
    public static void initGeral() {
        System.out.println("Log: Iniciando testes de Administrador.");
    }

    @BeforeEach
    public void init() {
        admin = new Administrador();
    }


    @Test
    public void deveLancarExcecaoParaCpfInvalido() {
        String cpfInvalido = "111.111.111-11";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            admin.setCpfUser(cpfInvalido);
        });
    }
}
