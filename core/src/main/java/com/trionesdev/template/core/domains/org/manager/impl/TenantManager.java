package com.trionesdev.template.core.domains.org.manager.impl;

import com.trionesdev.boot.core.autoconfigure.AppProperties;
import com.trionesdev.template.core.domains.org.dao.impl.TenantDAO;
import com.trionesdev.template.core.domains.org.dao.po.TenantPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TenantManager {
    private final AppProperties appProperties;
    private final TenantDAO tenantDAO;

    public void createTenant(TenantPO tenant) {
        tenantDAO.save(tenant);
    }

    public void deleteTenantById(String id) {
        tenantDAO.removeById(id);
    }

    public void updateTenantById(TenantPO tenant) {
        tenantDAO.updateById(tenant);
    }

    public Optional<TenantPO> findActorTenant(String tenantId) {
        if (appProperties.getMultiTenant()) {
            return Optional.ofNullable(tenantDAO.getById(tenantId));
        } else {
            return Optional.ofNullable(tenantDAO.selectFirst());
        }
    }

    public Optional<TenantPO> findTenantById(String id) {
        return Optional.ofNullable(tenantDAO.getById(id));
    }

    public Optional<TenantPO> findTenantBySerial(String serial) {
        return Optional.ofNullable(tenantDAO.selectBySerial(serial));
    }

    public Optional<TenantPO> findFirstTenant() {
        return Optional.ofNullable(tenantDAO.selectFirst());
    }
}
