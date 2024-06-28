package com.trionesdev.mes.core.domains.org.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.core.domains.org.dao.criteria.TenantCriteria;
import com.trionesdev.mes.core.domains.org.dao.mapper.TenantMapper;
import com.trionesdev.mes.core.domains.org.dao.po.TenantPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TenantDAO extends ServiceImpl<TenantMapper, TenantPO> {
    private LambdaQueryWrapper<TenantPO> buildQueryWrapper(TenantCriteria criteria) {
        var queryWrapper = new LambdaQueryWrapper<TenantPO>();
        return queryWrapper;
    }

    public List<TenantPO> selectList(TenantCriteria criteria) {
        return this.list(buildQueryWrapper(criteria));
    }

    public PageInfo<TenantPO> selectPage(TenantCriteria criteria) {
        return MpPageUtils.of(this.page(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

    public TenantPO selectBySerial(String serial) {
        return lambdaQuery().eq(TenantPO::getSerial, serial).one();
    }

    public TenantPO selectFirst() {
        return lambdaQuery().last(" limit 1 ").one();
    }
}
