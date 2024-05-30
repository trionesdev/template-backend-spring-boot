package com.trionesdev.mes.domain.core.domains.masterdata.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.DefectiveCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.DefectivePO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.mapper.DefectiveMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class DefectiveRepository extends ServiceImpl<DefectiveMapper, DefectivePO> {
    private LambdaQueryWrapper<DefectivePO> buildQueryWrapper(DefectiveCriteria criteria) {
        LambdaQueryWrapper<DefectivePO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), DefectivePO::getCode, criteria.getCode()).eq(StrUtil.isNotBlank(criteria.getName()), DefectivePO::getName, criteria.getName());
        }
        return queryWrapper;
    }

    public List<DefectivePO> selectList(DefectiveCriteria criteria) {
        return this.list(buildQueryWrapper(criteria).orderByDesc(DefectivePO::getCreatedAt));
    }

    public List<DefectivePO> selectListByCodes(Collection<String> codes) {
        return this.list(new LambdaQueryWrapper<DefectivePO>().in(DefectivePO::getCode, codes));
    }


    public PageInfo<DefectivePO> selectPage(DefectiveCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria).orderByDesc(DefectivePO::getCreatedAt)));
    }

}
