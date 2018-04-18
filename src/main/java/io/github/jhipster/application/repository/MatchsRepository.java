package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Matchs;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Matchs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MatchsRepository extends JpaRepository<Matchs, Long> {

}
