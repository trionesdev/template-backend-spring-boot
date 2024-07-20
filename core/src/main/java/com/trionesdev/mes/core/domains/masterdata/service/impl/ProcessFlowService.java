package com.trionesdev.mes.core.domains.masterdata.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.core.domains.custom.provider.impl.CustomProvider;
import com.trionesdev.mes.core.domains.masterdata.dao.po.ProcessFlowPO;
import com.trionesdev.mes.core.domains.masterdata.manager.impl.ManufactureProcessManager;
import com.trionesdev.mes.core.domains.masterdata.internal.aggreate.entity.ProcessFlow;
import com.trionesdev.mes.core.domains.masterdata.internal.aggreate.entity.ProcessFlow.Item;
import com.trionesdev.mes.core.domains.masterdata.internal.aggreate.entity.ProcessFlow.ItemType;
import com.trionesdev.mes.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.core.domains.masterdata.manager.impl.ProcessFlowManager;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.ProcessFlowCriteria;
import com.trionesdev.mes.core.domains.masterdata.dao.po.ManufactureProcessPO;
import com.trionesdev.mes.core.domains.masterdata.dto.ProcessFlowDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
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
    private final MasterDataBeanConvert masterDataBeanConvert;
    private final ProcessFlowManager processFlowManager;
    private final ManufactureProcessManager manufactureProcessManager;
    private final CustomProvider customProvider;

    public void createProcessFlow(ProcessFlow processFlow) {
        if (StrUtil.isBlank(processFlow.getCode())) {
            processFlow.setCode(customProvider.generateCode("PROCESS_FLOW"));
        }
        processFlowManager.createProcessFlow(processFlow);
    }

    public void deleteProcessFlowById(String id) {
        processFlowManager.deleteProcessFlowById(id);
    }

    public void updateProcessFlowById(ProcessFlow technology) {
        processFlowManager.updateProcessFlowById(technology);
    }

    public Optional<ProcessFlowDTO> findProcessFlowById(String id) {
        return processFlowManager.findById(id).map(processFlow -> {
            Set<String> processCodes = processFlow.getItems().stream().filter(item -> item.getType() == ItemType.PROCESS).map(Item::getCode).collect(Collectors.toSet());
            Set<String> flowCodes = processFlow.getItems().stream().filter(item -> item.getType() == ItemType.FLOW).map(Item::getCode).collect(Collectors.toSet());

            ProcessFlowDTO processFlowDTO = masterDataBeanConvert.entityToDto(processFlow);
            if (CollectionUtil.isNotEmpty(processCodes)) {
                Map<String, ManufactureProcessPO> processMap = manufactureProcessManager.findListByCodes(processCodes).stream().collect(Collectors.toMap(ManufactureProcessPO::getCode, process -> process));
                processFlowDTO.getItems().stream().filter(item -> item.getType() == ItemType.PROCESS).forEach(item -> {
                    item.setName(Optional.ofNullable(processMap.get(item.getCode())).map(ManufactureProcessPO::getName).orElse(null));
                });
            }
            if (CollectionUtil.isNotEmpty(flowCodes)) {
                Map<String, ProcessFlowPO> flowMap = processFlowManager.findRecordsByCodes(flowCodes).stream().collect(Collectors.toMap(ProcessFlowPO::getCode, process -> process));
                processFlowDTO.getItems().stream().filter(item -> item.getType() == ItemType.FLOW).forEach(item -> {
                    item.setName(Optional.ofNullable(flowMap.get(item.getCode())).map(ProcessFlowPO::getName).orElse(null));
                });
            }
            return processFlowDTO;
        });
    }

    public List<ProcessFlowDTO> findProcessFlowList(ProcessFlowCriteria criteria) {
        List<ProcessFlow> processFlows = processFlowManager.findProcessFlowList(criteria);
        return assembleProcessFlowDtoBatch(processFlows);
    }

    public PageInfo<ProcessFlowDTO> findProcessFlowPage(ProcessFlowCriteria criteria) {
        PageInfo<ProcessFlow> pageInfo = processFlowManager.findProcessFlowPage(criteria);
        return PageUtils.of(pageInfo, assembleProcessFlowDtoBatch(pageInfo.getRows()));
    }

    private List<ProcessFlowDTO> assembleProcessFlowDtoBatch(List<ProcessFlow> processFlows) {
        if (CollectionUtil.isEmpty(processFlows)) {
            return Collections.emptyList();
        }
        Set<String> processCodes = processFlows.stream().flatMap(processFlow -> processFlow.getItems().stream().filter(item -> item.getType() == ItemType.PROCESS).map(Item::getCode)).collect(Collectors.toSet());
        Set<String> flowCodes = processFlows.stream().flatMap(processFlow -> processFlow.getItems().stream().filter(item -> item.getType() == ItemType.FLOW).map(Item::getCode)).collect(Collectors.toSet());
        Map<String, ManufactureProcessPO> processMap = Maps.newHashMap();

        if (CollectionUtil.isNotEmpty(processCodes)) {
            processMap = manufactureProcessManager.findListByCodes(processCodes).stream().collect(Collectors.toMap(ManufactureProcessPO::getCode, process -> process));
        }
        Map<String, ProcessFlowPO> flowMap = Maps.newHashMap();
        if (CollectionUtil.isNotEmpty(flowCodes)) {
            flowMap = processFlowManager.findRecordsByCodes(flowCodes).stream().collect(Collectors.toMap(ProcessFlowPO::getCode, process -> process));
        }
        Map<String, ManufactureProcessPO> finalProcessMap = processMap;
        Map<String, ProcessFlowPO> finalFlowMap = flowMap;
        return processFlows.stream().map(processFlow -> {
            ProcessFlowDTO processFlowDTO = masterDataBeanConvert.entityToDto(processFlow);
            if (MapUtils.isNotEmpty(finalProcessMap)) {
                processFlowDTO.getItems().forEach(item -> {
                    if (item.getType() == ItemType.PROCESS) {
                        item.setName(Optional.ofNullable(finalProcessMap.get(item.getCode())).map(ManufactureProcessPO::getName).orElse(null));
                    }
                });
            }
            if (MapUtils.isNotEmpty(finalFlowMap)) {
                processFlowDTO.getItems().forEach(item -> {
                    if (item.getType() == ItemType.FLOW) {
                        item.setName(Optional.ofNullable(finalFlowMap.get(item.getCode())).map(ProcessFlowPO::getName).orElse(null));
                    }
                });
            }
            return processFlowDTO;
        }).collect(Collectors.toList());
    }

}
