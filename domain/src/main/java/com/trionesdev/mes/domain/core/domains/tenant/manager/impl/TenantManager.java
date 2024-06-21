package com.trionesdev.mes.domain.core.domains.tenant.manager.impl;

import com.trionesdev.mes.domain.core.domains.tenant.repository.impl.TenantRepository;
import com.trionesdev.mes.domain.core.domains.tenant.repository.po.TenantPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TenantManager {
    private final TenantRepository tenantRepository;

    public void createTenant(TenantPO tenant) {
        tenantRepository.save(tenant);
    }

    public void deleteTenantById(String id) {
        tenantRepository.removeById(id);
    }

    public void updateTenantById(TenantPO tenant) {
        tenantRepository.updateById(tenant);
    }

    public Optional<TenantPO> findTenantById(String id) {
        return Optional.ofNullable(tenantRepository.getById(id));
    }

    public Optional<TenantPO> findTenantBySerial(String serial) {
        return Optional.ofNullable(tenantRepository.selectBySerial(serial));
    }

    public Optional<TenantPO> findFirstTenant() {
        return Optional.ofNullable(tenantRepository.selectFirst());
    }
}
