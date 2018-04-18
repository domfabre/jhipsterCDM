package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Nations;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Nations entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NationsRepository extends JpaRepository<Nations, Long> {

}
