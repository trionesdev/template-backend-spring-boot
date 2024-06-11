package com.trionesdev.mes.domain.core.domains.manufacture.internal;

import com.trionesdev.mes.domain.core.domains.manufacture.entity.ManufactureOrder;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderMaterialPO;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderPO;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderTaskPO;
import com.trionesdev.mes.domain.core.dto.manufacture.ManufactureOrderDTO;
import com.trionesdev.mes.domain.core.dto.manufacture.ManufactureOrderTaskDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("manufactureBeanConvert")
public interface ManufactureBeanConvert {

    ManufactureOrderPO entityToPo(ManufactureOrder manufactureOrder);

    ManufactureOrderTaskPO entityToPo(ManufactureOrder.Task task);

    ManufactureOrderMaterialPO entityToPo(ManufactureOrder.Material material);

    ManufactureOrder poToEntity(ManufactureOrderPO manufactureOrderPO);

    ManufactureOrder.Task taskPoToEntity(ManufactureOrderTaskPO manufactureOrderTaskPO);

    ManufactureOrder.Material materialPoToEntity(ManufactureOrderMaterialPO materialPO);


    ManufactureOrderDTO entityToDto(ManufactureOrder manufactureOrder);

    ManufactureOrderDTO.Task taskEntityToDto(ManufactureOrder.Task task);

    ManufactureOrderDTO.Material materialEntityToDto(ManufactureOrder.Material material);

    ManufactureOrderTaskDTO taskPoToDto(ManufactureOrderTaskPO task);

}
