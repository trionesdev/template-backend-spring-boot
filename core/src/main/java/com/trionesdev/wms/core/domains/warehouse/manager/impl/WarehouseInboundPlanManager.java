package com.trionesdev.wms.core.domains.warehouse.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseInboundPlanCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.impl.WarehouseInboundPlanDAO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseInboundPlanPO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@CacheConfig(cacheNames = {"WarehouseInboundPlanManager"})
@RequiredArgsConstructor
@Component
public class WarehouseInboundPlanManager {
    private final WarehouseInboundPlanDAO warehouseInboundPlanDAO;

    public PageInfo<WarehouseInboundPlanPO> page(WarehouseInboundPlanCriteria criteria) {
        return warehouseInboundPlanDAO.page(criteria);
    }

    @CacheEvict(allEntries = true)
    public boolean save(WarehouseInboundPlanPO entity) {
        return warehouseInboundPlanDAO.save(entity);
    }

    @CacheEvict(allEntries = true)
    public boolean updateById(WarehouseInboundPlanPO entity) {
        return warehouseInboundPlanDAO.updateById(entity);
    }

    @Cacheable
    public WarehouseInboundPlanPO getById(String id) {
        return warehouseInboundPlanDAO.getById(id);
    }

    @CacheEvict(allEntries = true)
    public boolean removeById(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return true;
        }

        return warehouseInboundPlanDAO.removeByIds(ids);
    }

    @CacheEvict(allEntries = true)
    public boolean removeById(String id) {
        return warehouseInboundPlanDAO.removeById(id);
    }

    public void cancel(String id) {
        warehouseInboundPlanDAO.cancel(id);
    }
}