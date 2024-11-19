package com.trionesdev.wms.core.domains.customer.dao.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.customer.dao.criteria.CustomerCriteria;
import com.trionesdev.wms.core.domains.customer.dao.mapper.CustomerMapper;
import com.trionesdev.wms.core.domains.customer.dao.po.CustomerPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class CustomerDAO extends ServiceImpl<CustomerMapper, CustomerPO> {
    public LambdaQueryWrapper<CustomerPO> buildQueryWrapper(CustomerCriteria criteria) {
        LambdaQueryWrapper<CustomerPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), CustomerPO::getCode, criteria.getCode());
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getName()), CustomerPO::getName, criteria.getName());
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getContactName()), CustomerPO::getContactName, criteria.getContactName());
            queryWrapper.eq(ObjUtil.isNotNull(criteria.getEnabled()), CustomerPO::getEnabled, criteria.getEnabled());
        }
        queryWrapper.orderByDesc(CustomerPO::getCreatedAt);
        return queryWrapper;
    }

    public List<CustomerPO> selectList(CustomerCriteria criteria) {
        return this.list(buildQueryWrapper(criteria));
    }

    public PageInfo<CustomerPO> selectPage(CustomerCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }
}
