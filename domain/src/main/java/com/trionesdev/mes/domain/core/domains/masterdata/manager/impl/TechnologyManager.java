package com.trionesdev.mes.domain.core.domains.masterdata.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.TechnologicalProcess;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.Technology;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.impl.TechnologicalProcessDAO;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.impl.TechnologyDAO;
import com.trionesdev.mes.domain.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.domain.core.dto.TechnologyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TechnologyManager {
    private final MasterDataBeanConvert masterDataBeanConvert;
    private final TechnologyDAO technologyDAO;
    private final TechnologicalProcessDAO technologicalProcessDAO;

    @Transactional
    public void createTechnology(TechnologyDTO technology) {
        Technology technologyEntity = masterDataBeanConvert.dtoToEntity(technology);
        technologyDAO.save(technologyEntity);
        if (CollectionUtil.isNotEmpty(technology.getProcesses())) {
            List<TechnologicalProcess> technologicalProcesses = technology.getProcesses().stream().map(process -> TechnologicalProcess.builder().technologicalId(technologyEntity.getId())
                    .processCode(process.getCode())
                    .build()).collect(Collectors.toList());
            technologicalProcessDAO.saveBatch(technologicalProcesses);
        }
    }

    @Transactional
    public void deleteTechnologyById(String id) {
        technologyDAO.removeById(id);
        technologicalProcessDAO.deleteByTechnologicalId(id);
    }

    @Transactional
    public void updateTechnologyById(TechnologyDTO technology) {
        Objects.requireNonNull(technology.getId(), "id can not be null");
        Technology technologyEntity = masterDataBeanConvert.dtoToEntity(technology);
        technologyDAO.updateById(technologyEntity);
        technologicalProcessDAO.deleteByTechnologicalId(technologyEntity.getId());
        if (CollectionUtil.isNotEmpty(technology.getProcesses())) {
            List<TechnologicalProcess> technologicalProcesses = technology.getProcesses().stream().map(process -> TechnologicalProcess.builder().technologicalId(technologyEntity.getId())
                    .processCode(process.getCode())
                    .build()).collect(Collectors.toList());
            technologicalProcessDAO.saveBatch(technologicalProcesses);
        }
    }

    public Optional<Technology> findTechnologyById(String id) {
        return Optional.ofNullable(technologyDAO.getById(id));
    }

    public List<TechnologicalProcess> findProcessByTechnologyId(String id) {
        return technologicalProcessDAO.selectListByTechnologicalId(id);
    }

}
