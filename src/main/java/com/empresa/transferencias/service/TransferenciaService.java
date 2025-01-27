package com.empresa.transferencias.service;

import com.empresa.transferencias.model.Transferencia;
import com.empresa.transferencias.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Serviço responsável pela lógica de negócios para agendamento de transferências.
 */
@Service
public class TransferenciaService {

    private static final Logger logger = LoggerFactory.getLogger(TransferenciaService.class);

    private final TransferenciaRepository repository;

    /**
     * Construtor para injeção de dependência.
     *
     * @param repository Repositório de transferências.
     */
    @Autowired
    public TransferenciaService(TransferenciaRepository repository) {
        this.repository = repository;
    }

    /**
     * Agenda uma nova transferência financeira.
     * Este método valida os dados da transferência, calcula a taxa com base
     * na diferença de dias entre o agendamento e a data de transferência,
     * e salva a transferência no banco de dados.
     *
     * @param transferencia Dados da transferência, incluindo conta de origem,
     *                      conta de destino, valor e data de transferência.
     * @return Transferencia Dados da transferência agendada, incluindo a taxa.
     * @throws RuntimeException Em caso de validação ou falha interna.
     */
    public Transferencia agendarTransferencia(Transferencia transferencia) {
        try {
            validarTransferencia(transferencia);

            LocalDate dataAgendamento = LocalDate.now();
            transferencia.setDataAgendamento(dataAgendamento);

            int diasDiferenca = (int) ChronoUnit.DAYS.between(dataAgendamento, transferencia.getDataTransferencia());
            BigDecimal taxa = calcularTaxa(diasDiferenca, transferencia.getValorTransferencia());
            transferencia.setTaxa(taxa);

            Transferencia salva = repository.save(transferencia);
            logger.info("Transferência salva com sucesso: ID = {}", salva.getId());
            return salva;
        } catch (IllegalArgumentException e) {
            logger.error("Erro de validação ao agendar transferência: {}", e.getMessage(), e);
            throw new RuntimeException("Erro de validação: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Erro ao agendar a transferência: {}", e.getMessage(), e);
            throw new RuntimeException("Erro interno: " + e.getMessage(), e);
        }
    }

    /**
     * Valida os dados da transferência para garantir que estejam corretos.
     * Regras de validação:
     * - Conta de origem e destino não podem ser iguais.
     * - O valor da transferência deve ser maior que zero.
     *
     * @param transferencia Dados da transferência a serem validados.
     * @throws IllegalArgumentException Se as regras de validação forem violadas.
     */
    private void validarTransferencia(Transferencia transferencia) {
        if (transferencia.getContaOrigem().equals(transferencia.getContaDestino())) {
            throw new IllegalArgumentException("Conta de origem e destino não podem ser iguais.");
        }
        if (transferencia.getValorTransferencia().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor da transferência deve ser maior que zero.");
        }
    }

    /**
     * Retorna todas as transferências cadastradas no banco de dados.
     *
     * @return Lista contendo todas as transferências registradas.
     * @throws RuntimeException Em caso de falha ao acessar o banco de dados.
     */
    public List<Transferencia> listarTransferencias() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar transferências: " + e.getMessage(), e);
        }
    }

    /**
     * Busca transferências agendadas para uma data específica.
     *
     * @param dataTransferencia Data de transferência no formato LocalDate.
     * @return Lista de transferências cadastradas para a data informada.
     * @throws RuntimeException Em caso de falha no acesso ao banco de dados.
     */
    public List<Transferencia> buscarPorDataTransferencia(LocalDate dataTransferencia) {
        try {
            return repository.findByDataTransferencia(dataTransferencia);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar transferências pela data: " + e.getMessage(), e);
        }
    }

    /**
     * Calcula a taxa de transferência com base na diferença de dias
     * entre a data de agendamento e a data de transferência.
     *
     * @param diasDiferenca Diferença em dias entre as duas datas.
     * @param valor Valor da transferência.
     * @return BigDecimal Valor calculado da taxa.
     * @throws IllegalArgumentException Se a diferença de dias for inválida.
     */
    public static BigDecimal calcularTaxa(int diasDiferenca, BigDecimal valor) {
        if (diasDiferenca == 0) {
            return valor.multiply(BigDecimal.valueOf(0.025)).add(BigDecimal.valueOf(3.00));
        } else if (diasDiferenca <= 10) {
            return BigDecimal.valueOf(12.00);
        } else if (diasDiferenca <= 20) {
            return valor.multiply(BigDecimal.valueOf(0.082));
        } else if (diasDiferenca <= 30) {
            return valor.multiply(BigDecimal.valueOf(0.069));
        } else if (diasDiferenca <= 40) {
            return valor.multiply(BigDecimal.valueOf(0.047));
        } else if (diasDiferenca <= 50) {
            return valor.multiply(BigDecimal.valueOf(0.017));
        }
        throw new IllegalArgumentException("Taxa não aplicável. Transferência não permitida.");
    }
}
