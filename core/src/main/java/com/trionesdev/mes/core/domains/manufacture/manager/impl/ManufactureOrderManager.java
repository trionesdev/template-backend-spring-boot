package com.trionesdev.mes.core.domains.manufacture.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureOrderPO;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureOrderTaskPO;
import com.trionesdev.mes.core.domains.manufacture.entity.ManufactureOrder;
import com.trionesdev.mes.core.domains.manufacture.internal.ManufactureBeanConvert;
import com.trionesdev.mes.core.domains.manufacture.dao.criteria.ManufactureOrderCriteria;
import com.trionesdev.mes.core.domains.manufacture.dao.criteria.ManufactureOrderTaskCriteria;
import com.trionesdev.mes.core.domains.manufacture.dao.impl.ManufactureOrderMaterialDAO;
import com.trionesdev.mes.core.domains.manufacture.dao.impl.ManufactureOrderDAO;
import com.trionesdev.mes.core.domains.manufacture.dao.impl.ManufactureOrderTaskDAO;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureOrderMaterialPO;
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
    private final ManufactureOrderDAO manufactureOrderDAO;
    private final ManufactureOrderTaskDAO orderTaskDAO;
    private final ManufactureOrderMaterialDAO orderMaterialDAO;

    public void saveOrderRelations(ManufactureOrder order) {
        if (CollectionUtil.isNotEmpty(order.getTasks())) {
            List<ManufactureOrderTaskPO> tasks = order.getTasks().stream().map(task -> {
                ManufactureOrderTaskPO taskPO = convert.entityToPo(task);
                taskPO.setOrderId(order.getId());
                return taskPO;
            }).collect(Collectors.toList());
            orderTaskDAO.saveBatch(tasks);
        }
        if (CollectionUtil.isNotEmpty(order.getMaterials())) {
            List<ManufactureOrderMaterialPO> materials = order.getMaterials().stream().map(material -> {
                ManufactureOrderMaterialPO materialPO = convert.entityToPo(material);
                materialPO.setOrderId(order.getId());
                return materialPO;
            }).collect(Collectors.toList());
            orderMaterialDAO.saveBatch(materials);
        }
    }

    @Transactional
    public void create(ManufactureOrder order) {
        var orderPO = convert.entityToPo(order);
        manufactureOrderDAO.save(orderPO);
        order.setId(orderPO.getId());
        saveOrderRelations(order);
    }


    @Transactional
    public void deleteById(String id) {
        manufactureOrderDAO.removeById(id);
        orderTaskDAO.deleteByOrderId(id);
        orderMaterialDAO.deleteByOrderId(id);
    }

    @Transactional
    public void updateById(ManufactureOrder order) {
        orderTaskDAO.deleteByOrderId(order.getId());
        orderMaterialDAO.deleteByOrderId(order.getId());
        manufactureOrderDAO.updateById(convert.entityToPo(order));
        saveOrderRelations(order);
    }

    public Optional<ManufactureOrder> findById(String id) {
        return Optional.ofNullable(manufactureOrderDAO.getById(id)).map(t -> {
            return orderAssemble(t);
        });
    }

    public Optional<ManufactureOrder> findByCode(String code) {
        return Optional.ofNullable(manufactureOrderDAO.selectByCode(code)).map(t -> {
            return orderAssemble(t);
        });
    }

    public List<ManufactureOrder> findList(ManufactureOrderCriteria criteria) {
        var orders = manufactureOrderDAO.selectList(criteria);
        return assembleBatch(orders);
    }

    public PageInfo<ManufactureOrder> findPage(ManufactureOrderCriteria criteria) {
        PageInfo<ManufactureOrderPO> page = manufactureOrderDAO.selectPage(criteria);
        return PageUtils.of(page, assembleBatch(page.getRows()));
    }

    private ManufactureOrder orderAssemble(ManufactureOrderPO order) {
        var orderEntity = convert.poToEntity(order);
        orderEntity.setTasks(
                orderTaskDAO.selectListByOrderId(order.getId()).stream().map(task -> {
                    return convert.taskPoToEntity(task);
                }).collect(Collectors.toList())
        );
        orderEntity.setMaterials(orderMaterialDAO.selectListByOrderId(order.getId()).stream().map(material -> {
            return convert.materialPoToEntity(material);
        }).collect(Collectors.toList()));
        return orderEntity;
    }

    private List<ManufactureOrder> assembleBatch(List<ManufactureOrderPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        Set<String> ids = records.stream().map(ManufactureOrderPO::getId).collect(Collectors.toSet());
        Map<String, List<ManufactureOrderTaskPO>> tasks = orderTaskDAO.selectListByOrderIds(ids).stream().collect(Collectors.groupingBy(ManufactureOrderTaskPO::getOrderId));
        Map<String, List<ManufactureOrderMaterialPO>> materials = orderMaterialDAO.selectListByOrderIds(ids).stream().collect(Collectors.groupingBy(ManufactureOrderMaterialPO::getOrderId));
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

    public Optional<ManufactureOrderPO> findRecordById(String id) {
        return Optional.ofNullable(manufactureOrderDAO.getById(id));
    }

    public List<ManufactureOrderPO> findOrderRecords(Collection<String> orderIds) {
        if (CollectionUtil.isEmpty(orderIds)) {
            return Collections.emptyList();
        }
        return manufactureOrderDAO.listByIds(orderIds);
    }

    public PageInfo<ManufactureOrderTaskPO> findTasksPage(ManufactureOrderTaskCriteria criteria) {
        return orderTaskDAO.selectPage(criteria);
    }

    public Optional<ManufactureOrderTaskPO> findTaskById(String id) {
        return Optional.ofNullable(orderTaskDAO.getById(id));
    }

    public void updateOrderTaskById(ManufactureOrderTaskPO task) {
        orderTaskDAO.updateById(task);
    }

}
