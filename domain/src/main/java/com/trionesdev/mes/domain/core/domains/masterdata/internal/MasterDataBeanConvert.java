package com.trionesdev.mes.domain.core.domains.masterdata.internal;

import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.Technology;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.Unit;
import com.trionesdev.mes.domain.core.dto.masterdata.TechnologyDTO;
import com.trionesdev.mes.domain.core.dto.masterdata.UnitDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("masterDataBeanConvert")
public interface MasterDataBeanConvert {

    UnitDTO entityToDto(Unit unit);

    List<UnitDTO> unitsEntityToDto(List<Unit> unit);

    TechnologyDTO entityToDto(Technology technology);

    List<TechnologyDTO> technologiesEntityToDto(List<Technology> technology);

    Technology dtoToEntity(TechnologyDTO technology);

}
