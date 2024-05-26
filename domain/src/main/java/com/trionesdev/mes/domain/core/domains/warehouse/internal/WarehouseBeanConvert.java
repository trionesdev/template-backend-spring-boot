package com.trionesdev.mes.domain.core.domains.warehouse.internal;

import com.trionesdev.mes.domain.core.domains.warehouse.repository.po.WarehousePO;
import com.trionesdev.mes.domain.core.dto.warehouse.WarehouseDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("warehouseBeanConvert")
public interface WarehouseBeanConvert {

    WarehouseDTO poToDto(WarehousePO warehouse);

}
