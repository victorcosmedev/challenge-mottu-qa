package com.example.challenge_mottu.service;


import com.example.challenge_mottu.exceptions.VagaNotFoundException;
import com.example.challenge_mottu.model.Moto;
import com.example.challenge_mottu.model.Motoqueiro;
import com.example.challenge_mottu.model.StatusMoto;
import com.example.challenge_mottu.model.Vaga;
import com.example.challenge_mottu.records_DTOs.MotoRecord;
import com.example.challenge_mottu.repository.MotoRepository;
import com.example.challenge_mottu.repository.MotoqueiroRepository;
import com.example.challenge_mottu.repository.VagaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class MotoServiceTest {
    @Mock
    private MotoRepository motoRepository;
    @Mock
    private MotoqueiroRepository motoqueiroRepository;
    @Mock
    private VagaRepository vagaRepository;

    @InjectMocks
    private MotoService motoService;

    @BeforeAll
    public static void initGeral() {
        System.out.println("Log: Iniciando testes de MotoService - MotoServiceTest.");
    }

    @Test
    void deveCadastrarMotoComSucesso(){
        // Arrange
        MotoRecord moto = new MotoRecord(
                "Honda CB 650F",
                2022,
                "9CS23SAD24214",
                StatusMoto.VERDE,
                "71304662861",
                100,
                "Patio Principal",
                "Secao A1");

        Motoqueiro motoqueiroMock = new Motoqueiro();
        motoqueiroMock.setCpfUser("71304662861");

        Vaga vagaMock = new Vaga();
        vagaMock.setNumeroVaga(100);

        Mockito.when(motoqueiroRepository.findByCpfUser(Mockito.anyString())).thenReturn(motoqueiroMock);
        Mockito.when(
                vagaRepository.findByPatioSecaoENumero("Patio Principal", "Secao A1", 100))
                .thenReturn(Optional.of(vagaMock));
        Mockito.when(motoRepository.save(any(Moto.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        Moto motoSalva = motoService.cadastrar(moto);

        // Assert
        Assertions.assertNotNull(motoSalva);
        Assertions.assertEquals("9CS23SAD24214", motoSalva.getChassi());
        Mockito.verify(motoRepository, Mockito.times(1)).save(any(Moto.class));
    }

    @Test
    void deveLancarExcecaoQuandoVagaNaoExiste(){
        // Arrange
        MotoRecord record = new MotoRecord(
                "Yamaha YBR Factor 125",
                2023,
                "8CD123PHOA124091A",
                StatusMoto.VERMELHO,
                "38602854838",
                99,
                "Patio X",
                "Secao Y");

        Mockito.when(motoqueiroRepository.findByCpfUser(Mockito.anyString())).thenReturn(new Motoqueiro());
        Mockito.when(vagaRepository.findByPatioSecaoENumero(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(Optional.empty());

        // Act e Assert
        Assertions.assertThrows(VagaNotFoundException.class, () -> motoService.cadastrar(record));
        Mockito.verify(motoRepository, Mockito.never()).save(any(Moto.class));
    }
}
