package com.trionesdev.wms.core.domains.org.repository.impl;

import com.trionesdev.wms.core.domains.org.dao.impl.TenantMemberDAO;
import com.trionesdev.wms.core.domains.org.dao.po.TenantMemberPO;
import com.trionesdev.wms.core.domains.org.internal.OrgBeanConvert;
import com.trionesdev.wms.core.domains.org.internal.aggreate.entity.TenantMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TenantMemberRepository {
    private final OrgBeanConvert convert;
    private final TenantMemberDAO tenantMemberDAO;

    public void save(TenantMember tenantMember) {
        var tenantMemberPo = convert.memberEntityToPo(tenantMember);
        tenantMemberDAO.save(tenantMemberPo);
    }

    public void deleteById(String id) {
        tenantMemberDAO.removeById(id);
    }

    public void updateById(TenantMember tenantMember) {
        var tenantMemberPo = convert.memberEntityToPo(tenantMember);
        tenantMemberDAO.updateById(tenantMemberPo);
    }

    private TenantMember assembleMember(TenantMemberPO tenantMemberPO) {
        return convert.memberPoToEntity(tenantMemberPO);
    }

    public Optional<TenantMember> findById(String id) {
        return Optional.ofNullable(tenantMemberDAO.getById(id)).map(this::assembleMember);
    }

    public Optional<TenantMember> findByUsername(String tenantId, String username) {
        return Optional.ofNullable(tenantMemberDAO.selectByUsername(tenantId, username)).map(this::assembleMember);
    }

}
