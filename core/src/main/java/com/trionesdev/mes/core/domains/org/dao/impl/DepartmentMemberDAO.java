package com.trionesdev.mes.core.domains.org.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.core.domains.org.dao.criteria.DepartmentMemberCriteria;
import com.trionesdev.mes.core.domains.org.dao.mapper.DepartmentMemberMapper;
import com.trionesdev.mes.core.domains.org.dao.po.DepartmentMemberPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class DepartmentMemberDAO extends ServiceImpl<DepartmentMemberMapper, DepartmentMemberPO> {

    private LambdaQueryWrapper<DepartmentMemberPO> buildQueryWrapper(DepartmentMemberCriteria criteria) {
        var queryWrapper = new LambdaQueryWrapper<DepartmentMemberPO>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getDepartmentId()), DepartmentMemberPO::getDepartmentId, criteria.getDepartmentId())
                    .eq(StrUtil.isNotBlank(criteria.getMemberId()), DepartmentMemberPO::getMemberId, criteria.getMemberId());
        }
        return queryWrapper;
    }

    public List<DepartmentMemberPO> selectList(DepartmentMemberCriteria criteria) {
        return list(buildQueryWrapper(criteria));
    }

    public PageInfo<DepartmentMemberPO> selectPage(DepartmentMemberCriteria criteria) {
        return MpPageUtils.of(
                page(MpPageUtils.page(criteria), buildQueryWrapper(criteria))
        );
    }

    public void deleteByMemberId(String memberId) {
        remove(new LambdaQueryWrapper<DepartmentMemberPO>().eq(DepartmentMemberPO::getMemberId, memberId));
    }

    public List<DepartmentMemberPO> selectListByMemberId(String memberId) {
        return lambdaQuery().eq(DepartmentMemberPO::getMemberId, memberId).list();
    }

    public List<DepartmentMemberPO> selectListByDepartmentId(String departmentId) {
        return lambdaQuery().eq(DepartmentMemberPO::getDepartmentId, departmentId).list();
    }

}
