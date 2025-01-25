package com.empresa.transferencias.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe para tratamento global de exceções.
 * Captura erros de validação e exceções inesperadas, retornando mensagens amigáveis ao cliente.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Captura e trata erros de validação de dados enviados para os endpoints.
     *
     * @param ex Exceção de validação gerada.
     * @return ResponseEntity contendo detalhes dos campos inválidos.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        logger.error("Erro de validação: {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Captura e trata exceções genéricas não previstas no sistema.
     *
     * @param ex Exceção capturada.
     * @return ResponseEntity contendo uma mensagem genérica de erro interno.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralExceptions(Exception ex) {
        logger.error("Erro não esperado: {}", ex.getMessage(), ex);
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Erro interno. Consulte os logs para mais detalhes.");
        return ResponseEntity.status(500).body(errorResponse);
    }
}
