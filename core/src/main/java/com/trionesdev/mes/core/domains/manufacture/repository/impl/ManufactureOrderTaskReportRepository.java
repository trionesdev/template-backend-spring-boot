package com.trionesdev.mes.core.domains.manufacture.repository.impl;

import com.trionesdev.mes.core.domains.manufacture.dao.impl.ManufactureOrderTaskReportDAO;
import com.trionesdev.mes.core.domains.manufacture.entity.ManufactureOrderTaskReport;
import com.trionesdev.mes.core.domains.manufacture.internal.ManufactureBeanConvert;
import com.trionesdev.mes.infrastructure.ddd.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ManufactureOrderTaskReportRepository implements BaseRepository {
    private final ManufactureBeanConvert convert;
    private final ManufactureOrderTaskReportDAO orderTaskReportDAO;

    public void save(ManufactureOrderTaskReport record) {
        var report = convert.reportEntityToPo(record);
        orderTaskReportDAO.save(report);
    }

    public void deleteById(String id) {
        orderTaskReportDAO.removeById(id);
    }

    public void updateById(ManufactureOrderTaskReport record) {
        var report = convert.reportEntityToPo(record);
        orderTaskReportDAO.updateById(report);
    }

}
