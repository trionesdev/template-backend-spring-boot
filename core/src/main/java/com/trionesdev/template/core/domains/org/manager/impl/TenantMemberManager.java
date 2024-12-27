package com.trionesdev.template.core.domains.org.manager.impl;

import com.trionesdev.boot.core.autoconfigure.AppProperties;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.template.core.domains.org.dao.criteria.TenantMemberCriteria;
import com.trionesdev.template.core.domains.org.dao.impl.TenantDAO;
import com.trionesdev.template.core.domains.org.dao.impl.TenantMemberDAO;
import com.trionesdev.template.core.domains.org.dao.po.TenantMemberPO;
import com.trionesdev.template.core.domains.org.dao.po.TenantPO;
import com.trionesdev.template.core.domains.org.internal.aggreate.entity.TenantMember;
import com.trionesdev.template.core.domains.org.repository.impl.TenantMemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TenantMemberManager {
    private final AppProperties appProperties;
    private final TenantMemberDAO tenantMemberDAO;
    private final TenantDAO tenantDAO;
    private final TenantMemberRepository tenantMemberRepository;

    public void createMember(TenantMember tenantMember) {
        tenantMemberRepository.save(tenantMember);
    }

    public void deleteMemberById(String id) {
        tenantMemberDAO.removeById(id);
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


    public Optional<TenantMemberPO> findMemberByUsername(String tenantId, String username) {
        return Optional.ofNullable(tenantMemberDAO.selectByUsername(tenantId, username));
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
            tenantId = Optional.ofNullable(tenantDAO.selectBySerial(tenantSerial)).map(TenantPO::getId).orElse(null);
        }
        return findByTenantAccount(tenantId, account, password);
    }

}
