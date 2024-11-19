package com.trionesdev.wms.core.domains.warehouse.dao.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseContainerCriteria;
import com.trionesdev.wms.core.domains.warehouse.dao.mapper.WarehouseContainerMapper;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseContainerPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class WarehouseContainerDAO extends ServiceImpl<WarehouseContainerMapper, WarehouseContainerPO> {
    public LambdaQueryWrapper<WarehouseContainerPO> buildQueryWrapper(WarehouseContainerCriteria criteria) {
        LambdaQueryWrapper<WarehouseContainerPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), WarehouseContainerPO::getCode, criteria.getCode());
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getName()), WarehouseContainerPO::getName, criteria.getName());
            queryWrapper.eq(ObjUtil.isNotNull(criteria.getEnabled()), WarehouseContainerPO::getEnabled, criteria.getEnabled());
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getWarehouseId()), WarehouseContainerPO::getWarehouseId, criteria.getWarehouseId());
        }
        queryWrapper.orderByDesc(WarehouseContainerPO::getCreatedAt);
        return queryWrapper;
    }

    public List<WarehouseContainerPO> selectList(WarehouseContainerCriteria criteria) {
        return this.list(buildQueryWrapper(criteria));
    }

    public PageInfo<WarehouseContainerPO> selectPage(WarehouseContainerCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }
}
