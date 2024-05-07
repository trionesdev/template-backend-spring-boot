package com.trionesdev.mes.domain.core.domains.masterdata.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.TechnologyCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.ManufactureProcess;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.TechnologicalProcess;
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

    private void transformProcess(TechnologyDTO technologyDTO) {
        if (CollectionUtil.isNotEmpty(technologyDTO.getProcessCodes())) {
            Map<String, ManufactureProcess> manufactureProcessMap = manufactureProcessManager.findListByCodes(technologyDTO.getProcessCodes()).stream().collect(Collectors.toMap(ManufactureProcess::getCode, v -> v, (v1, v2) -> v1));
            List<TechnologyDTO.Process> processes = technologyDTO.getProcessCodes().stream().map(manufactureProcessMap::get).map(v -> TechnologyDTO.Process.builder().code(v.getCode()).name(v.getName()).build()).collect(Collectors.toList());
            technologyDTO.setProcesses(processes);
        }
    }

    public void createTechnology(TechnologyDTO technologyDTO) {
        transformProcess(technologyDTO);
        technologyManager.createTechnology(technologyDTO);
    }

    public void deleteTechnologyById(String id) {
        technologyManager.deleteTechnologyById(id);
    }

    public void updateTechnologyById(TechnologyDTO technologyDTO) {
        transformProcess(technologyDTO);
        technologyManager.updateTechnologyById(technologyDTO);
    }

    public Optional<TechnologyDTO> findTechnologyById(String id) {
        return technologyManager.findTechnologyById(id);
    }

    public List<ManufactureProcess> findTechnologyProcesses(String id) {
        List<TechnologicalProcess> processes = technologyManager.findProcessByTechnologyId(id);
        if (CollectionUtil.isEmpty(processes)) {
            return Collections.emptyList();
        }
        Set<String> processCodes = processes.stream().map(TechnologicalProcess::getProcessCode).collect(Collectors.toSet());
        Map<String, ManufactureProcess> processMap = manufactureProcessManager.findListByCodes(processCodes).stream().collect(Collectors.toMap(ManufactureProcess::getCode, p -> p, (p1, p2) -> p1));
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
        Map<String, ManufactureProcess> processMap = manufactureProcessManager.findListByCodes(processCodes).stream().collect(Collectors.toMap(ManufactureProcess::getCode, p -> p, (p1, p2) -> p1));
        return technologies.stream().peek(t -> {
            List<TechnologyDTO.Process> processes = t.getProcessCodes().stream().map(tCode -> Optional.ofNullable(processMap.get(tCode)).map(p -> TechnologyDTO.Process.builder().code(p.getCode()).name(p.getName()).build()).orElse(null)).collect(Collectors.toList());
            t.setProcesses(processes);
        }).collect(Collectors.toList());
    }

}
