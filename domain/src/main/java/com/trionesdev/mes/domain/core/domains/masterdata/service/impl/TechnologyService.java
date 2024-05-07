package com.trionesdev.mes.domain.core.domains.masterdata.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.TechnologyCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.po.ManufactureProcessPO;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.po.TechnologicalProcessPO;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.Technology;
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
public class TechnologyService {
    private final TechnologyManager technologyManager;
    private final ManufactureProcessManager manufactureProcessManager;

    private void transformProcess(Technology technology) {
        if (CollectionUtil.isNotEmpty(technology.getProcess())) {
            Set<String> codes = technology.getProcess().stream().map(Technology.Process::getCode).collect(Collectors.toSet());
            Map<String, ManufactureProcessPO> manufactureProcessMap = manufactureProcessManager.findListByCodes(codes).stream().collect(Collectors.toMap(ManufactureProcessPO::getCode, v -> v, (v1, v2) -> v1));
            technology.getProcess().forEach(process -> {
                Optional.ofNullable(manufactureProcessMap.get(process.getCode())).ifPresent(v -> process.setName(v.getName()));
            });
        }
    }

    public void createTechnology(Technology technology) {
        technologyManager.createTechnology(technology);
    }

    public void deleteTechnologyById(String id) {
        technologyManager.deleteTechnologyById(id);
    }

    public void updateTechnologyById(Technology technology) {
        technologyManager.updateTechnologyById(technology);
    }

    public Optional<TechnologyDTO> findTechnologyById(String id) {
        return technologyManager.findTechnologyById(id);
    }

    public List<ManufactureProcessPO> findTechnologyProcesses(String id) {
        List<TechnologicalProcessPO> processes = technologyManager.findProcessByTechnologyId(id);
        if (CollectionUtil.isEmpty(processes)) {
            return Collections.emptyList();
        }
        Set<String> processCodes = processes.stream().map(TechnologicalProcessPO::getProcessCode).collect(Collectors.toSet());
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
