package com.trionesdev.mes.domain.core.domains.masterdata.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.ManufactureProcess;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.mapper.ManufactureProcessMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class ManufactureProcessDAO extends ServiceImpl<ManufactureProcessMapper, ManufactureProcess> {

    public List<ManufactureProcess> selectListByCodes(Collection<String> codes) {
        return baseMapper.selectList(new LambdaQueryWrapper<ManufactureProcess>().in(ManufactureProcess::getCode, codes));
    }

}
