package com.trionesdev.wms.core.domains.warehouse.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseInboundPlanCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.mapper.WarehouseInboundPlanMapper;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseInboundPlanPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseInboundPlanPO.InboundPlanStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
public class WarehouseInboundPlanDAO extends ServiceImpl<WarehouseInboundPlanMapper, WarehouseInboundPlanPO> {

    public PageInfo<WarehouseInboundPlanPO> page(WarehouseInboundPlanCriteria criteria) {
        LambdaQueryWrapper<WarehouseInboundPlanPO> queryWrapper = buildQueryWrapper(criteria);
        IPage<WarehouseInboundPlanPO> page = new Page<>(criteria.getPageNum(), criteria.getPageSize());
        return MpPageUtils.of(baseMapper.selectPage(page, queryWrapper));
    }

    private LambdaQueryWrapper<WarehouseInboundPlanPO> buildQueryWrapper(WarehouseInboundPlanCriteria criteria) {
        LambdaQueryWrapper<WarehouseInboundPlanPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(StringUtils.isNotBlank(criteria.getSn()), WarehouseInboundPlanPO::getSn, criteria.getSn());
        return queryWrapper;
    }

    public void cancel(String id) {
        LambdaUpdateWrapper<WarehouseInboundPlanPO> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(WarehouseInboundPlanPO::getId, id);
        updateWrapper.set(WarehouseInboundPlanPO::getStatus, InboundPlanStatusEnum.CANCELLED);
        update(updateWrapper);
    }
}
