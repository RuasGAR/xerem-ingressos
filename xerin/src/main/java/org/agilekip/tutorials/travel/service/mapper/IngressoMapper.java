package org.agilekip.tutorials.travel.service.mapper;

import org.agilekip.tutorials.travel.domain.*;
import org.agilekip.tutorials.travel.service.dto.IngressoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ingresso} and its DTO {@link IngressoDTO}.
 */
@Mapper(componentModel = "spring", uses = { TimeMapper.class, EstadioMapper.class })
public interface IngressoMapper extends EntityMapper<IngressoDTO, Ingresso> {
    @Mapping(target = "timeMandante", source = "timeMandante", qualifiedByName = "nome")
    @Mapping(target = "estadio", source = "estadio", qualifiedByName = "nome")
    IngressoDTO toDto(Ingresso s);
}
