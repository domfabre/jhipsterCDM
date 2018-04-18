package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Stades;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Stades entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StadesRepository extends JpaRepository<Stades, Long> {

}
