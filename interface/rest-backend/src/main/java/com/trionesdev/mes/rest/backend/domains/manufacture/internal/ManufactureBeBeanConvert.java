package com.trionesdev.mes.rest.backend.domains.manufacture.internal;

import com.trionesdev.mes.domain.core.domains.manufacture.entity.ManufactureOrder;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro.ManufactureOrderCreateRO;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro.ManufactureOrderUpdateRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("manufactureBeBeanConvert")
public interface ManufactureBeBeanConvert {
    ManufactureOrder from(ManufactureOrderCreateRO args);

    ManufactureOrder from(ManufactureOrderUpdateRO args);
}
