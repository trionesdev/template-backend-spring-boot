package com.trionesdev.wms.core.domains.good.internal;

import com.trionesdev.wms.core.domains.good.dao.po.GoodPO;
import com.trionesdev.wms.core.domains.good.dto.GoodDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface GoodBeanConvert {

    GoodDTO poToDto(GoodPO po);
}
