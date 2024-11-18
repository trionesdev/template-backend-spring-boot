package com.trionesdev.wms.rest.tenant.domains.warehouse.internal;

import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseAreaCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseLocationCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseAreaPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseLocationPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehousePO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseAreaQueryRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseAreaRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseLocationQueryRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseLocationRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseQueryRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
@Named("warehouseBeRestBeanConvert")
public interface WarehouseBeRestConvert {

    WarehouseCriteria from(WarehouseQueryRO args);

    WarehousePO from(WarehouseRO args);

    WarehouseAreaPO from(WarehouseAreaRO args);

    WarehouseAreaCriteria from(WarehouseAreaQueryRO args);

    WarehouseLocationCriteria from(WarehouseLocationQueryRO args);

    WarehouseLocationPO from(WarehouseLocationRO args);

}
