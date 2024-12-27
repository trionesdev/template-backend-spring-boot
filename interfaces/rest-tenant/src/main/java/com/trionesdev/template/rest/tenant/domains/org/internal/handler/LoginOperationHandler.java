package com.trionesdev.template.rest.tenant.domains.org.internal.handler;

import com.alibaba.fastjson2.JSON;
import com.trionesdev.commons.core.jwt.JwtFacade;
import com.trionesdev.spring.core.audit.OperationAuditContext;
import com.trionesdev.spring.core.audit.OperationAuditHandler;
import com.trionesdev.template.core.domains.log.dto.OperationLogCreateCmd;
import com.trionesdev.template.core.domains.log.provider.OperationLogProvider;
import com.trionesdev.template.rest.tenant.domains.org.controller.vo.TokenVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.trionesdev.commons.core.jwt.ClaimsKeyConstant.*;

@RequiredArgsConstructor
@Component
public class LoginOperationHandler extends OperationAuditHandler {
    private final JwtFacade jwtFacade;
    private final OperationLogProvider operationLogProvider;

    @Override
    public void handle(OperationAuditContext operationAuditContext) {
        OperationLogCreateCmd cmd = OperationLogCreateCmd.builder()
                .type(operationAuditContext.getType())
                .category(operationAuditContext.getCategory())
                .action(operationAuditContext.getAction())
                .description(operationAuditContext.getDescription())
                .startAt(operationAuditContext.getStartAt())
                .endAt(operationAuditContext.getEndAt())
                .success(operationAuditContext.getSuccess())
                .errorMsg(operationAuditContext.getErrorMsg())
                .build();
        Optional.ofNullable(operationAuditContext.getBeforeValues()).ifPresent(map -> cmd.setBeforeValues(JSON.toJSONString(map)));
        Optional.ofNullable(operationAuditContext.getAfterValues()).ifPresent(map -> cmd.setAfterValues(JSON.toJSONString(map)));
        if (operationAuditContext.getSuccess()) {
            var res = (TokenVO) operationAuditContext.getResponse();
            var token = res.getToken();
            Optional.ofNullable(jwtFacade.parse(token)).ifPresent(map -> {
                Optional.ofNullable(map.get(ACTOR_USER_ID)).ifPresent(userId -> cmd.setActorId(String.valueOf(userId)));
                Optional.ofNullable(map.get(ACTOR_TENANT_ID)).ifPresent(tenantId -> cmd.setTenantId(String.valueOf(tenantId)));
                Optional.ofNullable(map.get(ACTOR_ROLE)).ifPresent(actorRole -> cmd.setActorRole(String.valueOf(actorRole)));
            });
        }
        operationLogProvider.createOperationLog(cmd);
    }
}
