package com.trionesdev.mes.core.domains.customer.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.core.domains.customer.dao.criteria.CustomerCriteria;
import com.trionesdev.mes.core.domains.customer.dao.mapper.CustomerMapper;
import com.trionesdev.mes.core.domains.customer.dao.po.CustomerPO;

import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO extends ServiceImpl<CustomerMapper, CustomerPO> {

    private LambdaQueryWrapper<CustomerPO> buildQueryWrapper(CustomerCriteria criteria) {
        LambdaQueryWrapper<CustomerPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getSerialNum()), CustomerPO::getSerialNum, criteria.getSerialNum());
        }
        return queryWrapper;
    }

    public List<CustomerPO> selectList(CustomerCriteria criteria) {
        return this.list(buildQueryWrapper(criteria));
    }

    public PageInfo<CustomerPO> selectPage(CustomerCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

}
