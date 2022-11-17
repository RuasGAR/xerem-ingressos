package org.agilekip.tutorials.travel.repository;

import org.agilekip.tutorials.travel.domain.Ingresso;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Ingresso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {}
