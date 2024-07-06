package com.trionesdev.mes.core.domains.manufacture.repository.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.mes.core.domains.manufacture.dao.impl.ManufactureOrderTaskReportDAO;
import com.trionesdev.mes.core.domains.manufacture.dao.impl.ManufactureOrderTaskReportDefectiveDAO;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureOrderTaskReportDefectivePO;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureOrderTaskReportPO;
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
public class ManufactureOrderTaskReportRepository implements BaseRepository {
    private final ManufactureBeanConvert convert;
    private final ManufactureOrderTaskReportDAO orderTaskReportDAO;
    private final ManufactureOrderTaskReportDefectiveDAO reportDefectiveDAO;

    @Transactional
    public void save(ManufactureOrderTaskReport record) {
        var report = convert.reportEntityToPo(record);
        orderTaskReportDAO.save(report);
        if (CollectionUtil.isNotEmpty(record.getDefectiveItems())) {
            List<ManufactureOrderTaskReportDefectivePO> defectiveItems = record.getDefectiveItems().stream().map(defectiveItem -> {
                return ManufactureOrderTaskReportDefectivePO.builder().reportId(report.getId()).code(defectiveItem.getCode()).quantity(defectiveItem.getQuantity()).build();
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
        if (CollectionUtil.isNotEmpty(record.getDefectiveItems())) {
            List<ManufactureOrderTaskReportDefectivePO> defectiveItems = record.getDefectiveItems().stream().map(defectiveItem -> {
                return ManufactureOrderTaskReportDefectivePO.builder().reportId(report.getId()).code(defectiveItem.getCode()).quantity(defectiveItem.getQuantity()).build();
            }).collect(Collectors.toList());
            reportDefectiveDAO.saveBatch(defectiveItems);
        }
    }

    public Optional<ManufactureOrderTaskReport> findById(String id) {
        return Optional.ofNullable(orderTaskReportDAO.getById(id)).map(po -> {
            var report = convert.reportPoToEntity(po);
            List<ManufactureOrderTaskReport.DefectiveItem> defectivePOList = reportDefectiveDAO.selectByReportId(id).stream().map(defectivePO -> {
                return ManufactureOrderTaskReport.DefectiveItem.builder().code(defectivePO.getCode()).quantity(defectivePO.getQuantity()).build();
            }).collect(Collectors.toList());
            report.setDefectiveItems(defectivePOList);
            return report;
        });
    }

}
