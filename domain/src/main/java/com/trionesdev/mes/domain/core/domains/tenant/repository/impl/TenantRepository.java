package com.trionesdev.mes.domain.core.domains.tenant.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.domain.core.domains.tenant.repository.criteria.TenantCriteria;
import com.trionesdev.mes.domain.core.domains.tenant.repository.mapper.TenantMapper;
import com.trionesdev.mes.domain.core.domains.tenant.repository.po.TenantPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TenantRepository extends ServiceImpl<TenantMapper, TenantPO> {
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

}
