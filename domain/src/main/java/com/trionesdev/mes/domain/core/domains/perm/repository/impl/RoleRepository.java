package com.trionesdev.mes.domain.core.domains.perm.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.domain.core.domains.perm.repository.criteria.RoleCriteria;
import com.trionesdev.mes.domain.core.domains.perm.repository.mapper.RoleMapper;
import com.trionesdev.mes.domain.core.domains.perm.repository.po.RolePO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository extends ServiceImpl<RoleMapper, RolePO> {
    private LambdaQueryWrapper<RolePO> buildQueryWrapper(RoleCriteria criteria) {
        LambdaQueryWrapper<RolePO> queryWrapper = new LambdaQueryWrapper<>();
        return queryWrapper;
    }

    public List<RolePO> selectList(RoleCriteria criteria) {
        return list(buildQueryWrapper(criteria));
    }

    public PageInfo<RolePO> selectPage(RoleCriteria criteria) {
        return MpPageUtils.of(
                page(MpPageUtils.page(criteria), buildQueryWrapper(criteria))
        );
    }

}
