package org.agilekip.tutorials.travel.service.mapper;

import org.agilekip.tutorials.travel.domain.*;
import org.agilekip.tutorials.travel.service.dto.ProcessoIngressoDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProcessoIngresso} and its DTO {@link ProcessoIngressoDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, IngressoMapper.class })
public interface ProcessoIngressoMapper extends EntityMapper<ProcessoIngressoDTO, ProcessoIngresso> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "ingresso", source = "ingresso")
    ProcessoIngressoDTO toDto(ProcessoIngresso s);
}
