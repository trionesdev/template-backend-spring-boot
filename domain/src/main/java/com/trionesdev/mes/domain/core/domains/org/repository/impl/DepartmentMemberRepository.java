package com.trionesdev.mes.domain.core.domains.org.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.domain.core.domains.org.repository.criteria.DepartmentMemberCriteria;
import com.trionesdev.mes.domain.core.domains.org.repository.mapper.DepartmentMemberMapper;
import com.trionesdev.mes.domain.core.domains.org.repository.po.DepartmentMemberPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class DepartmentMemberRepository extends ServiceImpl<DepartmentMemberMapper, DepartmentMemberPO> {

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

}
