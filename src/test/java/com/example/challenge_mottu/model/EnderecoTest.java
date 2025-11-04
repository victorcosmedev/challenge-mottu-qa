package com.example.challenge_mottu.model;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnderecoTest {

    private Endereco endereco;

    @BeforeAll
    public static void initGeral() {
        System.out.println("Log: Iniciando testes de Endereco.");
    }

    @BeforeEach
    public void init() {
        endereco = new Endereco(
                "01001-000",
                "Praça da Sé",
                "lado ímpar",
                "Sé",
                "São Paulo",
                "SP"
        );
    }

    @Test
    public void deveAtribuirLocalidadeCorretamente() {
        String localidadeEsperada = "São Paulo";
        Assertions.assertEquals(localidadeEsperada, endereco.getLocalidade());
    }
}
