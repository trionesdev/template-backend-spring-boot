package com.trionesdev.template.core.domains.org.service.impl;

import cn.hutool.core.util.IdUtil;
import com.trionesdev.boot.core.autoconfigure.AppProperties;
import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.context.actor.ActorRoleEnum;
import com.trionesdev.commons.core.jwt.JwtClaims;
import com.trionesdev.commons.core.jwt.JwtFacade;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.commons.exception.NotFoundException;
import com.trionesdev.template.core.domains.org.dao.criteria.DepartmentCriteria;
import com.trionesdev.template.core.domains.org.dao.criteria.TenantMemberCriteria;
import com.trionesdev.template.core.domains.org.dao.po.DepartmentMemberPO;
import com.trionesdev.template.core.domains.org.dto.ActorChangePasswordCmd;
import com.trionesdev.template.core.domains.org.dto.ChangePasswordCmd;
import com.trionesdev.template.core.domains.org.dto.OrgNodeDTO;
import com.trionesdev.template.core.domains.org.dto.TenantMemberCreateCmd;
import com.trionesdev.template.core.domains.org.dto.TenantMemberDTO;
import com.trionesdev.template.core.domains.org.dto.TenantMemberProfileUpdateCmd;
import com.trionesdev.template.core.domains.org.dto.TenantMemberSignInCmd;
import com.trionesdev.template.core.domains.org.dto.TenantMemberUpdateCmd;
import com.trionesdev.template.core.domains.org.internal.OrgDomainConvert;
import com.trionesdev.template.core.domains.org.internal.aggreate.entity.TenantMember;
import com.trionesdev.template.core.domains.org.manager.impl.DepartmentManager;
import com.trionesdev.template.core.domains.org.manager.impl.TenantMemberManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trionesdev.template.core.domains.org.internal.OrgError.PWD_ERROR;
import static com.trionesdev.template.core.domains.org.internal.OrgError.TENANT_ACCOUNT_OR_PWS_ERROR;

@RequiredArgsConstructor
@Service
public class TenantService {
    private final OrgDomainConvert convert;
    private final ActorContext actorContext;
    private final JwtFacade jwtFacade;
    private final AppProperties appProperties;
    private final TenantMemberManager tenantMemberManager;
    private final DepartmentManager departmentManager;

    @Transactional
    public void createMember(TenantMemberCreateCmd cmd) {
        var tenantMember = convert.memberCreateCmdToEntity(cmd);
        if (appProperties.getMultiTenant()) {
            Objects.requireNonNull(tenantMember.getUserId());
        } else {
            tenantMember.setUserId(IdUtil.getSnowflakeNextIdStr());
        }
        tenantMemberManager.createMember(tenantMember);
        departmentManager.setMemberDepartments(tenantMember, tenantMember.getDepartmentIds());
    }

    public void updateMemberById(TenantMemberUpdateCmd cmd) {
        var tenantMember = convert.memberUpdateCmdToEntity(cmd);
        tenantMemberManager.updateMemberById(tenantMember);
    }

    @Transactional
    public void updateMemberProfileById(TenantMemberProfileUpdateCmd cmd) {
        var tenantMember = convert.memberProfileUpdateCmdToEntity(cmd);
        tenantMemberManager.findMemberById(tenantMember.getId()).ifPresent(tenantMemberSnap -> {
            tenantMemberManager.updateMemberById(tenantMember);
            departmentManager.setMemberDepartments(tenantMemberSnap, tenantMember.getDepartmentIds());
        });
    }

    private TenantMemberDTO assembleTenantMember(TenantMember tenantMember) {
        var tenantMemberDTO = convert.memberEntityToDTO(tenantMember);
        var depMembers = departmentManager.findDepartmentMembersByUserId(tenantMemberDTO.getUserId());
        tenantMemberDTO.setDepartmentIds(depMembers.stream().map(DepartmentMemberPO::getDepartmentId).collect(Collectors.toList()));
        return tenantMemberDTO;
    }

    public Optional<TenantMemberDTO> findTenantMemberByMemberId(String memberId) {
        return tenantMemberManager.findMemberById(memberId).map(this::assembleTenantMember);
    }


    private List<TenantMemberDTO> assembleTenantMembers(List<TenantMember> members) {
        return members.stream().map(member -> {
            return convert.memberEntityToDTO(member);
        }).collect(Collectors.toList());
    }

    public List<TenantMemberDTO> findTenantMembers(TenantMemberCriteria criteria) {
        return assembleTenantMembers(tenantMemberManager.findMembers(criteria));
    }

    public PageInfo<TenantMemberDTO> findTenantMembersPage(TenantMemberCriteria criteria) {
        var page = tenantMemberManager.findMembersPage(criteria);
        return PageUtils.of(page, assembleTenantMembers(page.getRows()));
    }


    public String accountSignIn(TenantMemberSignInCmd cmd) {
        return tenantMemberManager.findByAccount(cmd.getTenantSerial(), cmd.getAccount(), cmd.getPassword()).map(tenantMember -> {
            return jwtFacade.generate(tenantMember.getUserId(), JwtClaims.builder().role(ActorRoleEnum.TENANT_MEMBER.name()).tenantId(tenantMember.getTenantId()).tenantMemberId(tenantMember.getId()).build());
        }).orElseThrow(() -> new NotFoundException(TENANT_ACCOUNT_OR_PWS_ERROR));
    }

    public void changeActorPassword(ActorChangePasswordCmd cmd) {
        tenantMemberManager.findMemberByUserId(actorContext.getUserId()).ifPresent(tenantMemberSnap -> {
            if (!tenantMemberSnap.passwordMatch(cmd.getOldPassword())) {
                throw new BusinessException(PWD_ERROR);
            }
            var tenantMember = TenantMember.builder().id(tenantMemberSnap.getId()).password(cmd.getNewPassword()).build();
            tenantMemberManager.updateMemberById(tenantMember);
        });
    }

    public void changePassword(ChangePasswordCmd cmd) {
        tenantMemberManager.findMemberById(cmd.getId()).ifPresent(tenantMemberSnap -> {
            var tenantMember = TenantMember.builder().id(cmd.getId()).password(cmd.getPassword()).build();
            tenantMemberManager.updateMemberById(tenantMember);
        });
    }

    public List<OrgNodeDTO> queryOrgNodeList(String wd) {
        List<OrgNodeDTO> result = new ArrayList<>();
        var departments = departmentManager.findDepartments(DepartmentCriteria.builder().name(wd).build());
        if (CollectionUtils.isNotEmpty(departments)) {
            departments.forEach(t -> {
                result.add(OrgNodeDTO.builder().id(t.getId()).name(t.getName()).type(OrgNodeDTO.Type.DEPARTMENT).build());
            });
        }
        var tenantMembers = tenantMemberManager.findMembers(TenantMemberCriteria.builder().wd(wd).build());
        if (CollectionUtils.isNotEmpty(tenantMembers)) {
            tenantMembers.forEach(t -> {
                var name = t.getName();
                if (StringUtils.isBlank(name)) {
                    name = t.getNickname();
                }
                result.add(OrgNodeDTO.builder().id(t.getId()).name(name).type(OrgNodeDTO.Type.MEMBER).avatar(t.getAvatar()).nickname(t.getNickname()).build());
            });
        }
        return result;
    }

}
