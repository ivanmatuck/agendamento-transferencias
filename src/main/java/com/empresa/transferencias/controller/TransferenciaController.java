package com.empresa.transferencias.controller;

import com.empresa.transferencias.model.Transferencia;
import com.empresa.transferencias.service.TransferenciaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para gerenciar transferências financeiras.
 * Expõe endpoints para agendar e listar transferências.
 */
@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    private static final Logger logger = LoggerFactory.getLogger(TransferenciaController.class);
    private final TransferenciaService service;

    /**
     * Construtor para injeção de dependência.
     *
     * @param service Instância do TransferenciaService.
     */
    @Autowired
    public TransferenciaController(TransferenciaService service) {
        this.service = service;
    }

    /**
     * Endpoint para agendar uma nova transferência.
     *
     * @param transferencia Dados válidos da transferência a ser agendada.
     * @return ResponseEntity contendo uma mensagem de sucesso e os dados da
     *         transferência agendada, ou erros detalhados em caso de falha.
     */
    @PostMapping
    public ResponseEntity<?> agendarTransferencia(@Valid @RequestBody Transferencia transferencia) {
        try {
            logger.info("Requisição para agendar transferência recebida: {}", transferencia);
            Transferencia transferenciaAgendada = service.agendarTransferencia(transferencia);
            logger.info("Transferência agendada com sucesso: ID = {}", transferenciaAgendada.getId());
            return ResponseEntity.ok(Map.of(
                    "mensagem", "Transferência agendada com sucesso.",
                    "dados", transferenciaAgendada
            ));
        } catch (IllegalArgumentException e) {
            logger.error("Erro de validação ao agendar transferência: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(Map.of(
                    "erro", "Erro de validação: " + e.getMessage()
            ));
        } catch (RuntimeException e) {
            logger.error("Erro interno ao agendar transferência: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body(Map.of(
                    "erro", "Erro inesperado: " + e.getMessage()
            ));
        } catch (Exception e) {
            logger.error("Erro não esperado ao agendar transferência: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body(Map.of(
                    "erro", "Erro crítico: " + e.getMessage()
            ));
        }
    }

    /**
     * Endpoint para listar todas as transferências cadastradas no sistema.
     *
     * @return ResponseEntity contendo a quantidade e os dados de todas
     *         as transferências registradas, ou uma mensagem de erro em
     *         caso de falha no processamento.
     */
    @GetMapping
    public ResponseEntity<?> listarTransferencias() {
        try {
            logger.info("Requisição para listar transferências recebida.");
            List<Transferencia> transferencias = service.listarTransferencias();
            logger.info("Transferências listadas com sucesso. Quantidade: {}", transferencias.size());
            return ResponseEntity.ok(Map.of(
                    "quantidade", transferencias.size(),
                    "transferencias", transferencias
            ));
        } catch (RuntimeException e) {
            logger.error("Erro interno ao listar transferências: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body(Map.of(
                    "erro", "Erro interno ao listar transferências: " + e.getMessage()
            ));
        } catch (Exception e) {
            logger.error("Erro inesperado ao listar transferências: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body(Map.of(
                    "erro", "Erro crítico ao listar transferências: " + e.getMessage()
            ));
        }
    }

    /**
     * Endpoint para buscar transferências agendadas em uma data específica.
     *
     * @param dataTransferencia Data de transferência no formato ISO-8601 (yyyy-MM-dd).
     * @return ResponseEntity contendo a lista de transferências agendadas
     *         para a data especificada, ou erros em caso de entrada inválida
     *         ou falha interna.
     */
    @GetMapping("/data")
    public ResponseEntity<?> buscarPorData(@RequestParam("data") String dataTransferencia) {
        try {
            logger.info("Requisição para buscar transferências pela data: {}", dataTransferencia);
            LocalDate data = LocalDate.parse(dataTransferencia);
            List<Transferencia> transferencias = service.buscarPorDataTransferencia(data);
            logger.info("Transferências encontradas para a data {}: {}", dataTransferencia, transferencias.size());
            return ResponseEntity.ok(Map.of(
                    "quantidade", transferencias.size(),
                    "transferencias", transferencias
            ));
        } catch (IllegalArgumentException e) {
            logger.error("Erro de formatação de data: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(Map.of(
                    "erro", "Formato de data inválido: " + e.getMessage()
            ));
        } catch (RuntimeException e) {
            logger.error("Erro interno ao buscar transferências: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body(Map.of(
                    "erro", "Erro interno ao buscar transferências: " + e.getMessage()
            ));
        } catch (Exception e) {
            logger.error("Erro inesperado ao buscar transferências: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body(Map.of(
                    "erro", "Erro crítico ao buscar transferências: " + e.getMessage()
            ));
        }
    }
}
