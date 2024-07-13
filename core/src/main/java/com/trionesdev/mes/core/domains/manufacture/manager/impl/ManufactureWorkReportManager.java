package com.trionesdev.mes.core.domains.manufacture.manager.impl;

import com.trionesdev.mes.core.domains.manufacture.dao.impl.ManufactureOrderTaskDAO;
import com.trionesdev.mes.core.domains.manufacture.internal.entity.ManufactureOrderTaskReport;
import com.trionesdev.mes.core.domains.manufacture.repository.impl.ManufactureWorkReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ManufactureWorkReportManager {
    private final ManufactureWorkReportRepository taskReportRepository;
    private final ManufactureOrderTaskDAO orderTaskDAO;

    public void create(ManufactureOrderTaskReport record) {
        var task = orderTaskDAO.getById(record.getTaskId());
        if (Objects.nonNull(task)) {
            record.setOrderId(task.getOrderId());
            record.setProcessCode(task.getProcessCode());
            taskReportRepository.save(record);
        }
    }

}
