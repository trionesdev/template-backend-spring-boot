package com.trionesdev.wms.core.domains.warehouse.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseInboundPlanItemCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.mapper.WarehouseInboundPlanItemMapper;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseInboundPlanItemPO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WarehouseInboundPlanItemDAO extends ServiceImpl<WarehouseInboundPlanItemMapper, WarehouseInboundPlanItemPO> {

    public PageInfo<WarehouseInboundPlanItemPO> page(WarehouseInboundPlanItemCriteria criteria) {
        LambdaQueryWrapper<WarehouseInboundPlanItemPO> queryWrapper = buildQueryWrapper(criteria);
        IPage<WarehouseInboundPlanItemPO> page = new Page<>(criteria.getPageNum(), criteria.getPageSize());
        return MpPageUtils.of(baseMapper.selectPage(page, queryWrapper));
    }

    private LambdaQueryWrapper<WarehouseInboundPlanItemPO> buildQueryWrapper(WarehouseInboundPlanItemCriteria criteria) {
        LambdaQueryWrapper<WarehouseInboundPlanItemPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StringUtils.isNotBlank(criteria.getId()), WarehouseInboundPlanItemPO::getId, criteria.getId());
        return queryWrapper;
    }

    public boolean removeByWarehouseInboundPlanId(String warehouseInboundPlanId) {
        LambdaQueryWrapper<WarehouseInboundPlanItemPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(WarehouseInboundPlanItemPO::getWarehouseInboundPlanId, warehouseInboundPlanId);
        return remove(queryWrapper);
    }

    public boolean removeByWarehouseInboundPlanId(List<String> warehouseInboundPlanIds) {
        if (CollectionUtils.isEmpty(warehouseInboundPlanIds)) {
            return true;
        }

        LambdaQueryWrapper<WarehouseInboundPlanItemPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(WarehouseInboundPlanItemPO::getWarehouseInboundPlanId, warehouseInboundPlanIds);
        return remove(queryWrapper);
    }

    public List<WarehouseInboundPlanItemPO> listByWarehouseInboundPlanId(String warehouseInboundPlanId) {
        LambdaQueryWrapper<WarehouseInboundPlanItemPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(WarehouseInboundPlanItemPO::getWarehouseInboundPlanId, warehouseInboundPlanId);
        return list(queryWrapper);
    }
}
