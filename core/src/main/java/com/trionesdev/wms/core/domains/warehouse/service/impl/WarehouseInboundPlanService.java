package com.trionesdev.wms.core.domains.warehouse.service.impl;


import com.google.common.collect.Lists;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.good.dto.GoodDTO;
import com.trionesdev.wms.core.domains.good.provider.GoodsProvider;
import com.trionesdev.wms.core.domains.supplier.dto.SupplierDTO;
import com.trionesdev.wms.core.domains.supplier.provider.SupplierProvider;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseInboundPlanCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseInboundPlanItemPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseInboundPlanPO;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseInboundPlanDTO;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseInboundPlanItemDTO;
import com.trionesdev.wms.core.domains.warehouse.internal.WarehouseBeanConvert;
import com.trionesdev.wms.core.domains.warehouse.manager.impl.WarehouseInboundPlanItemManager;
import com.trionesdev.wms.core.domains.warehouse.manager.impl.WarehouseInboundPlanManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WarehouseInboundPlanService {
    private final WarehouseInboundPlanManager warehouseInboundPlanManager;
    private final WarehouseInboundPlanItemManager warehouseInboundPlanItemManager;
    private final WarehouseBeanConvert convert;
    private final SupplierProvider supplierProvider;
    private final GoodsProvider goodsProvider;

    public PageInfo<WarehouseInboundPlanDTO> page(WarehouseInboundPlanCriteria criteria) {
        PageInfo<WarehouseInboundPlanPO> page = warehouseInboundPlanManager.page(criteria);
        List<WarehouseInboundPlanDTO> list = convert.fromInboundPlan(page.getRows());

        Set<String> supplierIds = list.stream()
                .map(WarehouseInboundPlanDTO::getSupplierId)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toSet());
        List<SupplierDTO> supplierDTOS = supplierProvider.findById(Lists.newArrayList(supplierIds));
        Map<String, SupplierDTO> supplierMap = supplierDTOS.stream()
                .collect(Collectors.toMap(SupplierDTO::getId, Function.identity()));

        for (WarehouseInboundPlanDTO dto : list) {
            dto.setSupplier(supplierMap.get(dto.getSupplierId()));
        }

        return MpPageUtils.of(page, list);
    }

    @Transactional
    public void save(WarehouseInboundPlanDTO dto) {
        WarehouseInboundPlanPO entity = convert.from(dto);
        if (StringUtils.isBlank(entity.getSn())) {
            entity.setSn(Math.round(Math.random() * 1000000) + "");
        }
        warehouseInboundPlanManager.save(entity);

        if (CollectionUtils.isNotEmpty(dto.getItems())) {
            List<WarehouseInboundPlanItemPO> items = convert.fromWarehouseInboundPlanItemDTO(dto.getItems());
            items.forEach(item -> {
                item.setId(null);
                item.setWarehouseInboundPlanId(entity.getId());
                item.setSupplierId(dto.getSupplierId());
            });
            warehouseInboundPlanItemManager.saveBatch(items);
        }
    }

    @Transactional
    public void updateById(WarehouseInboundPlanDTO dto) {
        WarehouseInboundPlanPO entity = convert.from(dto);
        warehouseInboundPlanManager.updateById(entity);

        warehouseInboundPlanItemManager.removeByWarehouseInboundPlanId(dto.getId());
        if (CollectionUtils.isNotEmpty(dto.getItems())) {
            List<WarehouseInboundPlanItemPO> items = convert.fromWarehouseInboundPlanItemDTO(dto.getItems());
            items.forEach(item -> {
                item.setId(null);
                item.setWarehouseInboundPlanId(entity.getId());
                item.setSupplierId(dto.getSupplierId());
            });
            warehouseInboundPlanItemManager.saveBatch(items);
        }
    }

    public WarehouseInboundPlanDTO getById(String id) {
        WarehouseInboundPlanPO entity = warehouseInboundPlanManager.getById(id);
        if (entity == null) {
            return null;
        }

        WarehouseInboundPlanDTO res = convert.from(entity);

        List<WarehouseInboundPlanItemPO> items = warehouseInboundPlanItemManager.listByWarehouseInboundPlanId(id);
        List<WarehouseInboundPlanItemDTO> itemDTOS = convert.fromWarehouseInboundPlanItemPO(items);

        Set<String> goodsIds = itemDTOS.stream()
                .map(WarehouseInboundPlanItemDTO::getGoodsId)
                .collect(Collectors.toSet());
        List<GoodDTO> goodDTOS = goodsProvider.listById(Lists.newArrayList(goodsIds));
        Map<String, GoodDTO> goodsMap = goodDTOS.stream()
                .collect(Collectors.toMap(GoodDTO::getId, Function.identity()));

        for (WarehouseInboundPlanItemDTO itemDTO : itemDTOS) {
            itemDTO.setGoods(goodsMap.get(itemDTO.getGoodsId()));
        }

        res.setItems(itemDTOS);

        return res;
    }

    @Transactional
    public boolean removeById(String id) {
        warehouseInboundPlanItemManager.removeByWarehouseInboundPlanId(id);
        warehouseInboundPlanManager.removeById(id);
        return true;
    }

    @Transactional
    public boolean removeById(List<String> ids) {
        warehouseInboundPlanItemManager.removeByWarehouseInboundPlanId(ids);
        warehouseInboundPlanManager.removeById(ids);
        return true;
    }

    public void cancel(String id) {
        warehouseInboundPlanManager.cancel(id);
    }
}