package com.trionesdev.wms.rest.tenant.domains.dic.internal;

import com.trionesdev.wms.core.domains.dic.dao.criteria.DistrictCriteria;
import com.trionesdev.wms.rest.tenant.domains.dic.controller.ro.DistrictQueryRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface DicBeRestConvert {
    DistrictCriteria from(DistrictQueryRO args);
}
