package com.trionesdev.wms.core.domains.warehouse.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseLocationCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseAreaPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseLocationPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehousePO;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseLocationDTO;
import com.trionesdev.wms.core.domains.warehouse.internal.WarehouseBeanConvert;
import com.trionesdev.wms.core.domains.warehouse.manager.impl.WarehouseAreaManager;
import com.trionesdev.wms.core.domains.warehouse.manager.impl.WarehouseLocationManager;
import com.trionesdev.wms.core.domains.warehouse.manager.impl.WarehouseManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WarehouseLocationService {
    private final WarehouseBeanConvert convert;
    private final WarehouseLocationManager warehouseLocationManager;
    private final WarehouseAreaManager warehouseAreaManager;
    private final WarehouseManager warehouseManager;

    public void create(WarehouseLocationPO po) {
        warehouseLocationManager.create(po);
    }

    public void updateById(WarehouseLocationPO po) {
        warehouseLocationManager.updateById(po);
    }

    public Optional<WarehouseLocationDTO> findById(String id) {
        return warehouseLocationManager.findById(id).map(this::assemble);
    }

    public List<WarehouseLocationDTO> findList(WarehouseLocationCriteria criteria) {
        List<WarehouseLocationPO> list = warehouseLocationManager.findList(criteria);
        return assembleBatch(list);
    }

    public PageInfo<WarehouseLocationDTO> findPage(WarehouseLocationCriteria criteria) {
        PageInfo<WarehouseLocationPO> page = warehouseLocationManager.findPage(criteria);
        return PageUtils.of(page, assembleBatch(page.getRows()));
    }

    public void deleteByIds(List<String> ids) {
        warehouseLocationManager.deleteByIds(ids);
    }

    private WarehouseLocationDTO assemble(WarehouseLocationPO po) {
        WarehouseLocationDTO dto = convert.poToDto(po);

        if (StrUtil.isNotBlank(dto.getWarehouseId())) {
            Optional<WarehousePO> warehouseOptional = warehouseManager.findById(dto.getWarehouseId());
            warehouseOptional.ifPresent(warehousePO -> dto.setWarehouseName(warehousePO.getName()));
        }

        if (StrUtil.isNotBlank(dto.getWarehouseAreaId())) {
            Optional<WarehouseAreaPO> warehouseAreaOptional = warehouseAreaManager.findById(dto.getWarehouseAreaId());
            warehouseAreaOptional.ifPresent(warehouseAreaPO -> dto.setWarehouseAreaName(warehouseAreaPO.getName()));
        }
        return dto;
    }

    public List<WarehouseLocationDTO> assembleBatch(List<WarehouseLocationPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        return records.stream().map(this::assemble).collect(Collectors.toList());
    }
}
