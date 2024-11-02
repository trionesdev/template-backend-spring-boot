package com.trionesdev.wms.core.domains.log.internal;

import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.core.util.JsonUtils;
import com.trionesdev.spring.core.audit.OperationAuditContext;
import com.trionesdev.spring.core.audit.OperationAuditHandler;
import com.trionesdev.wms.core.domains.log.manager.impl.OperationLogManager;
import lombok.RequiredArgsConstructor;
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
    public void process(OperationAuditContext operationAuditContext) {
        System.out.println(JsonUtils.toJsonString(operationAuditContext));
//        OperationLogPO operationLog = OperationLogPO.builder()
//                .operatorId(actorContext.getUserId())
//                .operatorRole(actorContext.getRole())
//                .type(operationAuditContext.getType())
//                .action(operationAuditContext.getAction())
//                .description(operationAuditContext.getDescription())
//                .startAt(operationAuditContext.getStartAt())
//                .endAt(operationAuditContext.getEndAt())
//                .success(operationAuditContext.getSuccess())
//                .build();
//        operationLogManager.create(operationLog);
    }
}
