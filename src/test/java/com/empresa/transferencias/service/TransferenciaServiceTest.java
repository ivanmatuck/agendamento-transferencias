package com.empresa.transferencias.service;

import com.empresa.transferencias.model.Transferencia;
import com.empresa.transferencias.repository.TransferenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferenciaServiceTest {

    private TransferenciaService service;
    private TransferenciaRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(TransferenciaRepository.class);
        service = new TransferenciaService(repository);
    }

    @Test
    void testAgendarTransferencia() {
        // Simula a transferência
        Transferencia transferencia = new Transferencia();
        transferencia.setContaOrigem("123456");
        transferencia.setContaDestino("654321");
        transferencia.setValorTransferencia(new BigDecimal("1000.00"));
        transferencia.setDataTransferencia(LocalDate.now().plusDays(5));

        // Mock do comportamento do repositório
        when(repository.save(any(Transferencia.class))).thenAnswer(invocation -> {
            Transferencia t = invocation.getArgument(0);
            t.setId(UUID.randomUUID());
            return t;
        });

        Transferencia agendada = service.agendarTransferencia(transferencia);

        assertNotNull(agendada.getId());
        assertEquals(new BigDecimal("1000.00"), agendada.getValorTransferencia());
        assertNotNull(agendada.getTaxa());
        verify(repository, times(1)).save(any(Transferencia.class));
    }

    @Test
    void testListarTransferencias() {
        // Simula dados no repositório
        Transferencia t1 = new Transferencia();
        t1.setId(UUID.randomUUID());
        t1.setContaOrigem("123456");
        t1.setContaDestino("654321");
        t1.setValorTransferencia(new BigDecimal("1000.00"));

        Transferencia t2 = new Transferencia();
        t2.setId(UUID.randomUUID());
        t2.setContaOrigem("987654");
        t2.setContaDestino("456789");
        t2.setValorTransferencia(new BigDecimal("500.00"));

        when(repository.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<Transferencia> transferencias = service.listarTransferencias();

        assertEquals(2, transferencias.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testBuscarPorDataTransferencia() {
        // Simula uma data e dados no repositório
        LocalDate data = LocalDate.now().plusDays(5);
        Transferencia transferencia = new Transferencia();
        transferencia.setId(UUID.randomUUID());
        transferencia.setContaOrigem("123456");
        transferencia.setContaDestino("654321");
        transferencia.setDataTransferencia(data);

        when(repository.findByDataTransferencia(data)).thenReturn(List.of(transferencia));

        List<Transferencia> resultado = service.buscarPorDataTransferencia(data);

        assertEquals(1, resultado.size());
        assertEquals(data, resultado.get(0).getDataTransferencia());
        verify(repository, times(1)).findByDataTransferencia(data);
    }
}

