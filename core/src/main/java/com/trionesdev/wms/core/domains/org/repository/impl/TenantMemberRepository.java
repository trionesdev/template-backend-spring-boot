package com.trionesdev.wms.core.domains.org.repository.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.wms.core.domains.org.dao.criteria.TenantMemberCriteria;
import com.trionesdev.wms.core.domains.org.dao.impl.TenantMemberDAO;
import com.trionesdev.wms.core.domains.org.dao.po.TenantMemberPO;
import com.trionesdev.wms.core.domains.org.internal.OrgDomainConvert;
import com.trionesdev.wms.core.domains.org.internal.aggreate.entity.TenantMember;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TenantMemberRepository {
    private final OrgDomainConvert convert;
    private final TenantMemberDAO tenantMemberDAO;

    public void save(TenantMember tenantMember) {
        var tenantMemberPo = convert.memberEntityToPo(tenantMember);
        tenantMemberDAO.save(tenantMemberPo);
        tenantMember.setId(tenantMemberPo.getId());
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

    public Optional<TenantMember> findByPhone(String tenantId, String username) {
        return Optional.ofNullable(tenantMemberDAO.selectByPhone(tenantId, username)).map(this::assembleMember);
    }

    public Optional<TenantMember> findByEmail(String tenantId, String email) {
        return Optional.ofNullable(tenantMemberDAO.selectByEmail(tenantId, email)).map(this::assembleMember);
    }

    private List<TenantMember> assembleMembers(List<TenantMemberPO> records) {
        if (CollectionUtils.isEmpty(records)) {
            return new ArrayList<>();
        }
        return records.stream().map(tenantMemberPO -> {
            return convert.memberPoToEntity(tenantMemberPO);
        }).toList();
    }

    public List<TenantMember> findMemberList(TenantMemberCriteria criteria) {
        var members = tenantMemberDAO.selectList(criteria);
        return assembleMembers(members);
    }

    public PageInfo<TenantMember> findMemberPage(TenantMemberCriteria criteria) {
        var pageInfo = tenantMemberDAO.selectPage(criteria);
        return PageUtils.of(pageInfo, assembleMembers(pageInfo.getRows()));
    }

}
