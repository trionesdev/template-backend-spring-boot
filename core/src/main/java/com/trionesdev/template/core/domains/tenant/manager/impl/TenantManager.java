package com.trionesdev.template.core.domains.tenant.manager.impl;

import com.trionesdev.boot.core.autoconfigure.AppProperties;
import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.template.core.domains.tenant.dao.criteria.TenantCriteria;
import com.trionesdev.template.core.domains.tenant.dao.impl.DepartmentMemberDAO;
import com.trionesdev.template.core.domains.tenant.dao.impl.TenantDAO;
import com.trionesdev.template.core.domains.tenant.dao.po.DepartmentMemberPO;
import com.trionesdev.template.core.domains.tenant.dao.po.TenantPO;
import com.trionesdev.template.core.domains.tenant.internal.aggregate.entity.TenantMember;
import com.trionesdev.template.core.domains.tenant.repository.impl.TenantMemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TenantManager {
    private final AppProperties appProperties;
    private final TenantDAO tenantDAO;
    private final TenantMemberRepository tenantMemberRepository;
    private final DepartmentMemberDAO departmentMemberDAO;

    private void createDefaultDepartmentRelations(TenantPO tenant, TenantMember member) {
        var departmentMember = DepartmentMemberPO.builder()
                .departmentId(IdentityConstants.STRING_ID_ZERO_VALUE)
                .memberId(member.getId())
                .build();
        departmentMemberDAO.save(departmentMember);
    }

    private String nextSerial() {
        String serial = tenantDAO.selectMaxSerial();
        if (Objects.isNull(serial)) {
            return StringUtils.leftPad("1", 8, '0');
        }
        return StringUtils.leftPad(String.valueOf(Integer.parseInt(serial) + 1), 8, '0');
    }

    @Transactional
    public void createTenant(TenantPO tenant, TenantMember member) {
        tenant.setSerial(nextSerial());
        tenantDAO.save(tenant);
        if (Objects.nonNull(member)) {
            member.setTenantId(tenant.getId());
            member.setMaster(true);
            tenantMemberRepository.save(member);
            createDefaultDepartmentRelations(tenant, member);
        }
    }

    public void deleteTenantById(String id) {
        tenantDAO.removeById(id);
    }

    public void updateTenantById(TenantPO tenant) {
        tenant.setSerial(null);
        tenantDAO.updateById(tenant);
    }

    public Optional<TenantPO> findActorTenant(String tenantId) {
        if (appProperties.getMultiTenant()) {
            Objects.requireNonNull(tenantId);
            return Optional.ofNullable(tenantDAO.getById(tenantId));
        } else {
            if (StringUtils.isNotBlank(tenantId)) {
                return Optional.ofNullable(tenantDAO.getById(tenantId));
            } else {
                return Optional.ofNullable(tenantDAO.selectFirst());
            }
        }
    }

    public Optional<TenantPO> findTenantById(String id) {
        return Optional.ofNullable(tenantDAO.getById(id));
    }

    public Optional<TenantPO> findTenantBySerial(String serial) {
        return Optional.ofNullable(tenantDAO.selectBySerial(serial));
    }

    public PageInfo<TenantPO> findTenantPage(TenantCriteria criteria) {
        return tenantDAO.selectPage(criteria);
    }
}
