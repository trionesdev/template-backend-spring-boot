package com.trionesdev.mes.core.domains.manufacture.service.impl;

import com.trionesdev.mes.core.domains.manufacture.dto.ManufactureOrderTaskReportDTO;
import com.trionesdev.mes.core.domains.manufacture.internal.ManufactureBeanConvert;
import com.trionesdev.mes.core.domains.manufacture.manager.impl.ManufactureOrderTaskReportManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ManufactureOrderTaskReportService {
    private final ManufactureBeanConvert convert;
    private final ManufactureOrderTaskReportManager orderTaskReportManager;

    public void createReport(ManufactureOrderTaskReportDTO report) {
        var orderTaskReport = convert.reportDtoToEntity(report);
        orderTaskReportManager.create(orderTaskReport);
    }

}
