package org.agilekip.tutorials.travel.service.mapper;

import org.agilekip.tutorials.travel.domain.*;
import org.agilekip.tutorials.travel.service.dto.IngressoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ingresso} and its DTO {@link IngressoDTO}.
 */
@Mapper(componentModel = "spring", uses = { EstadioMapper.class })
public interface IngressoMapper extends EntityMapper<IngressoDTO, Ingresso> {
    @Mapping(target = "nomeEstadio", source = "nomeEstadio", qualifiedByName = "nome")
    IngressoDTO toDto(Ingresso s);
}
