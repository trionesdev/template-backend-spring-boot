package com.trionesdev.mes.domain.core.domains.masterdata.internal;

import com.trionesdev.mes.domain.core.domains.masterdata.dao.po.ManufactureProcessPO;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.po.ProductDefinitionPO;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.po.TechnologyPO;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.po.UnitPO;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.Technology;
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
    UnitDTO entityToDto(UnitPO unit);

    List<UnitDTO> unitsEntityToDto(List<UnitPO> unit);
    //endregion

    //region production definition
    ProductDefinitionDTO entityToDto(ProductDefinitionPO productDefinition);

    List<ProductDefinitionDTO> productDefinitionsEntityToDto(List<ProductDefinitionPO> productDefinition);
    //endregion

    //region manufacture process
    ManufactureProcessDTO entityToDto(ManufactureProcessPO manufactureProcess);

    List<ManufactureProcessDTO> manufactureProcessesEntityToDto(List<ManufactureProcessPO> manufactureProcess);
    //endregion


    //region technology
    TechnologyDTO entityToDto(TechnologyPO technology);

    List<TechnologyDTO> technologiesEntityToDto(List<TechnologyPO> technology);

    TechnologyPO entityToPo(Technology technology);
    //endregion

}
