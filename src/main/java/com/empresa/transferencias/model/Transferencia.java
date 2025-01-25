package com.empresa.transferencias.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Representa uma transferência financeira.
 * Inclui informações como:
 * - Identificador único (UUID) para cada transferência
 * - Conta de origem e destino (validadas)
 * - Valor e taxa da transferência
 * - Data da transferência e data de agendamento
 *
 * Todos os atributos possuem validações para garantir consistência
 * e o cumprimento das regras de negócio definidas.
 */
@Entity
@Data
@Table(name = "transferencias")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Size(min = 6, max = 6, message = "Conta de origem deve ter exatamente 6 caracteres.")
    @Column(name = "conta_origem", nullable = false, length = 6)
    private String contaOrigem;

    @NotNull
    @Size(min = 6, max = 6, message = "Conta de destino deve ter exatamente 6 caracteres.")
    @Column(name = "conta_destino", nullable = false, length = 6)
    private String contaDestino;

    @NotNull
    @DecimalMin(value = "0.01", message = "O valor da transferência deve ser maior que 0.")
    @Column(name = "valor_transferencia", nullable = false)
    private BigDecimal valorTransferencia;

    @Column(name = "taxa", nullable = false)
    private BigDecimal taxa;

    @NotNull
    @Future(message = "A data da transferência deve ser no futuro.")
    @Column(name = "data_transferencia", nullable = false)
    private LocalDate dataTransferencia;

    @Column(name = "data_agendamento", nullable = false)
    private LocalDate dataAgendamento;
}
