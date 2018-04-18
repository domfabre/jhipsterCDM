package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.NationsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Nations and its DTO NationsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NationsMapper extends EntityMapper<NationsDTO, Nations> {


    @Mapping(target = "idnations", ignore = true)
    Nations toEntity(NationsDTO nationsDTO);

    default Nations fromId(Long id) {
        if (id == null) {
            return null;
        }
        Nations nations = new Nations();
        nations.setId(id);
        return nations;
    }
}
