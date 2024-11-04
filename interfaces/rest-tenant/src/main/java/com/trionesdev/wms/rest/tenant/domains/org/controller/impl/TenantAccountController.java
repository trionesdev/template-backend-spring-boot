package com.trionesdev.wms.rest.tenant.domains.org.controller.impl;

import com.trionesdev.spring.core.audit.OperationAudit;
import com.trionesdev.wms.core.domains.org.dto.TenantMemberSignInCmd;
import com.trionesdev.wms.core.domains.org.internal.handler.LoginOperationHandler;
import com.trionesdev.wms.core.domains.org.service.impl.TenantService;
import com.trionesdev.wms.rest.tenant.domains.org.controller.ro.tenant.TenantAccountSignInRO;
import com.trionesdev.wms.rest.tenant.domains.org.controller.vo.TokenVO;
import com.trionesdev.wms.rest.tenant.domains.org.internal.OrgBeRestConvert;
import com.trionesdev.wms.rest.tenant.domains.org.internal.OrgRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "租户账户")
@RequiredArgsConstructor
@RestController
@RequestMapping(OrgRestConstants.ORG_PATH)
public class TenantAccountController {
    private final OrgBeRestConvert convert;
    private final TenantService tenantService;

    @Operation(summary = "账号登录")
    @OperationAudit(type = "TENANT", category = "ORG", action = "LOGIN", description = "账号登录", handler = LoginOperationHandler.class)
    @PostMapping(value = "sign-in/account/{id}")
    public TokenVO accountSignIn(@PathVariable(value = "id") String id, @Validated @RequestBody TenantAccountSignInRO args) {
        var token = tenantService.accountSignIn(TenantMemberSignInCmd.builder().tenantSerial(args.getTenantSerial())
                .account(args.getAccount()).password(args.getPassword())
                .build());
        return TokenVO.builder().token(token).build();
    }
}
