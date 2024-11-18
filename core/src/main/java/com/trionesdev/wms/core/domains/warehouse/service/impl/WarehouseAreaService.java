package com.trionesdev.wms.core.domains.warehouse.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.wms.core.domains.base.provider.impl.BaseProvider;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseAreaCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseAreaPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehousePO;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseAreaDTO;
import com.trionesdev.wms.core.domains.warehouse.internal.WarehouseBeanConvert;
import com.trionesdev.wms.core.domains.warehouse.manager.impl.WarehouseAreaManager;
import com.trionesdev.wms.core.domains.warehouse.manager.impl.WarehouseManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WarehouseAreaService {
    private final WarehouseBeanConvert convert;
    private final WarehouseAreaManager warehouseAreaManager;
    private final WarehouseManager warehouseManager;
    private final BaseProvider customProvider;


    public void create(WarehouseAreaPO po) {
        warehouseAreaManager.create(po);
    }

    public void updateById(WarehouseAreaPO po) {
        warehouseAreaManager.updateById(po);
    }

    public Optional<WarehouseAreaDTO> findById(String id) {
        return warehouseAreaManager.findById(id).map(this::assemble);
    }

    public List<WarehouseAreaDTO> findList(WarehouseAreaCriteria criteria) {
        List<WarehouseAreaPO> list = warehouseAreaManager.findList(criteria);
        return assembleBatch(list);
    }

    public PageInfo<WarehouseAreaDTO> findPage(WarehouseAreaCriteria criteria) {
        PageInfo<WarehouseAreaPO> page = warehouseAreaManager.findPage(criteria);
        return PageUtils.of(page, assembleBatch(page.getRows()));
    }

    public void deleteByIds(List<String> ids) {
        warehouseAreaManager.deleteByIds(ids);
    }

    private WarehouseAreaDTO assemble(WarehouseAreaPO po) {
        WarehouseAreaDTO dto = convert.poToDto(po);
        if (StrUtil.isNotBlank(dto.getWarehouseId())) {
            Optional<WarehousePO> warehouseOptional = warehouseManager.findById(dto.getWarehouseId());
            warehouseOptional.ifPresent(warehousePO -> dto.setWarehouseName(warehousePO.getName()));
        }
        return dto;
    }

    public List<WarehouseAreaDTO> assembleBatch(List<WarehouseAreaPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        return records.stream().map(this::assemble).collect(Collectors.toList());
    }
}
