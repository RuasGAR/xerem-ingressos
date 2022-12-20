package org.agilekip.tutorials.travel.service.mapper;

import org.agilekip.tutorials.travel.domain.*;
import org.agilekip.tutorials.travel.service.dto.EstadioDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Estadio} and its DTO {@link EstadioDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EstadioMapper extends EntityMapper<EstadioDTO, Estadio> {
    @Named("nome")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    EstadioDTO toDtoNome(Estadio estadio);
}
