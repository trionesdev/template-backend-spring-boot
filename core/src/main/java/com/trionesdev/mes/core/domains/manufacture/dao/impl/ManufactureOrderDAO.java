package com.trionesdev.mes.core.domains.manufacture.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.core.domains.manufacture.dao.criteria.ManufactureOrderCriteria;
import com.trionesdev.mes.core.domains.manufacture.dao.mapper.ManufactureOrderMapper;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureOrderPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class ManufactureOrderDAO extends ServiceImpl<ManufactureOrderMapper, ManufactureOrderPO> {
    private LambdaQueryWrapper<ManufactureOrderPO> buildQueryWrapper(ManufactureOrderCriteria criteria) {
        LambdaQueryWrapper<ManufactureOrderPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), ManufactureOrderPO::getCode, criteria.getCode());
        }
        return queryWrapper;
    }

    public ManufactureOrderPO selectByCode(String code) {
        return baseMapper.selectOne(new LambdaQueryWrapper<ManufactureOrderPO>().eq(ManufactureOrderPO::getCode, code).last(" limit 1 "));
    }

    public List<ManufactureOrderPO> selectList(ManufactureOrderCriteria criteria) {
        return this.list(buildQueryWrapper(criteria).orderByDesc(ManufactureOrderPO::getCreatedAt));
    }

    public PageInfo<ManufactureOrderPO> selectPage(ManufactureOrderCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria).orderByDesc(ManufactureOrderPO::getCreatedAt)));
    }
}
