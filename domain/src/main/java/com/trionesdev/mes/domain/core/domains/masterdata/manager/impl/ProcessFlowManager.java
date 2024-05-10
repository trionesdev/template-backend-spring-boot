package com.trionesdev.mes.domain.core.domains.masterdata.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Sets;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.ProcessFlow;
import com.trionesdev.mes.domain.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.ProcessFlowCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.impl.ProcessFlowItemRepository;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.impl.ProcessFlowRepository;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProcessFlowItemPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProcessFlowPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProcessFlowManager {
    private final MasterDataBeanConvert masterDataBeanConvert;
    private final ProcessFlowRepository processFlowRepository;
    private final ProcessFlowItemRepository processFlowItemRepository;

    @Transactional
    public void createProcessFlow(ProcessFlow processFlow) {
        ProcessFlowPO processFlowPO = masterDataBeanConvert.entityToPo(processFlow);
        processFlowRepository.save(processFlowPO);
        if (CollectionUtil.isNotEmpty(processFlow.getItems())) {
            List<ProcessFlowItemPO> technologicalProcesses = processFlow.getItems().stream().map(item -> ProcessFlowItemPO.builder().flowId(processFlowPO.getId())
                    .type(item.getType())
                    .code(item.getCode())
                    .ratio(item.getRatio())
                    .build()).collect(Collectors.toList());
            processFlowItemRepository.saveBatch(technologicalProcesses);
        }
    }

    @Transactional
    public void deleteProcessFlowById(String id) {
        processFlowRepository.removeById(id);
        processFlowItemRepository.deleteByFlowId(id);
    }

    @Transactional
    public void updateProcessFlowById(ProcessFlow processFlow) {
        Objects.requireNonNull(processFlow.getId(), "id can not be null");
        ProcessFlowPO processFlowPO = masterDataBeanConvert.entityToPo(processFlow);
        processFlowRepository.updateById(processFlowPO);
        processFlowItemRepository.deleteByFlowId(processFlowPO.getId());
        if (CollectionUtil.isNotEmpty(processFlow.getItems())) {
            List<ProcessFlowItemPO> items = processFlow.getItems().stream().map(item -> ProcessFlowItemPO.builder().flowId(processFlowPO.getId())
                    .type(item.getType())
                    .code(item.getCode())
                    .ratio(item.getRatio())
                    .build()).collect(Collectors.toList());
            processFlowItemRepository.saveBatch(items);
        }
    }

    public Optional<ProcessFlow> findById(String id) {
        return Optional.ofNullable(processFlowRepository.getById(id)).map(processFlowPO -> {
            ProcessFlow processFlow = masterDataBeanConvert.poToEntity(processFlowPO);
            List<ProcessFlow.Item> items = processFlowItemRepository.selectListByFlowId(id).stream().map(item -> ProcessFlow.Item.builder().code(item.getCode()).type(item.getType()).ratio(item.getRatio()).build()).collect(Collectors.toList());
            processFlow.setItems(items);
            return processFlow;
        });
    }

    public List<ProcessFlow> findProcessFlowList(ProcessFlowCriteria criteria) {
        List<ProcessFlowPO> records = processFlowRepository.selectList(criteria);
        return assembleProcessFlow(records);
    }

    public PageInfo<ProcessFlow> findProcessFlowPage(ProcessFlowCriteria criteria) {
        PageInfo<ProcessFlowPO> pageInfo = processFlowRepository.selectPage(criteria);
        return PageUtils.of(pageInfo, assembleProcessFlow(pageInfo.getRows()));
    }

    public List<ProcessFlowPO> findRecordsByCodes(Collection<String> codes) {
        return processFlowRepository.selectListByCodes(codes);
    }

    private List<ProcessFlow> assembleProcessFlow(List<ProcessFlowPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        Set<String> flowIds = records.stream().map(ProcessFlowPO::getId).collect(Collectors.toSet());
        Map<String, List<ProcessFlowItemPO>> processFlowItemsMap = processFlowItemRepository.selectListByFlowIds(flowIds).stream()
                .collect(Collectors.groupingBy(ProcessFlowItemPO::getFlowId));
        return records.stream().map(processFlowPO -> {
            ProcessFlow processFlow = masterDataBeanConvert.poToEntity(processFlowPO);
            processFlow.setItems(processFlowItemsMap.getOrDefault(processFlowPO.getId(), Collections.emptyList()).stream().map(item -> ProcessFlow.Item.builder().code(item.getCode()).type(item.getType()).ratio(item.getRatio()).build()).collect(Collectors.toList()));
            return processFlow;
        }).collect(Collectors.toList());
    }

}
