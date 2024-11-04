package com.trionesdev.wms.core.domains.log.internal;

import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.spring.core.audit.OperationAuditContext;
import com.trionesdev.spring.core.audit.OperationAuditHandler;
import com.trionesdev.wms.core.domains.log.dao.po.OperationLogPO;
import com.trionesdev.wms.core.domains.log.manager.impl.OperationLogManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DefaultOperationHandler extends OperationAuditHandler {
    private final ActorContext actorContext;
    private final OperationLogManager operationLogManager;

    @Override
    public Boolean isDefault() {
        return true;
    }

    @Override
    public void handle(OperationAuditContext operationAuditContext) {
        OperationLogPO operationLog = OperationLogPO.builder()
                .actorId(actorContext.getUserId())
                .actorRole(actorContext.getRole())
                .type(operationAuditContext.getType())
                .category(operationAuditContext.getCategory())
                .action(operationAuditContext.getAction())
                .description(operationAuditContext.getDescription())
                .startAt(operationAuditContext.getStartAt())
                .endAt(operationAuditContext.getEndAt())
                .success(operationAuditContext.getSuccess())
                .errorMsg(StringUtils.substring(operationAuditContext.getErrorMsg(), 0, 500))
                .build();
        operationLogManager.create(operationLog);
    }
}
