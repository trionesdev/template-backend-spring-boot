package com.trionesdev.template.rest.tenant.domains.log.internal;

import com.trionesdev.template.core.domains.log.dao.criteria.OperationLogCriteria;
import com.trionesdev.template.rest.tenant.domains.log.controller.ro.OperationLogQueryRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface LogTenantRestConvert {
    OperationLogCriteria from(OperationLogQueryRO query);
}
