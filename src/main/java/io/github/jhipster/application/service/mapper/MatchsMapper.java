package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.MatchsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Matchs and its DTO MatchsDTO.
 */
@Mapper(componentModel = "spring", uses = {StadesMapper.class})
public interface MatchsMapper extends EntityMapper<MatchsDTO, Matchs> {

    @Mapping(source = "stades.id", target = "stadesId")
    MatchsDTO toDto(Matchs matchs);

    @Mapping(target = "idmatches", ignore = true)
    @Mapping(source = "stadesId", target = "stades")
    Matchs toEntity(MatchsDTO matchsDTO);

    default Matchs fromId(Long id) {
        if (id == null) {
            return null;
        }
        Matchs matchs = new Matchs();
        matchs.setId(id);
        return matchs;
    }
}
