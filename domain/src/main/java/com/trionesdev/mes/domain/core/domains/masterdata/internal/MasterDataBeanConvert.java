package com.trionesdev.mes.domain.core.domains.masterdata.internal;

import com.trionesdev.mes.domain.core.domains.masterdata.entity.ManufactureBom;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.ProductBom;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.ProductDefinition;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.*;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.ProcessFlow;
import com.trionesdev.mes.domain.core.dto.masterdata.*;
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

    DefectiveDTO defectivePoToDto(DefectivePO defective);

    //region production definition
    ProductDefinitionDTO entityToDto(ProductDefinitionPO productDefinition);

    List<ProductDefinitionDTO> productDefinitionsPoToDto(List<ProductDefinitionPO> productDefinition);

    ProductDefinition poToEntity(ProductDefinitionPO productDefinitionPO);

    ProductDefinitionDTO entityToDto(ProductDefinition productDefinition);
    //endregion

    //region manufacture process
    ManufactureProcessDTO poToDto(ManufactureProcessPO manufactureProcess);

    List<ManufactureProcessDTO> manufactureProcessesEntityToDto(List<ManufactureProcessPO> manufactureProcess);

    ManufactureProcessDTO.Defective poToDto(DefectivePO defective);
    //endregion


    //region process flow
    ProcessFlowDTO entityToDto(ProcessFlow processFlow);

    ProcessFlowPO entityToPo(ProcessFlow processFlow);

    ProcessFlow poToEntity(ProcessFlowPO processFlowPO);
    //endregion

    //region manufacture bom
    ManufactureBomPO entityToPo(ManufactureBom manufactureBom);

    ManufactureBom poToEntity(ManufactureBomPO manufactureBomPO);

    ManufactureBomItemPO entityToPo(ManufactureBom.Material material);

//    ManufactureBom.Material poToEntity(ManufactureBomItemPO manufactureBomItemPO);

    ManufactureBomDTO entityToDto(ManufactureBom manufactureBom);

    List<ManufactureBomDTO> manufactureBomListEntityToDto(List<ManufactureBom> manufactureProcess);


    ProductMaterialPO entityToPo(ProductBom.Material material);

    ProductBom.Material poToEntity(ProductMaterialPO material);

    ProductBomDTO bomEntityToDto(ProductDefinition productDefinition);

    ProductMaterialDTO poToDto(ProductMaterialPO material);

    ProductMaterialDTO.Product productEntityToDto(ProductDefinition productDefinition);
    //endregion

}
