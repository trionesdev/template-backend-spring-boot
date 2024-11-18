package com.trionesdev.wms.core.domains.warehouse.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseLocationCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.impl.WarehouseLocationDAO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseLocationPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WarehouseLocationManager {
    private final WarehouseLocationDAO warehouseLocationRepository;

    public void create(WarehouseLocationPO po) {
        warehouseLocationRepository.save(po);
    }

    public void updateById(WarehouseLocationPO po) {
        warehouseLocationRepository.updateById(po);
    }

    public Optional<WarehouseLocationPO> findById(String id) {
        return Optional.ofNullable(warehouseLocationRepository.getById(id));
    }

    public List<WarehouseLocationPO> findList(WarehouseLocationCriteria criteria) {
        return warehouseLocationRepository.selectList(criteria);
    }

    public PageInfo<WarehouseLocationPO> findPage(WarehouseLocationCriteria criteria) {
        return warehouseLocationRepository.selectPage(criteria);
    }

    public void deleteByIds(List<String> ids) {
        warehouseLocationRepository.removeByIds(ids);
    }
}
