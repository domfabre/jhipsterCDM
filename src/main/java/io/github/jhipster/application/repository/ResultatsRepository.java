package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Resultats;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Resultats entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResultatsRepository extends JpaRepository<Resultats, Long> {

}
