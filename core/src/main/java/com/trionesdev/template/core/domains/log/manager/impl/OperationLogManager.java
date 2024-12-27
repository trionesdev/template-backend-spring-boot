package com.trionesdev.template.core.domains.log.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.template.core.domains.log.dao.criteria.OperationLogCriteria;
import com.trionesdev.template.core.domains.log.dao.impl.OperationLogDAO;
import com.trionesdev.template.core.domains.log.dao.po.OperationLogPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OperationLogManager {
    private final OperationLogDAO operationLogDAO;

    public void create(OperationLogPO operationLog) {
        operationLogDAO.save(operationLog);
    }

    public PageInfo<OperationLogPO> findPage(OperationLogCriteria criteria) {
        return operationLogDAO.selectPage(criteria);
    }

}
