package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.JoueursDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Joueurs and its DTO JoueursDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface JoueursMapper extends EntityMapper<JoueursDTO, Joueurs> {


    @Mapping(target = "idjoueurs", ignore = true)
    Joueurs toEntity(JoueursDTO joueursDTO);

    default Joueurs fromId(Long id) {
        if (id == null) {
            return null;
        }
        Joueurs joueurs = new Joueurs();
        joueurs.setId(id);
        return joueurs;
    }
}
