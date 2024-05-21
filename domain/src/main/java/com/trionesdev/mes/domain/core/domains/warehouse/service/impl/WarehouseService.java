package com.trionesdev.mes.domain.core.domains.warehouse.service.impl;

import cn.hutool.core.util.StrUtil;
import com.trionesdev.mes.domain.core.domains.warehouse.manager.impl.WarehouseManager;
import com.trionesdev.mes.domain.core.domains.warehouse.repository.po.WarehousePO;
import com.trionesdev.mes.domain.core.provider.ssp.custom.impl.CustomProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WarehouseService {
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

    public Optional<WarehousePO> findById(String id) {
        return warehouseManager.findById(id);
    }

}
