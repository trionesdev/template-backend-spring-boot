package com.trionesdev.template.rest.boss.domains.tenant.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.template.core.domains.tenant.dto.TenantDTO;
import com.trionesdev.template.core.domains.tenant.service.impl.TenantService;
import com.trionesdev.template.rest.boss.domains.tenant.controller.ro.TenantQueryRO;
import com.trionesdev.template.rest.boss.domains.tenant.internal.TenantRestBossConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.trionesdev.template.rest.boss.domains.tenant.internal.TenantConstants.TENANT_PATH;

@Tag(name = "组织-租户")
@RequiredArgsConstructor
@RestController("boss_tenantController")
@RequestMapping(TENANT_PATH)
public class TenantController {
    private final TenantRestBossConvert convert;
    private final TenantService tenantService;

    @Operation(summary = "根据ID获取租户信息")
    @GetMapping(value = "tenants/{id}")
    public TenantDTO queryById(@PathVariable(value = "id") String id) {
        return tenantService.findTenantById(id).orElse(null);
    }

    @Operation(summary = "租户列表(分页)")
    @GetMapping(value = "tenant/page")
    public PageInfo<TenantDTO> queryTenantPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            TenantQueryRO query
    ) {
        var criteria = convert.tenantCriteriaFromQueryRo(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return tenantService.queryTenantPage(criteria);
    }

}
