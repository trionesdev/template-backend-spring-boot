package com.trionesdev.wms.core.domains.warehouse.internal;

import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseAreaPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseLocationPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehousePO;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseAreaDTO;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseDTO;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseLocationDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("warehouseBeanConvert")
public interface WarehouseBeanConvert {

    WarehouseDTO poToDto(WarehousePO warehouse);

    WarehouseAreaDTO poToDto(WarehouseAreaPO warehouseArea);

    WarehouseLocationDTO poToDto(WarehouseLocationPO warehouseLocation);

}
