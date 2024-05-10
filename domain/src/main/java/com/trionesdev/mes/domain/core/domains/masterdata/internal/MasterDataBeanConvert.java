package com.trionesdev.mes.domain.core.domains.masterdata.internal;

import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ManufactureProcessPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProductDefinitionPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProcessFlowPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.UnitPO;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.ProcessFlow;
import com.trionesdev.mes.domain.core.dto.masterdata.ManufactureProcessDTO;
import com.trionesdev.mes.domain.core.dto.masterdata.ProcessFlowDTO;
import com.trionesdev.mes.domain.core.dto.masterdata.ProductDefinitionDTO;
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
    UnitDTO poToDto(UnitPO unit);

    List<UnitDTO> unitsEntityToDto(List<UnitPO> unit);
    //endregion

    //region production definition
    ProductDefinitionDTO entityToDto(ProductDefinitionPO productDefinition);

    List<ProductDefinitionDTO> productDefinitionsPoToDto(List<ProductDefinitionPO> productDefinition);
    //endregion

    //region manufacture process
    ManufactureProcessDTO poToDto(ManufactureProcessPO manufactureProcess);

    List<ManufactureProcessDTO> manufactureProcessesEntityToDto(List<ManufactureProcessPO> manufactureProcess);
    //endregion


    //region process flow
    ProcessFlowDTO entityToDto(ProcessFlow processFlow);

    ProcessFlowPO entityToPo(ProcessFlow processFlow);

    ProcessFlow poToEntity(ProcessFlowPO processFlowPO);
    //endregion

}
