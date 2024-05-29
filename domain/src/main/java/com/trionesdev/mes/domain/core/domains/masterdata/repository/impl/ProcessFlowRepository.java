package com.trionesdev.mes.domain.core.domains.masterdata.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.ProcessFlowCriteria;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProcessFlowPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.mapper.ProcessFlowMapper;

import java.util.Collection;
import java.util.List;

@Repository
public class ProcessFlowRepository extends ServiceImpl<ProcessFlowMapper, ProcessFlowPO> {
    private LambdaQueryWrapper<ProcessFlowPO> buildQueryWrapper(ProcessFlowCriteria criteria) {
        LambdaQueryWrapper<ProcessFlowPO> queryWrapper = new LambdaQueryWrapper<>();
        return queryWrapper;
    }

    public ProcessFlowPO selectByCode(String code) {
        return baseMapper.selectOne(new LambdaQueryWrapper<ProcessFlowPO>().eq(ProcessFlowPO::getCode, code));
    }

    public List<ProcessFlowPO> selectList(ProcessFlowCriteria criteria) {
        return baseMapper.selectList(buildQueryWrapper(criteria));
    }

    public PageInfo<ProcessFlowPO> selectPage(ProcessFlowCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

    public List<ProcessFlowPO> selectListByCodes(Collection<String> codes) {
        return baseMapper.selectList(new LambdaQueryWrapper<ProcessFlowPO>().in(ProcessFlowPO::getCode, codes));
    }

}
