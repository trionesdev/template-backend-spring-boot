package com.trionesdev.wms.rest.tenant.domains.log.internal;

import com.trionesdev.wms.core.domains.log.dao.criteria.OperationLogCriteria;
import com.trionesdev.wms.rest.tenant.domains.log.controller.ro.OperationLogQueryRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface LogTenantRestConvert {
    OperationLogCriteria from(OperationLogQueryRO query);
}
