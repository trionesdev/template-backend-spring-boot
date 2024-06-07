package com.trionesdev.mes.domain.core.domains.manufacture.internal;

import com.trionesdev.mes.domain.core.domains.manufacture.entity.ManufactureOrder;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderMaterialPO;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderPO;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderTaskPO;
import com.trionesdev.mes.domain.core.dto.manufacture.ManufactureOrderDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

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

    List<ManufactureOrder.Task> tasksPoToEntity(List<ManufactureOrderTaskPO> tasks);

    List<ManufactureOrder.Material> materialsPoToEntity(List<ManufactureOrderMaterialPO> materials);


    ManufactureOrderDTO entityToDto(ManufactureOrder manufactureOrder);
}
