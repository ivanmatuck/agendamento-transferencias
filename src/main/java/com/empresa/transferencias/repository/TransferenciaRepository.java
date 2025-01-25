package com.empresa.transferencias.repository;

import com.empresa.transferencias.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface de repositório para a entidade Transferencia.
 * Fornece métodos para interagir com o banco de dados.
 */
@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    /**
     * Busca transferências agendadas com base na data de transferência.
     *
     * @param dataTransferencia data da transferência a ser buscada.
     * @return Lista de transferências agendadas para a data especificada.
     */
    List<Transferencia> findByDataTransferencia(LocalDate dataTransferencia);
}
