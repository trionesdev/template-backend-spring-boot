package com.trionesdev.mes.domain.core.domains.masterdata.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.po.TechnologicalProcessPO;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.mapper.TechnologicalProcessMapper;

import java.util.Collection;
import java.util.List;

@Repository
public class TechnologicalProcessDAO extends ServiceImpl<TechnologicalProcessMapper, TechnologicalProcessPO> {

    public void deleteByTechnologicalId(String technologicalId) {
        baseMapper.delete(new LambdaUpdateWrapper<TechnologicalProcessPO>().eq(TechnologicalProcessPO::getTechnologicalId, technologicalId));
    }

    public List<TechnologicalProcessPO> selectListByTechnologicalId(String technologicalId) {
        return baseMapper.selectList(new LambdaQueryWrapper<TechnologicalProcessPO>().eq(TechnologicalProcessPO::getTechnologicalId, technologicalId));
    }

    public List<TechnologicalProcessPO> selectListByTechnologicalIds(Collection<String> technologicalIds) {
        return baseMapper.selectList(new LambdaQueryWrapper<TechnologicalProcessPO>().in(TechnologicalProcessPO::getTechnologicalId, technologicalIds));
    }

}
