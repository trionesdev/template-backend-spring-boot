package com.trionesdev.template.rest.tenant.domains.org.controller.impl;

import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.template.core.domains.org.dto.OrgNodeDTO;
import com.trionesdev.template.core.domains.org.dto.TenantMemberDTO;
import com.trionesdev.template.core.domains.org.service.impl.TenantService;
import com.trionesdev.template.rest.tenant.domains.org.controller.ro.tenant.*;
import com.trionesdev.template.rest.tenant.domains.org.internal.OrgBeRestConvert;
import com.trionesdev.template.rest.tenant.domains.org.internal.OrgRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "租户")
@RequiredArgsConstructor
@RestController
@RequestMapping(OrgRestConstants.ORG_PATH)
public class TenantController {
    private final OrgBeRestConvert convert;
    private final ActorContext actorContext;
    private final TenantService tenantService;

    @Operation(summary = "创建租户成员")
    @PostMapping("tenant/members")
    public void createTenantMember(@Validated @RequestBody TenantMemberCreateRO args) {
        var tenantMember = convert.from(args);
        tenantService.createMember(tenantMember);
    }

    @Operation(summary = "根据ID更新租户成员")
    @PutMapping("tenant/members/{id}")
    public void updateTenantMemberById(
            @PathVariable(value = "id") String id,
            @Validated @RequestBody TenantMemberUpdateRO args
    ) {
        var tenantMember = convert.from(args);
        tenantMember.setId(id);
        tenantService.updateMemberProfileById(tenantMember);
    }

    @Operation(summary = "根据ID查询租户成员", description = "包含部门信息")
    @GetMapping(value = "tenant/members/{id}")
    public TenantMemberDTO queryTenantMemberById(@PathVariable String id) {
        return tenantService.findTenantMemberByMemberId(id).orElse(null);
    }

    @Operation(summary = "查询租户成员列表分页")
    @GetMapping("tenant/member/page")
    public PageInfo<TenantMemberDTO> queryTenantMembersPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            TenantMemberQueryRO query) {
        var criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return tenantService.findTenantMembersPage(criteria);
    }

    @Operation(summary = "获取当前执行成员")
    @GetMapping(value = "tenant/actor/member")
    public TenantMemberDTO queryActorMember() {
        return tenantService.findTenantMemberByMemberId(actorContext.getMemberId()).orElse(null);
    }

    @Operation(summary = "修改当前执行成员")
    @PutMapping(value = "tenant/actor/member")
    public void updateActorMember(@Validated @RequestBody ActorMemberProfileUpdateRO args) {
        var tenantMember = convert.from(args);
        tenantMember.setId(actorContext.getMemberId());
        tenantService.updateMemberById(tenantMember);
    }

    @Operation(summary = "修改当前用户密码")
    @PutMapping(value = "tenant/actor/password")
    public void actorChangePwd(@Validated @RequestBody ActorMemberChangePasswordRO args) {
        var changePwd = convert.form(args);
        tenantService.changeActorPassword(changePwd);
    }

    @Operation(summary = "修改密码")
    @PutMapping(value = "tenant/member/password")
    public void changePwd(@Validated @RequestBody ChangePasswordRO args) {
        var changePwd = convert.form(args);
        tenantService.changePassword(changePwd);
    }

    @Operation(summary = "查询组织列表(包含部门和租户成员)")
    @GetMapping(value = "tenant/org/list")
    public List<OrgNodeDTO> queryOrgNodeList(@RequestParam String wd) {
        return tenantService.queryOrgNodeList(wd);
    }

}
