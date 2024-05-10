package com.trionesdev.mes.domain.core.domains.masterdata.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.TechnologyCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProcessFlowItemPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProcessFlowPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.impl.ProcessFlowItemRepository;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.impl.ProcessFlowRepository;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.ProcessFlow;
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
    private final ProcessFlowRepository technologyDAO;
    private final ProcessFlowItemRepository technologicalProcessDAO;

    @Transactional
    public void createTechnology(ProcessFlow technology) {
        ProcessFlowPO technologyEntity = masterDataBeanConvert.entityToPo(technology);
        technologyDAO.save(technologyEntity);
        if (CollectionUtil.isNotEmpty(technology.getProcess())) {
            List<ProcessFlowItemPO> technologicalProcesses = technology.getProcess().stream().map(process -> ProcessFlowItemPO.builder().technologicalId(technologyEntity.getId())
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
    public void updateTechnologyById(ProcessFlow technology) {
        Objects.requireNonNull(technology.getId(), "id can not be null");
        ProcessFlowPO technologyEntity = masterDataBeanConvert.entityToPo(technology);
        technologyDAO.updateById(technologyEntity);
        technologicalProcessDAO.deleteByTechnologicalId(technologyEntity.getId());
        if (CollectionUtil.isNotEmpty(technology.getProcess())) {
            List<ProcessFlowItemPO> technologicalProcesses = technology.getProcess().stream().map(process -> ProcessFlowItemPO.builder().technologicalId(technologyEntity.getId())
                    .processCode(process.getCode())
                    .build()).collect(Collectors.toList());
            technologicalProcessDAO.saveBatch(technologicalProcesses);
        }
    }

    public Optional<TechnologyDTO> findTechnologyById(String id) {
        return Optional.ofNullable(technologyDAO.getById(id)).map(t -> {
            TechnologyDTO technologyDTO = masterDataBeanConvert.entityToDto(t);
            List<String> processCodes = technologicalProcessDAO.selectListByTechnologicalId(t.getId()).stream().map(ProcessFlowItemPO::getProcessCode).collect(Collectors.toList());
            technologyDTO.setProcessCodes(processCodes);
            return technologyDTO;
        });
    }

    private List<TechnologyDTO> transformToDtoBatch(List<ProcessFlowPO> technologies) {
        if (CollectionUtil.isEmpty(technologies)) {
            return Collections.emptyList();
        }
        Map<String, List<String>> technologicalProcessMap = technologicalProcessDAO.selectListByTechnologicalIds(technologies.stream().map(ProcessFlowPO::getId).collect(Collectors.toList()))
                .stream().collect(Collectors.groupingBy(ProcessFlowItemPO::getTechnologicalId, Collectors.mapping(ProcessFlowItemPO::getProcessCode, Collectors.toList())));
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
        PageInfo<ProcessFlowPO> pageInfo = technologyDAO.selectPage(criteria);
        return PageUtils.of(pageInfo, transformToDtoBatch(pageInfo.getRows()));
    }

    public List<ProcessFlowItemPO> findProcessByTechnologyId(String id) {
        return technologicalProcessDAO.selectListByTechnologicalId(id);
    }

}
