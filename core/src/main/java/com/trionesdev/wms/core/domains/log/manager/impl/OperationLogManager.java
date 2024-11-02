package com.trionesdev.wms.core.domains.log.manager.impl;

import com.trionesdev.wms.core.domains.log.dao.impl.OperationLogDAO;
import com.trionesdev.wms.core.domains.log.dao.po.OperationLogPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OperationLogManager {
    private final OperationLogDAO operationLogDAO;

    public void create(OperationLogPO operationLog) {
        operationLogDAO.save(operationLog);
    }

}
