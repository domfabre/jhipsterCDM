package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ParisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Paris and its DTO ParisDTO.
 */
@Mapper(componentModel = "spring", uses = {JoueursMapper.class, ResultatsMapper.class})
public interface ParisMapper extends EntityMapper<ParisDTO, Paris> {

    @Mapping(source = "joueurs.id", target = "joueursId")
    @Mapping(source = "idparis.id", target = "idparisId")
    ParisDTO toDto(Paris paris);

    @Mapping(source = "joueursId", target = "joueurs")
    @Mapping(source = "idparisId", target = "idparis")
    Paris toEntity(ParisDTO parisDTO);

    default Paris fromId(Long id) {
        if (id == null) {
            return null;
        }
        Paris paris = new Paris();
        paris.setId(id);
        return paris;
    }
}
