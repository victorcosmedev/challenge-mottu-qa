package com.example.challenge_mottu.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VagaTest {

    private Vaga vaga;

    @BeforeAll
    public static void initGeral() {
        System.out.println("Log: Iniciando testes de Vaga.");
    }

    @BeforeEach
    public void init() {
        vaga = new Vaga(101, true, null);
    }

    @Test
    public void deveAtribuirNumeroVagaEDisponibilidade() {
        Assertions.assertEquals(101, vaga.getNumeroVaga());
        Assertions.assertTrue(vaga.isDisponivel());
    }
}