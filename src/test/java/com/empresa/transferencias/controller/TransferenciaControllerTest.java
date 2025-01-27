package com.empresa.transferencias.controller;

import com.empresa.transferencias.model.Transferencia;
import com.empresa.transferencias.service.TransferenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferenciaControllerTest {

    private TransferenciaController controller;
    private TransferenciaService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(TransferenciaService.class);
        controller = new TransferenciaController(service);
    }

    @Test
    void testAgendarTransferencia() {
        Transferencia transferencia = new Transferencia();
        transferencia.setContaOrigem("123456");
        transferencia.setContaDestino("654321");
        transferencia.setValorTransferencia(new BigDecimal("1500.00"));
        transferencia.setDataTransferencia(LocalDate.now().plusDays(10));

        Transferencia transferenciaAgendada = new Transferencia();
        transferenciaAgendada.setId(UUID.randomUUID());
        transferenciaAgendada.setContaOrigem("123456");
        transferenciaAgendada.setContaDestino("654321");
        transferenciaAgendada.setValorTransferencia(new BigDecimal("1500.00"));
        transferenciaAgendada.setDataTransferencia(LocalDate.now().plusDays(10));
        transferenciaAgendada.setTaxa(new BigDecimal("12.00"));

        when(service.agendarTransferencia(any(Transferencia.class))).thenReturn(transferenciaAgendada);

        ResponseEntity<?> response = controller.agendarTransferencia(transferencia);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(service, times(1)).agendarTransferencia(any(Transferencia.class));
    }

    @Test
    void testListarTransferencias() {
        Transferencia t1 = new Transferencia();
        t1.setId(UUID.randomUUID());
        t1.setContaOrigem("123456");
        t1.setContaDestino("654321");

        Transferencia t2 = new Transferencia();
        t2.setId(UUID.randomUUID());
        t2.setContaOrigem("987654");
        t2.setContaDestino("456789");

        when(service.listarTransferencias()).thenReturn(List.of(t1, t2));

        ResponseEntity<?> response = controller.listarTransferencias();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(service, times(1)).listarTransferencias();
    }

    @Test
    void testBuscarPorData() {
        LocalDate data = LocalDate.now().plusDays(10);
        Transferencia transferencia = new Transferencia();
        transferencia.setId(UUID.randomUUID());
        transferencia.setDataTransferencia(data);

        when(service.buscarPorDataTransferencia(data)).thenReturn(List.of(transferencia));

        ResponseEntity<?> response = controller.buscarPorData(data.toString());

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(service, times(1)).buscarPorDataTransferencia(data);
    }
}
