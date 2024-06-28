package com.trionesdev.mes.core.domains.masterdata.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.core.domains.masterdata.dao.po.ProcessFlowPO;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.ProcessFlowCriteria;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.core.domains.masterdata.dao.mapper.ProcessFlowMapper;

import java.util.Collection;
import java.util.List;

@Repository
public class ProcessFlowDAO extends ServiceImpl<ProcessFlowMapper, ProcessFlowPO> {
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
