package com.trionesdev.mes.domain.core.domains.manufacture.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.manufacture.entity.ManufactureOrder;
import com.trionesdev.mes.domain.core.domains.manufacture.internal.ManufactureBeanConvert;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.criteria.ManufactureOrderCriteria;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.criteria.ManufactureOrderTaskCriteria;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.impl.ManufactureOrderMaterialRepository;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.impl.ManufactureOrderRepository;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.impl.ManufactureOrderTaskRepository;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderMaterialPO;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderPO;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderTaskPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ManufactureOrderManager {
    private final ManufactureBeanConvert convert;
    private final ManufactureOrderRepository manufactureOrderRepository;
    private final ManufactureOrderTaskRepository manufactureOrderTaskRepository;
    private final ManufactureOrderMaterialRepository manufactureOrderMaterialRepository;

    public void saveOrderRelations(ManufactureOrder order) {
        if (CollectionUtil.isNotEmpty(order.getTasks())) {
            List<ManufactureOrderTaskPO> tasks = order.getTasks().stream().map(task -> {
                ManufactureOrderTaskPO taskPO = convert.entityToPo(task);
                taskPO.setOrderId(order.getId());
                return taskPO;
            }).collect(Collectors.toList());
            manufactureOrderTaskRepository.saveBatch(tasks);
        }
        if (CollectionUtil.isNotEmpty(order.getMaterials())) {
            List<ManufactureOrderMaterialPO> materials = order.getMaterials().stream().map(material -> {
                ManufactureOrderMaterialPO materialPO = convert.entityToPo(material);
                materialPO.setOrderId(order.getId());
                return materialPO;
            }).collect(Collectors.toList());
            manufactureOrderMaterialRepository.saveBatch(materials);
        }
    }

    @Transactional
    public void create(ManufactureOrder order) {
        var orderPO = convert.entityToPo(order);
        manufactureOrderRepository.save(orderPO);
        order.setId(orderPO.getId());
        saveOrderRelations(order);
    }


    @Transactional
    public void deleteById(String id) {
        manufactureOrderRepository.removeById(id);
        manufactureOrderTaskRepository.deleteByOrderId(id);
        manufactureOrderMaterialRepository.deleteByOrderId(id);
    }

    @Transactional
    public void updateById(ManufactureOrder order) {
        manufactureOrderTaskRepository.deleteByOrderId(order.getId());
        manufactureOrderMaterialRepository.deleteByOrderId(order.getId());
        manufactureOrderRepository.updateById(convert.entityToPo(order));
        saveOrderRelations(order);
    }

    public Optional<ManufactureOrder> findById(String id) {
        return Optional.ofNullable(manufactureOrderRepository.getById(id)).map(t -> {
            return orderAssemble(t);
        });
    }


    public PageInfo<ManufactureOrder> findPage(ManufactureOrderCriteria criteria) {
        PageInfo<ManufactureOrderPO> page = manufactureOrderRepository.selectPage(criteria);
        return PageUtils.of(page, assembleBatch(page.getRows()));
    }

    private ManufactureOrder orderAssemble(ManufactureOrderPO order) {
        var orderEntity = convert.poToEntity(order);
        orderEntity.setTasks(
                manufactureOrderTaskRepository.selectListByOrderId(order.getId()).stream().map(task -> {
                    return convert.taskPoToEntity(task);
                }).collect(Collectors.toList())
        );
        orderEntity.setMaterials(manufactureOrderMaterialRepository.selectListByOrderId(order.getId()).stream().map(material -> {
            return convert.materialPoToEntity(material);
        }).collect(Collectors.toList()));
        return orderEntity;
    }

    private List<ManufactureOrder> assembleBatch(List<ManufactureOrderPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        Set<String> ids = records.stream().map(ManufactureOrderPO::getId).collect(Collectors.toSet());
        Map<String, List<ManufactureOrderTaskPO>> tasks = manufactureOrderTaskRepository.selectListByOrderIds(ids).stream().collect(Collectors.groupingBy(ManufactureOrderTaskPO::getOrderId));
        Map<String, List<ManufactureOrderMaterialPO>> materials = manufactureOrderMaterialRepository.selectListByOrderIds(ids).stream().collect(Collectors.groupingBy(ManufactureOrderMaterialPO::getOrderId));
        return records.stream().map(t -> {
            ManufactureOrder order = convert.poToEntity(t);
            order.setTasks(
                    tasks.getOrDefault(t.getId(), Collections.emptyList()).stream().map(task -> {
                        return convert.taskPoToEntity(task);
                    }).collect(Collectors.toList())
            );
            order.setMaterials(
                    materials.getOrDefault(t.getId(), Collections.emptyList()).stream().map(material -> {
                        return convert.materialPoToEntity(material);
                    }).collect(Collectors.toList())
            );
            return order;
        }).collect(Collectors.toList());
    }


    public List<ManufactureOrderPO> findOrderRecords(Collection<String> orderIds) {
        if (CollectionUtil.isEmpty(orderIds)) {
            return Collections.emptyList();
        }
        return manufactureOrderRepository.listByIds(orderIds);
    }

    public PageInfo<ManufactureOrderTaskPO> findTasksPage(ManufactureOrderTaskCriteria criteria) {
        return manufactureOrderTaskRepository.selectPage(criteria);
    }

}
