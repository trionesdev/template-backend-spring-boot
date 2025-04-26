package com.trionesdev.template.core.domains.boss.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.template.core.domains.boss.dao.criteria.BossDepartmentMemberCriteria;
import com.trionesdev.template.core.domains.boss.dao.mapper.BossDepartmentMemberMapper;
import com.trionesdev.template.core.domains.boss.dao.po.BossDepartmentMemberPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class BossDepartmentMemberDAO extends ServiceImpl<BossDepartmentMemberMapper, BossDepartmentMemberPO> {

    private LambdaQueryWrapper<BossDepartmentMemberPO> buildQueryWrapper(BossDepartmentMemberCriteria criteria) {
        var queryWrapper = new LambdaQueryWrapper<BossDepartmentMemberPO>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getDepartmentId()), BossDepartmentMemberPO::getDepartmentId, criteria.getDepartmentId())
                    .eq(StrUtil.isNotBlank(criteria.getUserId()), BossDepartmentMemberPO::getUserId, criteria.getUserId());
        }
        return queryWrapper;
    }

    public List<BossDepartmentMemberPO> selectList(BossDepartmentMemberCriteria criteria) {
        return list(buildQueryWrapper(criteria));
    }

    public PageInfo<BossDepartmentMemberPO> selectPage(BossDepartmentMemberCriteria criteria) {
        return MpPageUtils.of(
                page(MpPageUtils.page(criteria), buildQueryWrapper(criteria))
        );
    }

    public void deleteByUserId(String userId) {
        remove(new LambdaQueryWrapper<BossDepartmentMemberPO>().eq(BossDepartmentMemberPO::getUserId, userId));
    }

    public List<BossDepartmentMemberPO> selectListByUserId(String memberId) {
        return lambdaQuery().eq(BossDepartmentMemberPO::getUserId, memberId).list();
    }

    public List<BossDepartmentMemberPO> selectListByDepartmentId(String departmentId) {
        return lambdaQuery().eq(BossDepartmentMemberPO::getDepartmentId, departmentId).list();
    }

}
