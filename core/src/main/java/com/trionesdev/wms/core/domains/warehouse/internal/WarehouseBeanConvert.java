package com.trionesdev.wms.core.domains.warehouse.internal;

import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseInboundPlanPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseInboundPlanItemPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseAreaPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseContainerPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseLocationPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehousePO;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseInboundPlanDTO;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseInboundPlanItemDTO;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseAreaDTO;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseContainerDTO;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseDTO;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseLocationDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("warehouseBeanConvert")
public interface WarehouseBeanConvert {

    WarehouseDTO poToDto(WarehousePO warehouse);

    WarehouseAreaDTO poToDto(WarehouseAreaPO warehouseArea);

    WarehouseLocationDTO poToDto(WarehouseLocationPO warehouseLocation);

    WarehouseContainerDTO poToDto(WarehouseContainerPO warehouseContainer);

    WarehouseInboundPlanDTO from(WarehouseInboundPlanPO args);

    WarehouseInboundPlanPO from(WarehouseInboundPlanDTO args);

    List<WarehouseInboundPlanDTO> fromInboundPlan(List<WarehouseInboundPlanPO> args);

    WarehouseInboundPlanItemDTO from(WarehouseInboundPlanItemPO args);

    List<WarehouseInboundPlanItemPO> fromWarehouseInboundPlanItemDTO(List<WarehouseInboundPlanItemDTO> args);

    List<WarehouseInboundPlanItemDTO> fromWarehouseInboundPlanItemPO(List<WarehouseInboundPlanItemPO> args);

}
