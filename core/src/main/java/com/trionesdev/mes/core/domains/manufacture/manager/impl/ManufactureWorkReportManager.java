package com.trionesdev.mes.core.domains.manufacture.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.core.domains.manufacture.dao.criteria.ManufactureWorkReportCriteria;
import com.trionesdev.mes.core.domains.manufacture.dao.impl.ManufactureOrderDAO;
import com.trionesdev.mes.core.domains.manufacture.dao.impl.ManufactureOrderTaskDAO;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureOrderPO;
import com.trionesdev.mes.core.domains.manufacture.dto.ManufactureWorkReportDTO;
import com.trionesdev.mes.core.domains.manufacture.internal.ManufactureBeanConvert;
import com.trionesdev.mes.core.domains.manufacture.internal.entity.ManufactureWorkReport;
import com.trionesdev.mes.core.domains.manufacture.repository.impl.ManufactureWorkReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ManufactureWorkReportManager {
    private final ManufactureBeanConvert convert;
    private final ManufactureWorkReportRepository taskReportRepository;
    private final ManufactureOrderDAO orderDAO;
    private final ManufactureOrderTaskDAO orderTaskDAO;

    public void create(ManufactureWorkReport record) {
        var task = orderTaskDAO.getById(record.getTaskId());
        if (Objects.nonNull(task)) {
            record.setOrderId(task.getOrderId());
            record.setProcessCode(task.getProcessCode());
            record.createInitialize();
            taskReportRepository.save(record);
        }
    }

    public PageInfo<ManufactureWorkReportDTO> findPage(ManufactureWorkReportCriteria criteria) {
        var pageInfo = taskReportRepository.findPage(criteria);
        return PageUtils.of(pageInfo, assembleReports(pageInfo.getRows()));
    }

    private List<ManufactureWorkReportDTO> assembleReports(List<ManufactureWorkReport> records){
        if (CollectionUtil.isEmpty(records)){
            return Collections.emptyList();
        }
        var orderIds = records.stream().map(ManufactureWorkReport::getOrderId).collect(Collectors.toSet());
        var orderMap = orderDAO.listByIds(orderIds).stream().collect(Collectors.toMap(ManufactureOrderPO::getId, v->v,(v1,v2)->v1));
        return records.stream().map(t->{
            var report = convert.reportEntityToDto(t);
            report.setOrder(Optional.ofNullable(orderMap.get(t.getOrderId())).map(convert::reportOrderPoToDto).orElse(null));
            return report;
        }).toList();
    }
}
