package com.trionesdev.mes.domain.core.domains.masterdata.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.TechnologyCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.TechnologicalProcess;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.Technology;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.impl.TechnologicalProcessDAO;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.impl.TechnologyDAO;
import com.trionesdev.mes.domain.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.domain.core.dto.masterdata.TechnologyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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

    public Optional<TechnologyDTO> findTechnologyById(String id) {
        return Optional.ofNullable(technologyDAO.getById(id)).map(t -> {
            TechnologyDTO technologyDTO = masterDataBeanConvert.entityToDto(t);
            List<String> processCodes = technologicalProcessDAO.selectListByTechnologicalId(t.getId()).stream().map(TechnologicalProcess::getProcessCode).collect(Collectors.toList());
            technologyDTO.setProcessCodes(processCodes);
            return technologyDTO;
        });
    }

    private List<TechnologyDTO> transformToDtoBatch(List<Technology> technologies) {
        if (CollectionUtil.isEmpty(technologies)) {
            return Collections.emptyList();
        }
        Map<String, List<String>> technologicalProcessMap = technologicalProcessDAO.selectListByTechnologicalIds(technologies.stream().map(Technology::getId).collect(Collectors.toList()))
                .stream().collect(Collectors.groupingBy(TechnologicalProcess::getTechnologicalId, Collectors.mapping(TechnologicalProcess::getProcessCode, Collectors.toList())));
        return technologies.stream().map(t -> {
            TechnologyDTO technologyDTO = masterDataBeanConvert.entityToDto(t);
            technologyDTO.setProcessCodes(technologicalProcessMap.get(t.getId()));
            return technologyDTO;
        }).collect(Collectors.toList());
    }

    public List<TechnologyDTO> findList(TechnologyCriteria criteria) {
        return transformToDtoBatch(technologyDAO.selectList(criteria));
    }

    public PageInfo<TechnologyDTO> findPage(TechnologyCriteria criteria) {
        PageInfo<Technology> pageInfo = technologyDAO.selectPage(criteria);
        return PageUtils.of(pageInfo, transformToDtoBatch(pageInfo.getRows()));
    }

    public List<TechnologicalProcess> findProcessByTechnologyId(String id) {
        return technologicalProcessDAO.selectListByTechnologicalId(id);
    }

}
