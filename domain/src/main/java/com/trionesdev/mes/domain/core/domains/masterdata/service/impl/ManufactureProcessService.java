package com.trionesdev.mes.domain.core.domains.masterdata.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.DefectiveManager;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.ProcessFlowManager;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.ManufactureProcessCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.DefectivePO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ManufactureProcessPO;
import com.trionesdev.mes.domain.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.ManufactureProcessManager;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProcessFlowItemPO;
import com.trionesdev.mes.domain.core.dto.masterdata.ManufactureProcessDTO;
import com.trionesdev.mes.domain.core.provider.ssp.custom.impl.CustomProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ManufactureProcessService {
    private final MasterDataBeanConvert convert;
    private final ManufactureProcessManager manufactureProcessManager;
    private final ProcessFlowManager processFlowManager;
    private final DefectiveManager defectiveManager;
    private final CustomProvider customProvider;

    public void create(ManufactureProcessPO manufactureProcess) {
        if (StrUtil.isBlank(manufactureProcess.getCode())) {
            manufactureProcess.setCode(customProvider.generateCode("MANUFACTURE_PROCESS"));
        }
        manufactureProcessManager.create(manufactureProcess);
    }

    public void deleteById(String id) {
        manufactureProcessManager.deleteById(id);
    }

    public void updateById(ManufactureProcessPO manufactureProcess) {
        manufactureProcessManager.updateById(manufactureProcess);
    }

    public Optional<ManufactureProcessDTO> findById(String id) {
        return manufactureProcessManager.findById(id).map(po -> {
            ManufactureProcessDTO dto = convert.poToDto(po);
            if (CollectionUtil.isNotEmpty(dto.getDefectives())) {
                dto.setDefectives(defectiveManager.findListByCodes(dto.getDefectiveCodes()).stream().map(convert::poToDto).collect(Collectors.toList()));
            }
            return dto;
        });
    }

    public List<ManufactureProcessDTO> findList(ManufactureProcessCriteria criteria) {
        List<ManufactureProcessPO> list = manufactureProcessManager.findList(criteria);
        if (CollectionUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        return assembleBatch(list);
    }

    public PageInfo<ManufactureProcessDTO> findPage(ManufactureProcessCriteria criteria) {
        PageInfo<ManufactureProcessPO> pageInfo = manufactureProcessManager.findPage(criteria);
        return PageUtils.of(pageInfo, assembleBatch(pageInfo.getRows()));
    }

    public List<ManufactureProcessDTO> findProcessByCodes(Collection<String> codes) {
        if (CollectionUtil.isEmpty(codes)) {
            return Collections.emptyList();
        }
        List<ManufactureProcessPO> processes = manufactureProcessManager.findListByCodes(codes);
        return assembleBatch(processes);
    }

    private List<ManufactureProcessDTO> assembleBatch(List<ManufactureProcessPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        Set<String> defectiveCodes = records.stream().map(ManufactureProcessPO::getDefectiveCodes)
                .flatMap(codes -> {
                    if (CollectionUtil.isEmpty(codes)) {
                        return new ArrayList<String>().stream();
                    }
                    return codes.stream();
                }).collect(Collectors.toSet());
        var defectivesMap = defectiveManager.findListByCodes(defectiveCodes).stream().collect(Collectors.toMap(DefectivePO::getCode, v -> v, (v1, v2) -> v1));
        return records.stream().map(po -> {
            var dto = convert.poToDto(po);
            if (CollectionUtil.isNotEmpty(dto.getDefectiveCodes())) {
                dto.setDefectives(dto.getDefectiveCodes().stream().map(defectivesMap::get).map(convert::poToDto).collect(Collectors.toList()));
            }
            return dto;
        }).collect(Collectors.toList());
    }

    public List<ManufactureProcessDTO> findFlowsProcesses(List<String> flowIds) {
        List<ProcessFlowItemPO> flowItems = processFlowManager.findFlowItemsByFlowIds(flowIds);
        if (CollectionUtil.isEmpty(flowItems)) {
            return Collections.emptyList();
        }
        Set<String> processCodes = flowItems.stream().map(ProcessFlowItemPO::getCode).collect(Collectors.toSet());
        var processes = assembleBatch(manufactureProcessManager.findListByCodes(processCodes));
        var processesMap = processes.stream().collect(Collectors.toMap(ManufactureProcessDTO::getCode, v -> v, (v1, v2) -> v1));
        return flowItems.stream().map(item -> processesMap.get(item.getCode())).collect(Collectors.toList());
    }

}
