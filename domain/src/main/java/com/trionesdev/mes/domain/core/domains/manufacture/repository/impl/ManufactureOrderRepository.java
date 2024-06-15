package com.trionesdev.mes.domain.core.domains.manufacture.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.criteria.ManufactureOrderCriteria;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.mapper.ManufactureOrderMapper;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderPO;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderTaskPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class ManufactureOrderRepository extends ServiceImpl<ManufactureOrderMapper, ManufactureOrderPO> {
    private LambdaQueryWrapper<ManufactureOrderPO> buildQueryWrapper(ManufactureOrderCriteria criteria) {
        LambdaQueryWrapper<ManufactureOrderPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), ManufactureOrderPO::getCode, criteria.getCode());
        }
        return queryWrapper;
    }

    public List<ManufactureOrderPO> selectList(ManufactureOrderCriteria criteria) {
        return this.list(buildQueryWrapper(criteria).orderByDesc(ManufactureOrderPO::getCreatedAt));
    }

    public PageInfo<ManufactureOrderPO> selectPage(ManufactureOrderCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria).orderByDesc(ManufactureOrderPO::getCreatedAt)));
    }
}
