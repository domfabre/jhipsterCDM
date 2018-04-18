package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Joueurs;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Joueurs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JoueursRepository extends JpaRepository<Joueurs, Long> {

}
