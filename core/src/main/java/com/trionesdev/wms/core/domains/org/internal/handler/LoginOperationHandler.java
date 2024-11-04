package com.trionesdev.wms.core.domains.org.internal.handler;

import com.alibaba.fastjson2.JSON;
import com.trionesdev.spring.core.audit.OperationAuditContext;
import com.trionesdev.spring.core.audit.OperationAuditHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoginOperationHandler extends OperationAuditHandler {

    @Override
    public void handle(OperationAuditContext operationAuditContext) {
        System.out.println(JSON.toJSONString(operationAuditContext));
    }
}
