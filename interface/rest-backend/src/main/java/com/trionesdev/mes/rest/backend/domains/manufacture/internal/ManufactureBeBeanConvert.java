package com.trionesdev.mes.rest.backend.domains.manufacture.internal;

import com.trionesdev.mes.domain.core.domains.manufacture.entity.ManufactureOrder;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro.ManufactureOrderRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("manufactureBeBeanConvert")
public interface ManufactureBeBeanConvert {
    ManufactureOrder from(ManufactureOrderRO.Create args);

    ManufactureOrder from(ManufactureOrderRO.Update args);

    ManufactureOrder.Task from(ManufactureOrderRO.Task args);

    ManufactureOrder.Material from(ManufactureOrderRO.Material args);
}
