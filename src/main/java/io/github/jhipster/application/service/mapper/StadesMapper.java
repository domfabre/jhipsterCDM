package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.StadesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Stades and its DTO StadesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StadesMapper extends EntityMapper<StadesDTO, Stades> {


    @Mapping(target = "idstades", ignore = true)
    Stades toEntity(StadesDTO stadesDTO);

    default Stades fromId(Long id) {
        if (id == null) {
            return null;
        }
        Stades stades = new Stades();
        stades.setId(id);
        return stades;
    }
}
