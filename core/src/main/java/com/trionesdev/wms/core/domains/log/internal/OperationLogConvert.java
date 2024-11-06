package com.trionesdev.wms.core.domains.log.internal;

import com.trionesdev.wms.core.domains.log.dao.po.OperationLogPO;
import com.trionesdev.wms.core.domains.log.dto.OperationLogCreateCmd;
import com.trionesdev.wms.core.domains.log.dto.OperationLogExtDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface OperationLogConvert {

    OperationLogPO operationLogCreateCmdToPo(OperationLogCreateCmd cmd);

    OperationLogExtDTO operationLogPoToExtDto(OperationLogPO po);
}
