package com.trionesdev.template.core.domains.bossuser.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.template.core.domains.bossuser.dao.criteria.BossUserCriteria;
import com.trionesdev.template.core.domains.bossuser.dao.mapper.BossUserMapper;
import com.trionesdev.template.core.domains.bossuser.dao.po.BossUserPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BossUserDAO extends ServiceImpl<BossUserMapper, BossUserPO> {

    private LambdaQueryWrapper<BossUserPO> buildQueryWrapper(BossUserCriteria criteria) {
        LambdaQueryWrapper<BossUserPO> queryWrapper = new LambdaQueryWrapper<>();
        if (criteria != null) {
        }
        return queryWrapper;
    }

    public BossUserPO selectByUsername(String username) {
        return lambdaQuery().eq(BossUserPO::getUsername, username).one();
    }

    public BossUserPO selectByPhone(String phone) {
        return lambdaQuery().eq(BossUserPO::getPhone, phone).one();
    }

    public List<BossUserPO> selectList(BossUserCriteria criteria) {
        return baseMapper.selectList(buildQueryWrapper(criteria));
    }

    public PageInfo<BossUserPO> selectPage(BossUserCriteria criteria) {
        return MpPageUtils.of(
                baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria))
        );
    }
}
