package com.trionesdev.wms.core.domains.org.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.org.dao.criteria.DepartmentMemberCriteria;
import com.trionesdev.wms.core.domains.org.dao.mapper.DepartmentMemberMapper;
import com.trionesdev.wms.core.domains.org.dao.po.DepartmentMemberPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class DepartmentMemberDAO extends ServiceImpl<DepartmentMemberMapper, DepartmentMemberPO> {

    private LambdaQueryWrapper<DepartmentMemberPO> buildQueryWrapper(DepartmentMemberCriteria criteria) {
        var queryWrapper = new LambdaQueryWrapper<DepartmentMemberPO>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getDepartmentId()), DepartmentMemberPO::getDepartmentId, criteria.getDepartmentId())
                    .eq(StrUtil.isNotBlank(criteria.getUserId()), DepartmentMemberPO::getUserId, criteria.getUserId());
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

    public void deleteByUserId(String userId) {
        remove(new LambdaQueryWrapper<DepartmentMemberPO>().eq(DepartmentMemberPO::getUserId, userId));
    }

    public List<DepartmentMemberPO> selectListByUserId(String userId) {
        return lambdaQuery().eq(DepartmentMemberPO::getUserId, userId).list();
    }

    public List<DepartmentMemberPO> selectListByDepartmentId(String departmentId) {
        return lambdaQuery().eq(DepartmentMemberPO::getDepartmentId, departmentId).list();
    }

}
