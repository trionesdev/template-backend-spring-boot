package com.trionesdev.wms.core.domains.warehouse.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.impl.WarehouseDAO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehousePO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WarehouseManager {
    private final WarehouseDAO warehouseRepository;

    public void createWarehouse(WarehousePO warehouse) {
        warehouseRepository.save(warehouse);
    }

    public void deleteWarehouseById(String id) {
        warehouseRepository.removeById(id);
    }

    public void updateWarehouseById(WarehousePO warehouse) {
        warehouseRepository.updateById(warehouse);
    }

    public Optional<WarehousePO> findById(String id) {
        return Optional.ofNullable(warehouseRepository.getById(id));
    }

    public List<WarehousePO> findList(WarehouseCriteria criteria) {
        return warehouseRepository.selectList(criteria);
    }

    public PageInfo<WarehousePO> findPage(WarehouseCriteria criteria) {
        return warehouseRepository.selectPage(criteria);
    }

    public void deleteByIds(List<String> ids) {
        warehouseRepository.removeByIds(ids);
    }
}
