package com.trionesdev.mes.core.domains.manufacture.repository.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.core.domains.manufacture.dao.criteria.ManufactureWorkReportCriteria;
import com.trionesdev.mes.core.domains.manufacture.dao.impl.ManufactureWorkReportDAO;
import com.trionesdev.mes.core.domains.manufacture.dao.impl.ManufactureWorkReportDefectiveDAO;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureWorkReportDefectivePO;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureWorkReportPO;
import com.trionesdev.mes.core.domains.manufacture.internal.entity.ManufactureWorkReport;
import com.trionesdev.mes.core.domains.manufacture.internal.ManufactureBeanConvert;
import com.trionesdev.mes.infrastructure.ddd.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class ManufactureWorkReportRepository implements BaseRepository {
    private final ManufactureBeanConvert convert;
    private final ManufactureWorkReportDAO orderTaskReportDAO;
    private final ManufactureWorkReportDefectiveDAO reportDefectiveDAO;

    @Transactional
    public void save(ManufactureWorkReport record) {
        var report = convert.reportEntityToPo(record);
        orderTaskReportDAO.save(report);
        if (CollectionUtil.isNotEmpty(record.getDefectives())) {
            List<ManufactureWorkReportDefectivePO> defectiveItems = record.getDefectives().stream().map(defectiveItem -> {
                return ManufactureWorkReportDefectivePO.builder()
                        .orderId(report.getOrderId()).taskId(report.getTaskId())
                        .reportId(report.getId()).processCode(report.getProcessCode()).defectiveCode(defectiveItem.getCode()).quantity(defectiveItem.getQuantity()).build();
            }).collect(Collectors.toList());
            reportDefectiveDAO.saveBatch(defectiveItems);
        }
    }

    @Transactional
    public void deleteById(String id) {
        orderTaskReportDAO.removeById(id);
        reportDefectiveDAO.removeByReportId(id);
    }

    @Transactional
    public void updateById(ManufactureWorkReport record) {
        var report = convert.reportEntityToPo(record);
        orderTaskReportDAO.updateById(report);
        reportDefectiveDAO.removeByReportId(report.getId());
        if (CollectionUtil.isNotEmpty(record.getDefectives())) {
            List<ManufactureWorkReportDefectivePO> defectiveItems = record.getDefectives().stream().map(defectiveItem -> {
                return ManufactureWorkReportDefectivePO.builder().reportId(report.getId()).defectiveCode(defectiveItem.getCode()).quantity(defectiveItem.getQuantity()).build();
            }).collect(Collectors.toList());
            reportDefectiveDAO.saveBatch(defectiveItems);
        }
    }

    public Optional<ManufactureWorkReport> findById(String id) {
        return Optional.ofNullable(orderTaskReportDAO.getById(id)).map(po -> {
            var report = convert.reportPoToEntity(po);
            List<ManufactureWorkReport.DefectiveItem> defectivePOList = reportDefectiveDAO.selectByReportId(id).stream().map(defectivePO -> {
                return ManufactureWorkReport.DefectiveItem.builder().code(defectivePO.getDefectiveCode()).quantity(defectivePO.getQuantity()).build();
            }).collect(Collectors.toList());
            report.setDefectives(defectivePOList);
            return report;
        });
    }

    public PageInfo<ManufactureWorkReport> findPage(ManufactureWorkReportCriteria criteria){
        var page = orderTaskReportDAO.selectPage(criteria);
    return PageUtils.of(page,assembleDefectives(page.getRows()));
    }

    private List<ManufactureWorkReport> assembleDefectives(List<ManufactureWorkReportPO> records){
        if (CollectionUtil.isEmpty(records)){
            return Collections.emptyList();
        }
        var reportIds = records.stream().map(ManufactureWorkReportPO::getId).collect(Collectors.toSet());
        Map<String, List<ManufactureWorkReport.DefectiveItem>> defectivesMap = reportDefectiveDAO.selectListByReportIds(reportIds).stream().collect(Collectors.toMap(ManufactureWorkReportDefectivePO::getReportId, v-> ListUtil.toList(ManufactureWorkReport.DefectiveItem.builder().code(v.getDefectiveCode()).quantity(v.getQuantity()).build()),(v1, v2)->{
            v1.addAll(v2);
            return v1;
        }));
        return records.stream().map(reportPO -> {
            var report = convert.reportPoToEntity(reportPO);
            report.setDefectives(defectivesMap.get(reportPO.getId()));
            return report;
        }).collect(Collectors.toList());
    }

}
