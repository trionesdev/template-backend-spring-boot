package com.trionesdev.mes.rest.backend.domains.tenant.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.application.tenant.impl.TenantAppService;
import com.trionesdev.mes.domain.core.domains.tenant.service.TenantDomainService;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;
import com.trionesdev.mes.rest.backend.domains.tenant.controller.query.TenantMemberQuery;
import com.trionesdev.mes.rest.backend.domains.tenant.controller.ro.TenantMemberRO;
import com.trionesdev.mes.rest.backend.domains.tenant.internal.TenantBeRestBeanConvert;
import com.trionesdev.mes.rest.backend.domains.tenant.internal.TenantRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "租户")
@RequiredArgsConstructor
@RestController
@RequestMapping(TenantRestConstants.TENANT_PATH)
public class TenantResource {
    private final TenantBeRestBeanConvert convert;
    private final TenantDomainService tenantService;
    private final TenantAppService tenantAppService;

    @Operation(summary = "创建租户成员")
    @PostMapping("members")
    public void createTenantMember(@Validated @RequestBody TenantMemberRO.Create args) {
        var tenantMember = convert.from(args);
        tenantAppService.createTenantMember(tenantMember);
    }

    @Operation(summary = "根据ID更新租户成员")
    @PutMapping("members/{id}")
    public void updateTenantMemberById(@PathVariable(value = "id") String id, @Validated @RequestBody TenantMemberRO.Update args) {
        var tenantMember = convert.from(args);
        tenantMember.setId(id);
        tenantAppService.updateTenantMemberById(tenantMember);
    }

    @Operation(summary = "根据ID查询租户成员")
    @GetMapping("members/{id}")
    public TenantMemberDTO queryTenantMemberById(@PathVariable String id) {
        return tenantAppService.findTenantMemberByMemberId(id).orElse(null);
    }

    @Operation(summary = "查询租户成员列表分页")
    @GetMapping("members/page")
    public PageInfo<TenantMemberDTO> queryTenantMembersPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            TenantMemberQuery query) {
        var criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return tenantService.findTenantMembersPage(criteria);
    }
}
