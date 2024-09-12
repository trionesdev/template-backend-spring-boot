package com.trionesdev.wms.core.domains.org.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.org.dao.po.TenantMemberPO;
import com.trionesdev.wms.core.domains.org.dao.criteria.TenantMemberCriteria;
import com.trionesdev.wms.core.domains.org.dao.impl.TenantDAO;
import com.trionesdev.wms.core.domains.org.dao.impl.TenantMemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TenantMemberManager {
    private final TenantMemberDAO tenantMemberDAO;
    private final TenantDAO tenantDAO;

    public void createMember(TenantMemberPO tenantMember) {
        tenantMemberDAO.save(tenantMember);
    }

    public void deleteMemberById(String id) {
        tenantMemberDAO.removeById(id);
    }

    public void updateMemberById(TenantMemberPO tenantMember) {
        tenantMemberDAO.save(tenantMember);
    }

    public Optional<TenantMemberPO> findMemberById(String id) {
        return Optional.ofNullable(tenantMemberDAO.getById(id));
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

    public List<TenantMemberPO> findMembers(TenantMemberCriteria criteria) {
        return tenantMemberDAO.selectList(criteria);
    }

    public PageInfo<TenantMemberPO> findMembersPage(TenantMemberCriteria criteria) {
        return tenantMemberDAO.selectPage(criteria);
    }

    public Optional<TenantMemberPO> accountSignIn(String tenantSerial, String username, String password) {
        return Optional.ofNullable(tenantDAO.selectBySerial(tenantSerial)).flatMap(tenant ->
                Optional.ofNullable(tenantMemberDAO.selectByUsername(tenant.getId(), username))
                        .filter(member -> new BCryptPasswordEncoder().matches(password, member.getEncodedPassword())));
    }

}
