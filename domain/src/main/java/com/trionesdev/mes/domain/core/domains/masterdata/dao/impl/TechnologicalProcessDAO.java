package com.trionesdev.mes.domain.core.domains.masterdata.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.TechnologicalProcess;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.mapper.TechnologicalProcessMapper;

import java.util.Collection;
import java.util.List;

@Repository
public class TechnologicalProcessDAO extends ServiceImpl<TechnologicalProcessMapper, TechnologicalProcess> {

    public void deleteByTechnologicalId(String technologicalId) {
        baseMapper.delete(new LambdaUpdateWrapper<TechnologicalProcess>().eq(TechnologicalProcess::getTechnologicalId, technologicalId));
    }

    public List<TechnologicalProcess> selectListByTechnologicalId(String technologicalId) {
        return baseMapper.selectList(new LambdaQueryWrapper<TechnologicalProcess>().eq(TechnologicalProcess::getTechnologicalId, technologicalId));
    }

    public List<TechnologicalProcess> selectListByTechnologicalIds(Collection<String> technologicalIds) {
        return baseMapper.selectList(new LambdaQueryWrapper<TechnologicalProcess>().in(TechnologicalProcess::getTechnologicalId, technologicalIds));
    }

}
