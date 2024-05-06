package com.trioinesdev.backend.rest.backend.domains.masterdata.internal;

import com.trioinesdev.backend.rest.backend.domains.masterdata.controller.ro.UnitCreateRO;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.Unit;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("masterDataBeRestBeanConvert")
public interface MasterDataBeRestBeanConvert {

    Unit from(UnitCreateRO args);
}
