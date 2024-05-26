package com.trionesdev.mes.rest.backend.domains.warehouse.internal;

import com.trionesdev.mes.domain.core.domains.warehouse.repository.criteria.WarehouseCriteria;
import com.trionesdev.mes.domain.core.domains.warehouse.repository.po.WarehousePO;
import com.trionesdev.mes.rest.backend.domains.warehouse.controller.query.WarehouseQuery;
import com.trionesdev.mes.rest.backend.domains.warehouse.controller.ro.WarehouseCreateRO;
import com.trionesdev.mes.rest.backend.domains.warehouse.controller.ro.WarehouseUpdateRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("warehouseBeRestBeanConvert")
public interface WarehouseBeRestBeanConvert {

    WarehousePO from(WarehouseCreateRO args);

    WarehousePO from(WarehouseUpdateRO args);

    WarehouseCriteria from(WarehouseQuery query);
}
