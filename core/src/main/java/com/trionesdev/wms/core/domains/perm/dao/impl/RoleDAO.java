package com.trionesdev.wms.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.perm.dao.criteria.RoleCriteria;
import com.trionesdev.wms.core.domains.perm.dao.po.RolePO;
import com.trionesdev.wms.core.domains.perm.dao.mapper.RoleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAO extends ServiceImpl<RoleMapper, RolePO> {
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
