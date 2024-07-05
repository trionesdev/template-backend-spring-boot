package com.trionesdev.mes.core.domains.manufacture.manager.impl;

import com.trionesdev.mes.core.domains.manufacture.internal.entity.ManufactureOrderTaskReport;
import com.trionesdev.mes.core.domains.manufacture.repository.impl.ManufactureOrderTaskReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ManufactureOrderTaskReportManager {
    private final ManufactureOrderTaskReportRepository taskReportRepository;

    public void create(ManufactureOrderTaskReport record) {
        taskReportRepository.save(record);
    }

}
