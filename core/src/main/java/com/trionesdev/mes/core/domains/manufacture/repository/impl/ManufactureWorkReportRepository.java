package com.trionesdev.mes.core.domains.manufacture.repository.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.mes.core.domains.manufacture.dao.impl.ManufactureWorkReportDAO;
import com.trionesdev.mes.core.domains.manufacture.dao.impl.ManufactureWorkReportDefectiveDAO;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureWorkReportDefectivePO;
import com.trionesdev.mes.core.domains.manufacture.internal.entity.ManufactureOrderTaskReport;
import com.trionesdev.mes.core.domains.manufacture.internal.ManufactureBeanConvert;
import com.trionesdev.mes.infrastructure.ddd.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class ManufactureWorkReportRepository implements BaseRepository {
    private final ManufactureBeanConvert convert;
    private final ManufactureWorkReportDAO orderTaskReportDAO;
    private final ManufactureWorkReportDefectiveDAO reportDefectiveDAO;

    @Transactional
    public void save(ManufactureOrderTaskReport record) {
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
    public void updateById(ManufactureOrderTaskReport record) {
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

    public Optional<ManufactureOrderTaskReport> findById(String id) {
        return Optional.ofNullable(orderTaskReportDAO.getById(id)).map(po -> {
            var report = convert.reportPoToEntity(po);
            List<ManufactureOrderTaskReport.DefectiveItem> defectivePOList = reportDefectiveDAO.selectByReportId(id).stream().map(defectivePO -> {
                return ManufactureOrderTaskReport.DefectiveItem.builder().code(defectivePO.getDefectiveCode()).quantity(defectivePO.getQuantity()).build();
            }).collect(Collectors.toList());
            report.setDefectives(defectivePOList);
            return report;
        });
    }

}
