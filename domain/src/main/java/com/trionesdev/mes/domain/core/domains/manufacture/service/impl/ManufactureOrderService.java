package com.trionesdev.mes.domain.core.domains.manufacture.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.manufacture.entity.ManufactureOrder;
import com.trionesdev.mes.domain.core.domains.manufacture.internal.ManufactureBeanConvert;
import com.trionesdev.mes.domain.core.domains.manufacture.manager.impl.ManufactureOrderManager;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.criteria.ManufactureOrderCriteria;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.criteria.ManufactureOrderTaskCriteria;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderPO;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderTaskPO;
import com.trionesdev.mes.domain.core.dto.manufacture.ManufactureOrderDTO;
import com.trionesdev.mes.domain.core.dto.manufacture.ManufactureOrderTaskDTO;
import com.trionesdev.mes.domain.core.dto.masterdata.ManufactureProcessDTO;
import com.trionesdev.mes.domain.core.dto.masterdata.ProductDefinitionDTO;
import com.trionesdev.mes.domain.core.provider.ssp.custom.impl.CustomProvider;
import com.trionesdev.mes.domain.core.provider.ssp.masterdata.impl.MasterDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ManufactureOrderService {
    private final ManufactureBeanConvert convert;
    private final ManufactureOrderManager manufactureOrderManager;
    private final MasterDataProvider masterDataProvider;
    private final CustomProvider customProvider;

    public void createManufactureOrder(ManufactureOrder order) {
        if (StrUtil.isBlank(order.getCode())) {
            order.setCode(customProvider.generateCode("MANUFACTURE_ORDER"));
        }
        manufactureOrderManager.create(order);
    }

    public void deleteManufactureOrder(String id) {
        manufactureOrderManager.deleteById(id);
    }

    public void updateManufactureOrderById(ManufactureOrder order) {
        manufactureOrderManager.updateById(order);
    }

    public Optional<ManufactureOrderDTO> findManufactureOrderById(String id) {
        return manufactureOrderManager.findById(id).map(order -> {
            return assembleOrder(order);
        });
    }


    public PageInfo<ManufactureOrderDTO> findManufactureOrderPage(ManufactureOrderCriteria criteria) {
        PageInfo<ManufactureOrder> page = manufactureOrderManager.findPage(criteria);
        return PageUtils.of(page, assembleBatch(page.getRows()));
    }

    private ManufactureOrderDTO assembleOrder(ManufactureOrder order) {
        var dto = convert.entityToDto(order);
        var productSnap = masterDataProvider.getProductByCode(order.getProductCode());
        Set<String> taskCodes = order.getTasks().stream().map(ManufactureOrder.Task::getProcessCode).collect(Collectors.toSet());
        Map<String, ManufactureProcessDTO> processMap = masterDataProvider.getProcessesByCodes(taskCodes).stream().collect(Collectors.toMap(ManufactureProcessDTO::getCode, v -> v, (v1, v2) -> v1));
        Set<String> materialCodes = order.getMaterials().stream().map(ManufactureOrder.Material::getProductCode).collect(Collectors.toSet());
        Map<String, ProductDefinitionDTO> materialMap = masterDataProvider.getProductsByCodes(materialCodes).stream().collect(Collectors.toMap(ProductDefinitionDTO::getCode, v -> v, (v1, v2) -> v1));
        dto.setProduct(
                Optional.ofNullable(productSnap).map(product -> ManufactureOrderDTO.Product.builder().code(product.getCode()).name(product.getName()).build()).orElse(null)
        );
        dto.setTasks(order.getTasks().stream().map(task -> {
            var taskDto = convert.taskEntityToDto(task);
            Optional.ofNullable(processMap.get(task.getProcessCode())).ifPresent(process ->
                    taskDto.setName(process.getName())
            );
            return taskDto;
        }).toList());
        dto.setMaterials(order.getMaterials().stream().map(material -> {
            var materialDto = convert.materialEntityToDto(material);
            Optional.ofNullable(materialMap.get(material.getProductCode())).ifPresent(product ->
                    materialDto.setName(product.getName())
            );
            return materialDto;
        }).toList());
        return dto;
    }

    private List<ManufactureOrderDTO> assembleBatch(List<ManufactureOrder> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        Set<String> productCodes = records.stream().map(ManufactureOrder::getProductCode).collect(Collectors.toSet());
        Map<String, ProductDefinitionDTO> productsMap = masterDataProvider.getProductsByCodes(productCodes).stream().collect(Collectors.toMap(ProductDefinitionDTO::getCode, v -> v, (v1, v2) -> v1));
        Set<String> taskCodes = records.stream().flatMap(order -> order.getTasks().stream().map(ManufactureOrder.Task::getProcessCode)).collect(Collectors.toSet());
        Map<String, ManufactureProcessDTO> processMap = masterDataProvider.getProcessesByCodes(taskCodes).stream().collect(Collectors.toMap(ManufactureProcessDTO::getCode, v -> v, (v1, v2) -> v1));
        Set<String> materialCodes = records.stream().flatMap(order -> order.getMaterials().stream().map(ManufactureOrder.Material::getProductCode)).collect(Collectors.toSet());
        Map<String, ProductDefinitionDTO> materialMap = masterDataProvider.getProductsByCodes(materialCodes).stream().collect(Collectors.toMap(ProductDefinitionDTO::getCode, v -> v, (v1, v2) -> v1));
        return records.stream().map(order -> {
            var dto = convert.entityToDto(order);
            dto.setProduct(
                    Optional.ofNullable(productsMap.get(order.getProductCode())).map(product -> ManufactureOrderDTO.Product.builder().code(product.getCode()).name(product.getName()).specification(product.getSpecification()).build()).orElse(null)
            );
            dto.setTasks(order.getTasks().stream().map(task -> {
                var taskDto = convert.taskEntityToDto(task);
                Optional.ofNullable(processMap.get(task.getProcessCode())).ifPresent(process ->
                        taskDto.setName(process.getName()).setRatio(process.getRatio())
                );
                return taskDto;
            }).toList());
            dto.setMaterials(order.getMaterials().stream().map(material -> {
                var materialDto = convert.materialEntityToDto(material);
                Optional.ofNullable(materialMap.get(material.getProductCode())).ifPresent(product ->
                        materialDto.setName(product.getName()).setSpecification(product.getSpecification()).setType(product.getType()).setUnit(Optional.ofNullable(product.getUnit()).map(ProductDefinitionDTO.Unit::getName).orElse(null))
                );
                return materialDto;
            }).toList());
            return dto;
        }).collect(Collectors.toList());
    }

    private ManufactureOrderTaskDTO assembleTask(ManufactureOrderTaskPO record) {
        var dto = convert.taskPoToDto(record);
        manufactureOrderManager.findRecordById(record.getOrderId()).ifPresent(order -> {
            dto.setOrderCode(order.getCode());
            Optional.ofNullable(masterDataProvider.getProductByCode(order.getProductCode())).ifPresent(product -> {
                dto.setProduct(ManufactureOrderTaskDTO.Product.builder().code(product.getCode()).name(product.getName()).specification(product.getSpecification()).build());
            });
            Optional.ofNullable(masterDataProvider.getProcessByCode(record.getProcessCode())).ifPresent(process -> {
                dto.setName(process.getName());
            });
        });
        return dto;
    }

    private List<ManufactureOrderTaskDTO> assembleTasks(List<ManufactureOrderTaskPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        Set<String> orderIds = records.stream().map(ManufactureOrderTaskPO::getOrderId).collect(Collectors.toSet());
        List<ManufactureOrderPO> orders = manufactureOrderManager.findOrderRecords(orderIds);
        Set<String> productCodes = orders.stream().map(ManufactureOrderPO::getProductCode).collect(Collectors.toSet());
        Map<String, ProductDefinitionDTO> productMap = masterDataProvider.getProductsByCodes(productCodes).stream().collect(Collectors.toMap(ProductDefinitionDTO::getCode, v -> v, (v1, v2) -> v1));
        Map<String, ManufactureOrderPO> orderMap = orders.stream().collect(Collectors.toMap(ManufactureOrderPO::getId, v -> v, (v1, v2) -> v1));
        Set<String> processCodes = records.stream().map(ManufactureOrderTaskPO::getProcessCode).collect(Collectors.toSet());
        Map<String, ManufactureProcessDTO> processMap = masterDataProvider.getProcessesByCodes(processCodes).stream().collect(Collectors.toMap(ManufactureProcessDTO::getCode, v -> v, (v1, v2) -> v1));
        return records.stream().map(record -> {
            var dto = convert.taskPoToDto(record);
            Optional.ofNullable(orderMap.get(record.getOrderId())).ifPresent(order -> {
                dto.setOrderCode(order.getCode());
                Optional.ofNullable(productMap.get(order.getProductCode())).ifPresent(product -> {
                    dto.setProduct(ManufactureOrderTaskDTO.Product.builder().code(product.getCode()).name(product.getName()).specification(product.getSpecification()).build());
                });
            });
            Optional.ofNullable(processMap.get(record.getProcessCode())).ifPresent(process ->
                    dto.setName(process.getName())
            );
            return dto;
        }).collect(Collectors.toList());
    }

    public PageInfo<ManufactureOrderTaskDTO> findTasksPage(ManufactureOrderTaskCriteria criteria) {
        var page = manufactureOrderManager.findTasksPage(criteria);
        return PageUtils.of(page, assembleTasks(page.getRows()));
    }

    public Optional<ManufactureOrderTaskDTO> findTaskById(String taskId) {
        return manufactureOrderManager.findTaskById(taskId).map(this::assembleTask);
    }

}
