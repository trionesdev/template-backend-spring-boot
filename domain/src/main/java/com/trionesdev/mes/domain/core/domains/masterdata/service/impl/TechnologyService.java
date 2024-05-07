package com.trionesdev.mes.domain.core.domains.masterdata.service.impl;

import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.TechnologicalProcess;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.Technology;
import com.trionesdev.mes.domain.core.domains.masterdata.manager.impl.TechnologyManager;
import com.trionesdev.mes.domain.core.dto.TechnologyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TechnologyService {
    private final TechnologyManager technologyManager;

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

    public List<TechnologicalProcess> findTechnologyProcesses(String id) {
        return technologyManager.findProcessByTechnologyId(id);
    }

}
