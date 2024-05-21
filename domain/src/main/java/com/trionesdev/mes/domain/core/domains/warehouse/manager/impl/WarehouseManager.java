package com.trionesdev.mes.domain.core.domains.warehouse.manager.impl;

import com.trionesdev.mes.domain.core.domains.warehouse.repository.impl.WarehouseRepository;
import com.trionesdev.mes.domain.core.domains.warehouse.repository.po.WarehousePO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WarehouseManager {
    private final WarehouseRepository warehouseRepository;

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
}
