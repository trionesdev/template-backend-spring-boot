package com.trionesdev.mes.domain.core.domains.masterdata.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProcessFlowItemPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.mapper.ProcessFlowItemMapper;

import java.util.Collection;
import java.util.List;

@Repository
public class ProcessFlowItemRepository extends ServiceImpl<ProcessFlowItemMapper, ProcessFlowItemPO> {

    public void deleteByFlowId(String flowId) {
        baseMapper.delete(new LambdaUpdateWrapper<ProcessFlowItemPO>().eq(ProcessFlowItemPO::getFlowId, flowId));
    }

    public List<ProcessFlowItemPO> selectListByFlowId(String flowId) {
        return baseMapper.selectList(new LambdaQueryWrapper<ProcessFlowItemPO>().eq(ProcessFlowItemPO::getFlowId, flowId));
    }

    public List<ProcessFlowItemPO> selectListByFlowIds(Collection<String> flowIds) {
        return baseMapper.selectList(new LambdaQueryWrapper<ProcessFlowItemPO>().in(ProcessFlowItemPO::getFlowId, flowIds));
    }

}
