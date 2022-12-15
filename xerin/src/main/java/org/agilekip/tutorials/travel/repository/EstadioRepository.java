package org.agilekip.tutorials.travel.repository;

import org.agilekip.tutorials.travel.domain.Estadio;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Estadio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EstadioRepository extends JpaRepository<Estadio, Long> {}
