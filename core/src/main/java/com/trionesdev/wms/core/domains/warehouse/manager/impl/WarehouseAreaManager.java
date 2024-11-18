package com.trionesdev.wms.core.domains.warehouse.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseAreaCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.impl.WarehouseAreaDAO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseAreaPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WarehouseAreaManager {
    private final WarehouseAreaDAO warehouseAreaRepository;

    public void create(WarehouseAreaPO po) {
        warehouseAreaRepository.save(po);
    }

    public void updateById(WarehouseAreaPO po) {
        warehouseAreaRepository.updateById(po);
    }

    public Optional<WarehouseAreaPO> findById(String id) {
        return Optional.ofNullable(warehouseAreaRepository.getById(id));
    }

    public List<WarehouseAreaPO> findList(WarehouseAreaCriteria criteria) {
        return warehouseAreaRepository.selectList(criteria);
    }

    public PageInfo<WarehouseAreaPO> findPage(WarehouseAreaCriteria criteria) {
        return warehouseAreaRepository.selectPage(criteria);
    }

    public void deleteByIds(List<String> ids) {
        warehouseAreaRepository.removeByIds(ids);
    }
}
