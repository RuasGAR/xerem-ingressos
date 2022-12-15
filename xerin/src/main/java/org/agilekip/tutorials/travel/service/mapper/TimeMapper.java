package org.agilekip.tutorials.travel.service.mapper;

import org.agilekip.tutorials.travel.domain.*;
import org.agilekip.tutorials.travel.service.dto.TimeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Time} and its DTO {@link TimeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TimeMapper extends EntityMapper<TimeDTO, Time> {
    @Named("nome")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    TimeDTO toDtoNome(Time time);
}
