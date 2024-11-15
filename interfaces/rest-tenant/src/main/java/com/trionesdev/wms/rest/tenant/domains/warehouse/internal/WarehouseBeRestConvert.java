package com.trionesdev.wms.rest.tenant.domains.warehouse.internal;

import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehousePO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseQueryRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
@Named("warehouseBeRestBeanConvert")
public interface WarehouseBeRestConvert {

    WarehouseCriteria from(WarehouseQueryRO args);

    WarehousePO from(WarehouseRO args);

}
