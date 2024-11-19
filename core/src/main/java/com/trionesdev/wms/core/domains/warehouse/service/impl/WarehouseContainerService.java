package com.trionesdev.wms.core.domains.warehouse.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseContainerCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseContainerPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehousePO;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseContainerDTO;
import com.trionesdev.wms.core.domains.warehouse.internal.WarehouseBeanConvert;
import com.trionesdev.wms.core.domains.warehouse.manager.impl.WarehouseContainerManager;
import com.trionesdev.wms.core.domains.warehouse.manager.impl.WarehouseManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WarehouseContainerService {
    private final WarehouseBeanConvert convert;
    private final WarehouseContainerManager warehouseContainerManager;
    private final WarehouseManager warehouseManager;

    public void create(WarehouseContainerPO po) {
        warehouseContainerManager.create(po);
    }

    public void updateById(WarehouseContainerPO po) {
        warehouseContainerManager.updateById(po);
    }

    public Optional<WarehouseContainerDTO> findById(String id) {
        return warehouseContainerManager.findById(id).map(this::assemble);
    }

    public List<WarehouseContainerDTO> findList(WarehouseContainerCriteria criteria) {
        List<WarehouseContainerPO> list = warehouseContainerManager.findList(criteria);
        return assembleBatch(list);
    }

    public PageInfo<WarehouseContainerDTO> findPage(WarehouseContainerCriteria criteria) {
        PageInfo<WarehouseContainerPO> page = warehouseContainerManager.findPage(criteria);
        return PageUtils.of(page, assembleBatch(page.getRows()));
    }

    public void deleteByIds(List<String> ids) {
        warehouseContainerManager.deleteByIds(ids);
    }

    private WarehouseContainerDTO assemble(WarehouseContainerPO po) {
        WarehouseContainerDTO dto = convert.poToDto(po);

        if (StrUtil.isNotBlank(dto.getWarehouseId())) {
            Optional<WarehousePO> warehouseOptional = warehouseManager.findById(dto.getWarehouseId());
            warehouseOptional.ifPresent(warehousePO -> dto.setWarehouseName(warehousePO.getName()));
        }

        return dto;
    }

    public List<WarehouseContainerDTO> assembleBatch(List<WarehouseContainerPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        return records.stream().map(this::assemble).collect(Collectors.toList());
    }
}
