package com.trionesdev.wms.core.domains.warehouse.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseContainerCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.impl.WarehouseContainerDAO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseContainerPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WarehouseContainerManager {
    private final WarehouseContainerDAO warehouseContainerRepository;

    public void create(WarehouseContainerPO po) {
        warehouseContainerRepository.save(po);
    }

    public void updateById(WarehouseContainerPO po) {
        warehouseContainerRepository.updateById(po);
    }

    public Optional<WarehouseContainerPO> findById(String id) {
        return Optional.ofNullable(warehouseContainerRepository.getById(id));
    }

    public List<WarehouseContainerPO> findList(WarehouseContainerCriteria criteria) {
        return warehouseContainerRepository.selectList(criteria);
    }

    public PageInfo<WarehouseContainerPO> findPage(WarehouseContainerCriteria criteria) {
        return warehouseContainerRepository.selectPage(criteria);
    }

    public void deleteByIds(List<String> ids) {
        warehouseContainerRepository.removeByIds(ids);
    }
}
