package org.agilekip.tutorials.travel.process.processoIngresso;

import org.agilekip.tutorials.travel.domain.Estadio;
import org.agilekip.tutorials.travel.domain.Ingresso;
import org.agilekip.tutorials.travel.domain.ProcessoIngresso;
import org.agilekip.tutorials.travel.service.dto.EstadioDTO;
import org.agilekip.tutorials.travel.service.dto.IngressoDTO;
import org.agilekip.tutorials.travel.service.dto.ProcessoIngressoDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface EscolherSetorMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    ProcessoIngressoDTO toProcessoIngressoDTO(ProcessoIngresso processoIngresso);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "setorEstadio", source = "setorEstadio")
    @Mapping(target = "assentoEstadio", source = "assentoEstadio")
    @Mapping(target = "estadio", source = "estadio")
    IngressoDTO toIngressoDTO(Ingresso ingresso);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    EstadioDTO toEstadioDTO(Estadio nome);
}
