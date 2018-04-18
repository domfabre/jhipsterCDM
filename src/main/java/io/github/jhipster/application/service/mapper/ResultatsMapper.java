package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ResultatsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Resultats and its DTO ResultatsDTO.
 */
@Mapper(componentModel = "spring", uses = {NationsMapper.class, MatchsMapper.class})
public interface ResultatsMapper extends EntityMapper<ResultatsDTO, Resultats> {

    @Mapping(source = "nations.id", target = "nationsId")
    @Mapping(source = "matchs.id", target = "matchsId")
    ResultatsDTO toDto(Resultats resultats);

    @Mapping(source = "nationsId", target = "nations")
    @Mapping(source = "matchsId", target = "matchs")
    Resultats toEntity(ResultatsDTO resultatsDTO);

    default Resultats fromId(Long id) {
        if (id == null) {
            return null;
        }
        Resultats resultats = new Resultats();
        resultats.setId(id);
        return resultats;
    }
}
