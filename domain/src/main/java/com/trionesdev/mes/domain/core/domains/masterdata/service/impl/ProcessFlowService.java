package com.trionesdev.mes.domain.core.domains.masterdata.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.TechnologyCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ManufactureProcessPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProcessFlowItemPO;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.ProcessFlow;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.ManufactureProcessManager;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.TechnologyManager;
import com.trionesdev.mes.domain.core.dto.masterdata.TechnologyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProcessFlowService {
    private final TechnologyManager technologyManager;
    private final ManufactureProcessManager manufactureProcessManager;

    public void createTechnology(ProcessFlow technology) {
        technologyManager.createTechnology(technology);
    }

    public void deleteTechnologyById(String id) {
        technologyManager.deleteTechnologyById(id);
    }

    public void updateTechnologyById(ProcessFlow technology) {
        technologyManager.updateTechnologyById(technology);
    }

    public Optional<TechnologyDTO> findTechnologyById(String id) {
        return technologyManager.findTechnologyById(id);
    }

    public List<ManufactureProcessPO> findTechnologyProcesses(String id) {
        List<ProcessFlowItemPO> processes = technologyManager.findProcessByTechnologyId(id);
        if (CollectionUtil.isEmpty(processes)) {
            return Collections.emptyList();
        }
        Set<String> processCodes = processes.stream().map(ProcessFlowItemPO::getProcessCode).collect(Collectors.toSet());
        Map<String, ManufactureProcessPO> processMap = manufactureProcessManager.findListByCodes(processCodes).stream().collect(Collectors.toMap(ManufactureProcessPO::getCode, p -> p, (p1, p2) -> p1));
        return processes.stream().map(p -> processMap.get(p.getProcessCode())).collect(Collectors.toList());
    }

    public PageInfo<TechnologyDTO> findTechnologyPage(TechnologyCriteria criteria) {
        PageInfo<TechnologyDTO> technologies = technologyManager.findPage(criteria);
        return PageUtils.of(technologies, assembleTechnologyDTOBatch(technologies.getRows()));
    }

    private List<TechnologyDTO> assembleTechnologyDTOBatch(List<TechnologyDTO> technologies) {
        if (CollectionUtil.isEmpty(technologies)) {
            return Collections.emptyList();
        }
        Set<String> processCodes = technologies.stream().map(TechnologyDTO::getProcessCodes).flatMap(List::stream).collect(Collectors.toSet());
        Map<String, ManufactureProcessPO> processMap = manufactureProcessManager.findListByCodes(processCodes).stream().collect(Collectors.toMap(ManufactureProcessPO::getCode, p -> p, (p1, p2) -> p1));
        return technologies.stream().peek(t -> {
            List<TechnologyDTO.Process> processes = t.getProcessCodes().stream().map(tCode -> Optional.ofNullable(processMap.get(tCode)).map(p -> TechnologyDTO.Process.builder().code(p.getCode()).name(p.getName()).build()).orElse(null)).collect(Collectors.toList());
            t.setProcesses(processes);
        }).collect(Collectors.toList());
    }

}
