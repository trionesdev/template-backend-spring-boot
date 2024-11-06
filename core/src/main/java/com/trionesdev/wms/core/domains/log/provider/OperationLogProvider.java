package com.trionesdev.wms.core.domains.log.provider;

import com.trionesdev.wms.core.domains.log.dto.OperationLogCreateCmd;
import com.trionesdev.wms.core.domains.log.internal.OperationLogConvert;
import com.trionesdev.wms.core.domains.log.manager.impl.OperationLogManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OperationLogProvider {
    private final OperationLogConvert convert;
    private final OperationLogManager operationLogManager;

    public void createOperationLog(OperationLogCreateCmd cmd) {
        var operationLogPO = convert.operationLogCreateCmdToPo(cmd);
        operationLogManager.create(operationLogPO);
    }
}
