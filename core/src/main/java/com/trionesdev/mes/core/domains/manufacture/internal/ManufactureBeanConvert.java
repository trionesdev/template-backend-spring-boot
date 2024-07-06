package com.trionesdev.mes.core.domains.manufacture.internal;

import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureOrderPO;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureOrderTaskPO;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureOrderTaskReportPO;
import com.trionesdev.mes.core.domains.manufacture.dto.ManufactureOrderDTO;
import com.trionesdev.mes.core.domains.manufacture.dto.ManufactureOrderTaskReportDTO;
import com.trionesdev.mes.core.domains.manufacture.dto.ManufactureOrderTaskReportDetailDTO;
import com.trionesdev.mes.core.domains.manufacture.internal.entity.ManufactureOrder;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureOrderMaterialPO;
import com.trionesdev.mes.core.domains.manufacture.dto.ManufactureOrderTaskDTO;
import com.trionesdev.mes.core.domains.manufacture.internal.entity.ManufactureOrderTaskReport;
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


    //region order task report
    ManufactureOrderTaskReport reportDetailDtoToEntity(ManufactureOrderTaskReportDetailDTO report);
    ManufactureOrderTaskReport reportDtoToEntity(ManufactureOrderTaskReportDTO report);

    ManufactureOrderTaskReportPO reportEntityToPo(ManufactureOrderTaskReport report);

    ManufactureOrderTaskReport reportPoToEntity(ManufactureOrderTaskReportPO report);
    //endregion

}
