package com.trionesdev.mes.domain.core.domains.warehouse.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.domain.core.domains.warehouse.repository.criteria.WarehouseCriteria;
import com.trionesdev.mes.domain.core.domains.warehouse.repository.mapper.WarehouseMapper;
import com.trionesdev.mes.domain.core.domains.warehouse.repository.po.WarehousePO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class WarehouseRepository extends ServiceImpl<WarehouseMapper, WarehousePO> {
    public LambdaQueryWrapper<WarehousePO> buildQueryWrapper(WarehouseCriteria criteria) {
        LambdaQueryWrapper<WarehousePO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), WarehousePO::getCode, criteria.getCode());
        }
        return queryWrapper;
    }

    public List<WarehousePO> selectList(WarehouseCriteria criteria) {
        return this.list(buildQueryWrapper(criteria));
    }

    public PageInfo<WarehousePO> selectPage(WarehouseCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

}
