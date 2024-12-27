package com.trionesdev.template.core.domains.user.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.template.core.domains.user.dao.criteria.UserCriteria;
import com.trionesdev.template.core.domains.user.dao.mapper.UserMapper;
import com.trionesdev.template.core.domains.user.dao.po.UserPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class UserDAO extends ServiceImpl<UserMapper, UserPO> {
    private LambdaQueryWrapper<UserPO> buildQueryWrapper(UserCriteria criteria) {
        var queryWrapper = new LambdaQueryWrapper<UserPO>();
        if (Objects.nonNull(criteria)) {
        }
        return queryWrapper;
    }

    public UserPO selectByUsername(String username) {
        return lambdaQuery().eq(UserPO::getUsername, username).one();
    }

    public UserPO selectByPhone(String phone) {
        return lambdaQuery().eq(UserPO::getPhone, phone).one();
    }

    public List<UserPO> selectList(UserCriteria criteria) {
        return this.list(buildQueryWrapper(criteria));
    }

    public PageInfo<UserPO> selectPage(UserCriteria criteria) {
        return MpPageUtils.of(this.page(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

}
