package com.trionesdev.mes.domain.core.domains.masterdata.internal;

import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.ManufactureProcess;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.ProductDefinition;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.Technology;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.Unit;
import com.trionesdev.mes.domain.core.dto.masterdata.ManufactureProcessDTO;
import com.trionesdev.mes.domain.core.dto.masterdata.ProductDefinitionDTO;
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

    //region unit
    UnitDTO entityToDto(Unit unit);

    List<UnitDTO> unitsEntityToDto(List<Unit> unit);
    //endregion

    //region production definition
    ProductDefinitionDTO entityToDto(ProductDefinition productDefinition);

    List<ProductDefinitionDTO> productDefinitionsEntityToDto(List<ProductDefinition> productDefinition);
    //endregion

    //region manufacture process
    ManufactureProcessDTO entityToDto(ManufactureProcess manufactureProcess);

    List<ManufactureProcessDTO> manufactureProcessesEntityToDto(List<ManufactureProcess> manufactureProcess);
    //endregion


    //region technology
    TechnologyDTO entityToDto(Technology technology);

    List<TechnologyDTO> technologiesEntityToDto(List<Technology> technology);

    Technology dtoToEntity(TechnologyDTO technology);
    //endregion

}
