package org.agilekip.tutorials.travel.repository;

import java.util.Optional;
import org.agilekip.tutorials.travel.domain.ProcessoIngresso;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ProcessoIngresso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProcessoIngressoRepository extends JpaRepository<ProcessoIngresso, Long> {
    Optional<ProcessoIngresso> findByProcessInstanceId(Long processInstanceId);
}
