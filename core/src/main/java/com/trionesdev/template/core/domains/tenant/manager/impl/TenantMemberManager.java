package com.trionesdev.template.core.domains.tenant.manager.impl;

import com.trionesdev.boot.core.autoconfigure.AppProperties;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.template.core.domains.tenant.dao.criteria.TenantMemberCriteria;
import com.trionesdev.template.core.domains.tenant.dao.impl.TenantDAO;
import com.trionesdev.template.core.domains.tenant.dao.po.TenantPO;
import com.trionesdev.template.core.domains.tenant.internal.aggregate.entity.TenantMember;
import com.trionesdev.template.core.domains.tenant.repository.impl.TenantMemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.trionesdev.template.core.domains.tenant.internal.OrgErrors.TENANT_SERIAL_EMPTY;

@RequiredArgsConstructor
@Service
public class TenantMemberManager {
    private final AppProperties appProperties;

    private final TenantDAO tenantDAO;
    private final TenantMemberRepository tenantMemberRepository;

    public void createMember(TenantMember tenantMember) {
        tenantMemberRepository.save(tenantMember);
    }

    public void updateMemberById(TenantMember tenantMember) {
        tenantMemberRepository.updateById(tenantMember);
    }

    public Optional<TenantMember> findMemberById(String id) {
        return tenantMemberRepository.findById(id);
    }

    public Optional<TenantMember> findMemberByUserId(String userId) {
        return tenantMemberRepository.findByUserId(userId);
    }

    public List<TenantMember> findMembersByIds(Collection<String> ids) {
        return tenantMemberRepository.findListByIds(ids);
    }

    public List<TenantMember> findMembersByUserIds(Collection<String> userIds) {
        return tenantMemberRepository.findListByUserIds(userIds);
    }


    public List<TenantMember> findMembers(TenantMemberCriteria criteria) {
        return tenantMemberRepository.findMemberList(criteria);
    }

    public PageInfo<TenantMember> findMembersPage(TenantMemberCriteria criteria) {
        return tenantMemberRepository.findMemberPage(criteria);
    }

    /**
     * 根据租户下，用户的账户和密码获取用户信息
     *
     * @param tenantId 租户ID
     * @param account  账户，可以是手机号，邮箱，用户名
     * @param password 密码
     * @return 用户信息
     */
    public Optional<TenantMember> findByTenantAccount(String tenantId, String account, String password) {
        var accountType = TenantMember.getAccountType(account);
        Optional<TenantMember> memberSnap = Optional.empty();
        if (accountType == TenantMember.AccountType.PHONE) {
            memberSnap = tenantMemberRepository.findByPhone(tenantId, account);
        } else if (accountType == TenantMember.AccountType.EMAIL) {
            memberSnap = tenantMemberRepository.findByEmail(tenantId, account);
        } else {
            memberSnap = tenantMemberRepository.findByUsername(tenantId, account);
        }
        return memberSnap.filter(member -> member.passwordMatch(password));
    }

    /**
     * 根据租户下，用户的账户和密码获取用户信息
     *
     * @param tenantSerial 租户序列号，如果当前是单租户，则不生效
     * @param account      账户
     * @param password     密码
     * @return 用户信息
     */
    public Optional<TenantMember> findByAccount(String tenantSerial, String account, String password) {
        String tenantId = null;
        if (BooleanUtils.isTrue(appProperties.getMultiTenant())) {
            if (StringUtils.isBlank(tenantSerial)) {
                throw new BusinessException(TENANT_SERIAL_EMPTY);
            }
            tenantId = Optional.ofNullable(tenantDAO.selectBySerial(tenantSerial)).map(TenantPO::getId).orElse(null);
        }
        return findByTenantAccount(tenantId, account, password);
    }

    public List<TenantMember> findMembersByUserId(String userId) {
        return tenantMemberRepository.findMembersByUserId(userId);
    }

    public Optional<TenantMember> findMemberByUserId(String tenantId, String userId) {
        return tenantMemberRepository.findMemberByUserId(tenantId, userId);
    }

    public List<TenantMember> findTenantMastersByTenantIds(List<String> tenantIds) {
        if (CollectionUtils.isEmpty(tenantIds)) {
            return new ArrayList<>();
        }
        return tenantMemberRepository.findMemberList(TenantMemberCriteria.builder().tenantIds(tenantIds).master(true).build());
    }

}
