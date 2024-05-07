package com.trionesdev.mes.domain.core.domains.masterdata.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.ManufactureProcess;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.TechnologicalProcess;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.Technology;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.ManufactureProcessManager;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.TechnologyManager;
import com.trionesdev.mes.domain.core.dto.TechnologyDTO;
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

    public void createTechnology(TechnologyDTO technologyDTO) {
        technologyManager.createTechnology(technologyDTO);
    }

    public void deleteTechnologyById(String id) {
        technologyManager.deleteTechnologyById(id);
    }

    public void updateTechnologyById(TechnologyDTO technologyDTO) {
        technologyManager.updateTechnologyById(technologyDTO);
    }

    public Optional<Technology> findTechnologyById(String id) {
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


}
