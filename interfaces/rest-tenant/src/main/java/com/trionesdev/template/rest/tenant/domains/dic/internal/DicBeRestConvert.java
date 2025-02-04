package com.trionesdev.template.rest.tenant.domains.dic.internal;

import com.trionesdev.template.core.domains.dic.dao.criteria.DistrictCriteria;
import com.trionesdev.template.rest.tenant.domains.dic.controller.ro.DistrictQueryRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface DicBeRestConvert {
    DistrictCriteria from(DistrictQueryRO args);
}
