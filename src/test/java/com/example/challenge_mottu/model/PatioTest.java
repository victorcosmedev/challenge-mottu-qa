package com.example.challenge_mottu.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class PatioTest {

    private Patio patio;

    @BeforeAll
    public static void initGeral() {
        System.out.println("Log: Iniciando testes de Patio.");
    }

    @BeforeEach
    public void init() {
        patio = new Patio(100.0, 50.0, new ArrayList<>(), "Patio Principal");
    }

    @Test
    public void deveAtribuirIdentificacaoELarguraCorretamente() {
        Assertions.assertEquals("Patio Principal", patio.getIdentificacao());
        Assertions.assertEquals(100.0, patio.getLargura());
    }
}