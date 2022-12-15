package org.agilekip.tutorials.travel.process.processoIngresso;

import org.agilekip.tutorials.travel.domain.Ingresso;
import org.agilekip.tutorials.travel.domain.ProcessoIngresso;
import org.agilekip.tutorials.travel.service.dto.IngressoDTO;
import org.agilekip.tutorials.travel.service.dto.ProcessoIngressoDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface ConfirmacaoMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    ProcessoIngressoDTO toProcessoIngressoDTO(ProcessoIngresso processoIngresso);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "timeMandante", source = "timeMandante")
    @Mapping(target = "timeVisitante", source = "timeVisitante")
    @Mapping(target = "horarioJogo", source = "horarioJogo")
    @Mapping(target = "data", source = "data")
    @Mapping(target = "estadio", source = "estadio")
    @Mapping(target = "setorEstadio", source = "setorEstadio")
    @Mapping(target = "assentoEstadio", source = "assentoEstadio")
    @Mapping(target = "nomeComprador", source = "nomeComprador")
    @Mapping(target = "cpfComprador", source = "cpfComprador")
    @Mapping(target = "nascimentoComprador", source = "nascimentoComprador")
    @Mapping(target = "emailComprador", source = "emailComprador")
    IngressoDTO toIngressoDTO(Ingresso ingresso);
}
