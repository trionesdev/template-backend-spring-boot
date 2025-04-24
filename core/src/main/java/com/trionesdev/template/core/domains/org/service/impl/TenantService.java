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
import com.trionesdev.commons.model.ActorProfile;
import com.trionesdev.template.core.domains.notification.provider.impl.NotificationProvider;
import com.trionesdev.template.core.domains.org.dao.criteria.DepartmentCriteria;
import com.trionesdev.template.core.domains.org.dao.criteria.TenantMemberCriteria;
import com.trionesdev.template.core.domains.org.dao.po.DepartmentMemberPO;
import com.trionesdev.template.core.domains.org.dao.po.TenantPO;
import com.trionesdev.template.core.domains.org.dto.*;
import com.trionesdev.template.core.domains.org.dto.cmd.*;
import com.trionesdev.template.core.domains.org.internal.OrgDomainConvert;
import com.trionesdev.template.core.domains.org.internal.aggreate.entity.TenantMember;
import com.trionesdev.template.core.domains.org.manager.impl.DepartmentManager;
import com.trionesdev.template.core.domains.org.manager.impl.TenantManager;
import com.trionesdev.template.core.domains.org.manager.impl.TenantMemberManager;
import com.trionesdev.template.core.domains.org.shared.enums.OrgNodeType;
import com.trionesdev.template.core.domains.user.dto.UserCreateCmd;
import com.trionesdev.template.core.domains.user.provider.UserProvider;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trionesdev.template.core.domains.org.internal.OrgErrors.*;

@RequiredArgsConstructor
@Service
public class TenantService {
    private final OrgDomainConvert convert;
    private final ActorContext actorContext;
    private final JwtFacade jwtFacade;
    private final AppProperties appProperties;
    private final TenantManager tenantManager;
    private final TenantMemberManager tenantMemberManager;
    private final DepartmentManager departmentManager;
    private final UserProvider userProvider;
    private final NotificationProvider notificationProvider;

    public void createTenantByActor(TenantPO tenant) {
        var user = userProvider.getUserById(actorContext.getUserId());
        if (Objects.isNull(user)) {
            throw new NotFoundException(USER_NOT_FOUND);
        }
        var tenantMember = TenantMember.builder()
                .userId(user.getId())
                .avatar(user.getAvatar())
                .nickname(user.getNickname())
                .username(user.getUsername())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
        tenantManager.createTenant(tenant, tenantMember);
    }

    /**
     * 运营人员创建租户
     *
     * @param cmd
     */
    public void createTenantByBoss(BossCreateTenantCmd cmd) {
        var verifySuccess = notificationProvider.verifySmsValidationCode(cmd.getPhone(), cmd.getValidationCode());
        if (!verifySuccess) {
            throw new BusinessException(VALIDATION_CODE_ERROR);
        }
        var userCreatCmd = UserCreateCmd.builder()
                .phone(cmd.getPhone())
                .email(cmd.getEmail())
                .username(cmd.getUsername())
                .nickname(cmd.getNickname())
                .password(cmd.getPassword())
                .build();
        var user = userProvider.createUserByPhoneOrReturnExist(userCreatCmd);
        var tenant = TenantPO.builder()
                .name(cmd.getName())
                .description(cmd.getDescription())
                .build();
        var tenantMember = TenantMember.builder()
                .userId(user.getId())
                .avatar(user.getAvatar())
                .nickname(user.getNickname())
                .username(user.getUsername())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
        tenantManager.createTenant(tenant, tenantMember);
    }

    public Optional<TenantDTO> findActorTenant() {
        return tenantManager.findActorTenant(actorContext.getTenantId()).map(convert::tenantPoToDto);
    }

    public void updateActorTenant(TenantPO tenant) {
        if (BooleanUtils.isTrue(appProperties.getMultiTenant())) {
            tenant.setId(actorContext.getTenantId());
            tenantManager.updateTenantById(tenant);
        } else {
            tenantManager.findActorTenant(actorContext.getTenantId()).ifPresent(tenantSnap -> {
                tenant.setId(tenantSnap.getId());
                tenantManager.updateTenantById(tenant);
            });
        }
    }

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
        var depMembers = departmentManager.findDepartmentMembersByMemberId(tenantMemberDTO.getMemberId());
        tenantMemberDTO.setDepartmentIds(depMembers.stream().map(DepartmentMemberPO::getDepartmentId).collect(Collectors.toList()));
        return tenantMemberDTO;
    }

    public Optional<TenantMemberDTO> findTenantMemberByMemberId(String memberId) {
        return tenantMemberManager.findMemberById(memberId).map(this::assembleTenantMember);
    }

    public Optional<ActorProfile> findActorProfile() {
        if (Objects.equals(ActorRoleEnum.TENANT_MEMBER.name(), actorContext.getRole())) {
            return tenantMemberManager.findMemberById(actorContext.getMemberId()).map(memberSnap -> {
                return ActorProfile.builder()
                        .role(ActorRoleEnum.TENANT_MEMBER.name())
                        .nickname(memberSnap.getNickname())
                        .userId(actorContext.getUserId())
                        .memberId(actorContext.getMemberId())
                        .tenantId(actorContext.getTenantId())
                        .build();
            });
        } else if (Objects.equals(ActorRoleEnum.USER.name(), actorContext.getRole())) {
            return Optional.ofNullable(userProvider.getUserById(actorContext.getUserId())).map(user -> {
                return ActorProfile.builder()
                        .role(ActorRoleEnum.USER.name())
                        .nickname(user.getNickname())
                        .userId(user.getId())
                        .avatar(user.getAvatar())
                        .tenantId(actorContext.getTenantId())
                        .build();
            });

        }
        return Optional.empty();
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
            tenantMemberSnap.setPassword(cmd.getPassword());
            tenantMemberManager.updateMemberById(tenantMemberSnap);
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
                result.add(OrgNodeDTO.builder().id(t.getId()).name(t.getName()).type(OrgNodeType.DEPARTMENT).build());
            });
        }
        var tenantMembers = tenantMemberManager.findMembers(TenantMemberCriteria.builder().wd(wd).build());
        if (CollectionUtils.isNotEmpty(tenantMembers)) {
            tenantMembers.forEach(t -> {
                var name = t.getName();
                if (StringUtils.isBlank(name)) {
                    name = t.getNickname();
                }
                result.add(OrgNodeDTO.builder().id(t.getId()).name(name).type(OrgNodeType.MEMBER).avatar(t.getAvatar()).nickname(t.getNickname()).build());
            });
        }
        return result;
    }

}
