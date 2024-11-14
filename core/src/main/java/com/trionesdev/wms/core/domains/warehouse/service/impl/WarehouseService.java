package com.trionesdev.wms.core.domains.warehouse.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.wms.core.domains.format.provider.impl.CustomProvider;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehousePO;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseDTO;
import com.trionesdev.wms.core.domains.warehouse.internal.WarehouseBeanConvert;
import com.trionesdev.wms.core.domains.warehouse.manager.impl.WarehouseManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WarehouseService {
    private final WarehouseBeanConvert convert;
    private final WarehouseManager warehouseManager;
    private final CustomProvider customProvider;

    public void createWarehouse(WarehousePO warehouse) {
        if (StrUtil.isBlank(warehouse.getCode())) {
            warehouse.setCode(customProvider.generateCode("WAREHOUSE"));
        }
        warehouseManager.createWarehouse(warehouse);
    }

    public void deleteWarehouseById(String id) {
        warehouseManager.deleteWarehouseById(id);
    }

    public void updateWarehouseById(WarehousePO warehouse) {
        warehouse.setCode(null);
        warehouseManager.updateWarehouseById(warehouse);
    }

    public Optional<WarehouseDTO> findById(String id) {
        return warehouseManager.findById(id).map(this::assemble);
    }

    public List<WarehouseDTO> findList(WarehouseCriteria criteria) {
        List<WarehousePO> list = warehouseManager.findList(criteria);
        return assembleBatch(list);
    }

    public PageInfo<WarehouseDTO> findPage(WarehouseCriteria criteria) {
        PageInfo<WarehousePO> page = warehouseManager.findPage(criteria);
        return PageUtils.of(page, assembleBatch(page.getRows()));
    }


    private WarehouseDTO assemble(WarehousePO warehousePO) {
        return convert.poToDto(warehousePO);
    }

    public List<WarehouseDTO> assembleBatch(List<WarehousePO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        return records.stream().map(this::assemble).collect(Collectors.toList());
    }

    public void deleteByIds(List<String> ids) {
        warehouseManager.deleteByIds(ids);
    }
}
