package com.trionesdev.mes.rest.backend.domains.org.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberDTO;
import com.trionesdev.mes.core.domains.org.service.impl.TenantService;
import com.trionesdev.mes.rest.backend.domains.org.controller.query.TenantMemberQuery;
import com.trionesdev.mes.rest.backend.domains.org.controller.ro.TenantMemberRO;
import com.trionesdev.mes.rest.backend.domains.org.internal.OrgBeRestBeanConvert;
import com.trionesdev.mes.rest.backend.domains.org.internal.OrgRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "租户")
@RequiredArgsConstructor
@RestController
@RequestMapping(OrgRestConstants.ORG_PATH)
public class TenantController {
    private final OrgBeRestBeanConvert convert;
    private final TenantService tenantService;

    @Operation(summary = "创建租户成员")
    @PostMapping("tenant/members")
    public void createTenantMember(@Validated @RequestBody TenantMemberRO.Create args) {
        var tenantMember = convert.from(args);
        tenantService.createMember(tenantMember);
    }

    @Operation(summary = "根据ID更新租户成员")
    @PutMapping("tenant/members/{id}")
    public void updateTenantMemberById(@PathVariable(value = "id") String id, @Validated @RequestBody TenantMemberRO.Update args) {
        var tenantMember = convert.from(args);
        tenantMember.setId(id);
        tenantService.updateMemberById(tenantMember);
    }

    @Operation(summary = "根据ID查询租户成员")
    @GetMapping("tenant/members/{id}")
    public TenantMemberDTO queryTenantMemberById(@PathVariable String id) {
        return tenantService.findTenantMemberByMemberId(id).orElse(null);
    }

    @Operation(summary = "查询租户成员列表分页")
    @GetMapping("tenant/members/page")
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
