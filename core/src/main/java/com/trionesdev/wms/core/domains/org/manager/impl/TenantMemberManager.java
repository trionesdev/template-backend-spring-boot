package com.trionesdev.wms.core.domains.org.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.org.dao.po.TenantMemberPO;
import com.trionesdev.wms.core.domains.org.dao.criteria.TenantMemberCriteria;
import com.trionesdev.wms.core.domains.org.dao.impl.TenantDAO;
import com.trionesdev.wms.core.domains.org.dao.impl.TenantMemberDAO;
import com.trionesdev.wms.core.domains.org.dao.po.TenantPO;
import com.trionesdev.wms.core.domains.org.internal.aggreate.entity.TenantMember;
import com.trionesdev.wms.core.domains.org.repository.impl.TenantMemberRepository;
import com.trionesdev.wms.infrastructure.conf.app.AppProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public void updateMemberById(TenantMemberPO tenantMember) {
        tenantMemberDAO.save(tenantMember);
    }

    public Optional<TenantMember> findMemberById(String id) {
        return tenantMemberRepository.findById(id);
    }

    public Optional<TenantMemberPO> findMemberByUserId(String userId) {
        return Optional.ofNullable(tenantMemberDAO.selectByUserId(userId));
    }

    public List<TenantMemberPO> findMembersByIds(Collection<String> ids) {
        return tenantMemberDAO.listByIds(ids);
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

    public Optional<TenantMember> findByAccount(String tenantSerial, String account, String password) {
        String tenantId = null;
        if (BooleanUtils.isTrue(appProperties.getMultiTenant())) {
            tenantId = Optional.ofNullable(tenantDAO.selectBySerial(tenantSerial)).map(TenantPO::getId).orElse(null);
        }
        return findByTenantAccount(tenantId, account, password);
    }

}
