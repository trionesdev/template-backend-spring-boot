package com.trionesdev.mes.domain.core.domains.masterdata.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.ManufactureProcessCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.po.ManufactureProcessPO;
import com.trionesdev.mes.domain.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.ManufactureProcessManager;
import com.trionesdev.mes.domain.core.dto.masterdata.ManufactureProcessDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ManufactureProcessService {
    private final MasterDataBeanConvert masterDataBeanConvert;
    private final ManufactureProcessManager manufactureProcessManager;

    public void create(ManufactureProcessPO manufactureProcess) {
        manufactureProcessManager.create(manufactureProcess);
    }

    public void deleteById(String id) {
        manufactureProcessManager.deleteById(id);
    }

    public void updateById(ManufactureProcessPO manufactureProcess) {
        manufactureProcessManager.updateById(manufactureProcess);
    }

    public Optional<ManufactureProcessDTO> findById(String id) {
        return manufactureProcessManager.findById(id).map(masterDataBeanConvert::entityToDto);
    }

    public List<ManufactureProcessDTO> findList(ManufactureProcessCriteria criteria) {
        List<ManufactureProcessPO> list = manufactureProcessManager.findList(criteria);
        if (CollectionUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        return masterDataBeanConvert.manufactureProcessesEntityToDto(list);
    }

    public PageInfo<ManufactureProcessDTO> findPage(ManufactureProcessCriteria criteria) {
        PageInfo<ManufactureProcessPO> pageInfo = manufactureProcessManager.findPage(criteria);
        return PageUtils.of(pageInfo, masterDataBeanConvert.manufactureProcessesEntityToDto(pageInfo.getRows()));
    }
}
