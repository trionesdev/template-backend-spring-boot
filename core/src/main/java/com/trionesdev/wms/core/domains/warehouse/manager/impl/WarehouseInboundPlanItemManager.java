package com.trionesdev.wms.core.domains.warehouse.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseInboundPlanItemCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.impl.WarehouseInboundPlanItemDAO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseInboundPlanItemPO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@CacheConfig(cacheNames = {"WarehouseInboundPlanItemManager"})
@RequiredArgsConstructor
@Component
public class WarehouseInboundPlanItemManager {
    private final WarehouseInboundPlanItemDAO warehouseInboundPlanItemDAO;

    public PageInfo<WarehouseInboundPlanItemPO> page(WarehouseInboundPlanItemCriteria criteria) {
        return warehouseInboundPlanItemDAO.page(criteria);
    }

    public List<WarehouseInboundPlanItemPO> listByWarehouseInboundPlanId(String warehouseInboundPlanId) {
        return warehouseInboundPlanItemDAO.listByWarehouseInboundPlanId(warehouseInboundPlanId);
    }

    @CacheEvict(allEntries = true)
    public boolean save(WarehouseInboundPlanItemPO entity) {
        return warehouseInboundPlanItemDAO.save(entity);
    }

    @CacheEvict(allEntries = true)
    public boolean updateById(WarehouseInboundPlanItemPO entity) {
        return warehouseInboundPlanItemDAO.updateById(entity);
    }

    @Cacheable
    public WarehouseInboundPlanItemPO getById(String id) {
        return warehouseInboundPlanItemDAO.getById(id);
    }

    @CacheEvict(allEntries = true)
    public boolean removeById(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return true;
        }

        return warehouseInboundPlanItemDAO.removeByIds(ids);
    }

    @CacheEvict(allEntries = true)
    public boolean removeById(String id) {
        return warehouseInboundPlanItemDAO.removeById(id);
    }

    @CacheEvict(allEntries = true)
    public boolean removeByWarehouseInboundPlanId(String warehouseInboundPlanId) {
        return warehouseInboundPlanItemDAO.removeByWarehouseInboundPlanId(warehouseInboundPlanId);
    }

    @CacheEvict(allEntries = true)
    public boolean removeByWarehouseInboundPlanId(List<String> warehouseInboundPlanIds) {
        return warehouseInboundPlanItemDAO.removeByWarehouseInboundPlanId(warehouseInboundPlanIds);
    }

    @CacheEvict(allEntries = true)
    public boolean saveBatch(List<WarehouseInboundPlanItemPO> entities) {
        return warehouseInboundPlanItemDAO.saveBatch(entities);
    }
}