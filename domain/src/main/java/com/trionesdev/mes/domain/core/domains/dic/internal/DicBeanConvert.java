package com.trionesdev.mes.domain.core.domains.dic.internal;

import com.trionesdev.mes.domain.core.domains.dic.dao.entity.Unit;
import com.trionesdev.mes.domain.core.dto.UnitDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("dicBeanConvert")
public interface DicBeanConvert {

    UnitDTO entityToDto(Unit unit);

    List<UnitDTO> unitsEntityToDto(List<Unit> unit);
}
