package com.trionesdev.template.core.domains.boss.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.template.core.domains.boss.dao.criteria.BossRoleCriteria;
import com.trionesdev.template.core.domains.boss.dao.mapper.BossRoleMapper;
import com.trionesdev.template.core.domains.boss.dao.po.BossRolePO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BossRoleDAO extends ServiceImpl<BossRoleMapper, BossRolePO> {
    private LambdaQueryWrapper<BossRolePO> buildQueryWrapper(BossRoleCriteria criteria) {
        LambdaQueryWrapper<BossRolePO> queryWrapper = new LambdaQueryWrapper<>();
        return queryWrapper;
    }

    public List<BossRolePO> selectList(BossRoleCriteria criteria) {
        return list(buildQueryWrapper(criteria));
    }

    public PageInfo<BossRolePO> selectPage(BossRoleCriteria criteria) {
        return MpPageUtils.of(
                baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria))
        );
    }

    public List<BossRolePO> selectListByParent(String parentId) {
        return list(new LambdaQueryWrapper<BossRolePO>().eq(BossRolePO::getParentId, parentId));
    }
}
